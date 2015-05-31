package org.pepsik.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pepsik.model.Profile;
import org.pepsik.model.User;
import org.pepsik.service.SmartService;
import org.pepsik.web.exception.BadRequestException;
import org.pepsik.web.exception.ResourceNotFoundException;
import org.pepsik.web.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/user")
public class UserController {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SmartService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);

        binder.registerCustomEditor(DateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(fmt.parseDateTime(text));
                } catch (IllegalArgumentException ex) {
                    binder.getBindingResult().rejectValue("birthdate", "birthdate.emptyOrInvalid");
                }
            }

            @Override
            public String getAsText() {
                return fmt.print((DateTime) getValue());
            }
        });

        binder.registerCustomEditor(String.class, "user.userPassword.password", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                setValue(encoder.encode(text));
            }

            @Override
            public String getAsText() {
                return "";
            }
        });
    }

    @RequestMapping(method = RequestMethod.GET)
    public String newUser(Model model) {
        Profile profile = new Profile();
        profile.setUser(new User());
        model.addAttribute(profile);
        return "user/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@Valid Profile profile, BindingResult bindingResult) {
        if (service.isExistUsername(profile.getUser().getUsername()))
            bindingResult.rejectValue("user.username", "username.exist");
        if (bindingResult.hasErrors())
            return "user/create";
        User user = profile.getUser();
        user.setProfile(profile);
        user.getUserPassword().setUser(user);
        service.saveUser(user);
        return "redirect:/registration_successful";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getUserProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute(service.getProfile(username));
        return "user/view_profile";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") long id) {
        service.deleteUser(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{username}/favorites", method = RequestMethod.GET, produces = "text/html")
    public String getUserFavorites(@PathVariable String username, Model model) {
        if (!service.isExistUsername(username))
            throw new UserNotFoundException();
        model.addAttribute(service.getFavorites(username));
        model.addAttribute("username", username);
        return "user/favorites";
    }

    @RequestMapping(value = {"/{username}/posts/{pageId}"}, method = RequestMethod.GET, produces = "text/html")
    public String getUserPosts(@PathVariable("username") String username, @PathVariable("pageId") String strPageId, Model model) {
        if (!service.isExistUsername(username))
            throw new UserNotFoundException();
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long postsCount = service.getUserPostsCount(service.getUser(username));
        if (pageId > service.getPagesCount(postsCount))
            throw new ResourceNotFoundException();

        model.addAttribute(service.getUserPosts(username, pageId));
        model.addAttribute("pagination", service.getPagination(pageId, postsCount));
        model.addAttribute("currentPageIndex", pageId);
        return "user/posts";
    }
}
