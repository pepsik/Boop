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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

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
        model.addAttribute("username", username);
        return "user/view_profile";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") long id) {
        service.deleteUser(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{username}/favorites/{pageId}", method = RequestMethod.GET, produces = "text/html")
    public String getUserFavorites(@PathVariable String username, @PathVariable(value = "pageId") String strPageId, Model model) {
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long favoritesCount = service.getUserFavoritesCount(username);
        if (pageId != 1 && favoritesCount == 0 || pageId <= 0)
            throw new ResourceNotFoundException();

        if (favoritesCount > 0) {
            if (pageId > service.getPagesCount(favoritesCount))
                throw new ResourceNotFoundException();

            model.addAttribute(service.getUserFavorites(username, pageId));
            model.addAttribute("pagination", service.getPagination(pageId, favoritesCount));
        } else
            model.addAttribute("pagination", Arrays.asList("1"));

        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute("username", username);
        model.addAttribute("favoritesCount", favoritesCount);
        return "user/favorites";
    }

    @RequestMapping(value = {"/{username}/posts/{pageId}"}, method = RequestMethod.GET, produces = "text/html")
    public String getUserPosts(@PathVariable("username") String username, @PathVariable("pageId") String strPageId, Model model) {
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long postsCount = service.getUserPostsCount(username);
        if (pageId != 1 && postsCount == 0 || pageId <= 0)
            throw new ResourceNotFoundException();

        if (postsCount > 0) {
            if (pageId > service.getPagesCount(postsCount))
                throw new ResourceNotFoundException();

            model.addAttribute(service.getUserPosts(username, pageId));
            model.addAttribute("pagination", service.getPagination(pageId, postsCount));
        } else
            model.addAttribute("pagination", Arrays.asList("1"));


        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute("username", username);
        model.addAttribute("postsCount", postsCount);
        return "user/posts";
    }

    @RequestMapping(value = {"/{username}/comments/{pageId}"}, method = RequestMethod.GET, produces = "text/html")
    public String getUserComments(@PathVariable("username") String username, @PathVariable("pageId") String strPageId, Model model) {
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long commentsCount = service.getUserCommentsCount(username);
        if (pageId != 1 && commentsCount == 0 || pageId <= 0)
            throw new ResourceNotFoundException();

        if (commentsCount > 0) {
            if (pageId > service.getPagesCount(commentsCount / 2))                  //TODO: comments defalut counter - (commentsCount / 2)
                throw new ResourceNotFoundException();

            model.addAttribute(service.getUserComments(username, pageId));
            model.addAttribute("pagination", service.getPagination(pageId, commentsCount / 2));
        } else
            model.addAttribute("pagination", Arrays.asList("1"));

        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute("username", username);
        model.addAttribute("commentsCount", commentsCount);
        return "user/comments";
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public void uploadUserAvatar(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
//        String name = file.getName();
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(request.getSession().getServletContext().getRealPath("/resources/images/avatars/"));
                File dir2 = new File("C:\\Users\\pepsik\\IdeaProjects\\SmartSite\\src\\main\\webapp\\resources\\images\\avatars");
                if (!dir.exists())
                    dir.mkdirs();

                if (!dir2.exists())
                    dir2.mkdirs();

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + loggedUser + ".jpeg");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                File serverFile2 = new File(dir2.getAbsolutePath()
                        + File.separator + loggedUser + ".jpeg");
                BufferedOutputStream stream2 = new BufferedOutputStream(
                        new FileOutputStream(serverFile2));
                stream2.write(bytes);
                stream2.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                logger.info("Server File Location="
                        + serverFile2.getAbsolutePath());
            } catch (Exception e) {
            }
        }
    }
}
