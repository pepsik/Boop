package org.pepsik.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pepsik.model.Profile;
import org.pepsik.model.User;
import org.pepsik.service.SmartService;
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

        binder.registerCustomEditor(String.class, "user.password", new PropertyEditorSupport() {
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

        service.saveProfile(profile);
        return "redirect:/registration_successful";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getUser(@PathVariable("username") String username, Model model) {
        model.addAttribute(service.getProfile(username));
        return "user/view_profile";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") long id) {
        service.deleteUser(id);
        return "redirect:/home";
    }
}
