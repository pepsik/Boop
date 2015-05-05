package org.pepsik.web;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pepsik.model.Account;
import org.pepsik.model.Profile;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

@Controller
@RequestMapping("/account")
public class AccountController {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

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
    }

    @RequestMapping(method = RequestMethod.GET)
    public String newAccount(Model model) {
        model.addAllAttributes(Arrays.asList(new Account(), new Profile()));
        return "account/create";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editAccount(@PathVariable("id") long id, HttpSession session, Model model) {
        Account account = service.getAccount(id);
        session.setAttribute("account", account);
        model.addAttribute(account);
        return "account/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult) {

        if (service.isExistUsername(account.getUsername()))
            bindingResult.rejectValue("username", "username.exist");

        if (bindingResult.hasErrors())
            return "account/create";

        service.saveAccount(account);
        return "redirect:/account/" + account.getId();
    }

    @RequestMapping(value = "/{userIdentificator}", method = RequestMethod.GET)
    public String getAccount(@PathVariable("userIdentificator") String userIdentificator, Model model) {
        Account account;
        if (NumberUtils.isNumber(userIdentificator))
            account = service.getAccount(Long.parseLong(userIdentificator));
        else
            account = service.getAccount(userIdentificator);
        model.addAttribute("account", account);
        return "account/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") long id, Account editedAccount, HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        editedAccount.setId(account.getId());
        service.saveAccount(editedAccount);
        return "redirect:/account/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAccount(@PathVariable("id") long id) {
        service.deleteAccount(id);
        return "redirect:/home";
    }
}
