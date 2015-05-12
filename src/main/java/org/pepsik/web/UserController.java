package org.pepsik.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pepsik.model.Account;
import org.pepsik.model.Profile;
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

import javax.servlet.http.HttpSession;
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
                    binder.getBindingResult().reject("birthdate.emptyOrInvalid");
                }
            }

            @Override
            public String getAsText() {
                return fmt.print((DateTime) getValue());
            }
        });

        binder.registerCustomEditor(String.class, "account.password", new PropertyEditorSupport() {
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
    public String newAccount(Model model) {
        Profile profile = new Profile();
        profile.setAccount(new Account());
        model.addAttribute(profile);
        return "user/create";
    }

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String editAccount(@PathVariable("username") String username, HttpSession session, Model model) {
        Profile profile = service.getProfile(username);
        session.setAttribute("profile", profile);
        model.addAttribute(profile);
        return "user/edit_profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createAccount(@Valid Profile profile, BindingResult bindingResult) {

        if (service.isExistUsername(profile.getAccount().getUsername()))
            bindingResult.rejectValue("account.username", "username.exist");

        if (bindingResult.hasErrors())
            return "user/create";

        Account account = profile.getAccount();
        service.saveAccount(account);
        logger.info(account.getId().toString());         //fix
        profile.setId(account.getId());
        service.saveProfile(profile);
        return "redirect:/user/" + profile.getAccount().getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String getProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute(service.getProfile(username));
        return "user/view_profile";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("username") String username, @Valid Profile editedProfile, BindingResult result, HttpSession session) {
        if (result.hasErrors())
            return "user/edit_profile";

        Profile profile = (Profile) session.getAttribute("profile");
        editedProfile.setId(profile.getId());
        editedProfile.setAccount(profile.getAccount());
        service.saveProfile(editedProfile);
        return "redirect:/user/" + username;
    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String deleteAccount(@PathVariable("id") long id) {
//        service.deleteAccount(id);
//        return "redirect:/home";
//    }
}
