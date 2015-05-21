package org.pepsik.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pepsik.model.Account;
import org.pepsik.model.Profile;
import org.pepsik.service.SmartService;
import org.pepsik.model.support.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

/**
 * Created by pepsik on 5/17/15.
 */

@Controller
@RequestMapping("/settings")
public class UserSettingsController {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final Logger logger = LoggerFactory.getLogger(UserSettingsController.class);


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
                    binder.getBindingResult().rejectValue("birthdate", "birthdate.emptyOrInvalid", "Empty or invalid");
                }
            }

            @Override
            public String getAsText() {
                return fmt.print((DateTime) getValue());
            }
        });

        binder.registerCustomEditor(String.class, "password", new PropertyEditorSupport() {
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "text/html")
    public String getProfile(Model model, HttpSession session) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = service.getProfile(loggedUser);
        session.setAttribute("profile", profile);
        model.addAttribute(profile);
        return "settings/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PUT, produces = "text/html")
    public String updateProfile(@Valid Profile updatedProfile, BindingResult result, HttpSession session) {
        if (result.hasErrors())
            return "settings/profile";

        Profile oldProfile = (Profile) session.getAttribute("profile");
        session.removeAttribute("profile");

        updatedProfile.setId(oldProfile.getId());
        updatedProfile.setAccount(oldProfile.getAccount());
        updatedProfile.setEmail(oldProfile.getEmail());
        service.saveProfile(updatedProfile);
        return "redirect:/settings/profile";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = "text/html")
    public String getAccount(Model model, HttpSession session) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = service.getAccount(loggedUser);
        session.setAttribute("account", account);
        model.addAttribute(account);
        model.addAttribute(new Password());
        return "settings/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.PUT, produces = "text/html")
    public String updateAccount(@Valid Password password, BindingResult result, HttpSession session, Model model) {

        final Account account = (Account) session.getAttribute("account");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password.getOld_password(), account.getPassword()))
            result.addError(new FieldError("password", "old_password", "Old password isn't valid"));

        if (!password.getNew_password().equals(password.getRepeat_new_password()))
            result.addError(new FieldError("password", "repeat_new_password", "Confirm password not match"));

        if (result.hasErrors()) {
            model.addAttribute(result);
            return "settings/account";
        }

        account.setPassword(encoder.encode(password.getNew_password()));
        session.removeAttribute("account");
        return "redirect:/settings/account";
    }

    @RequestMapping(value = "/emails", method = RequestMethod.GET, produces = "text/html")
    public String getEmails(Model model) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = service.getProfile(loggedUser);
        String email = profile.getEmail();
        if (email == null)
            email = "";

        model.addAttribute("email", email);
        return "settings/emails";
    }

    @RequestMapping(value = "/emails", method = RequestMethod.PUT, produces = "text/html")
    public String updateEmails(String email, Model model) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = service.getProfile(loggedUser);
        profile.setEmail(email);                   //TODO: validation email
        service.saveProfile(profile);
        model.addAttribute("email", email);
        return "redirect:/settings/emails";
    }

    @RequestMapping(value = "/security", method = RequestMethod.GET, produces = "text/html")
    public String getSecurity(Model model) {
        return "settings/security";
    }
}
