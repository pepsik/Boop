package org.pepsik.validator;

import org.pepsik.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by pepsik on 4/18/15.
 */
public class AccountValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "username.emptyOrWhitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password.emptyOrWhitespace");
        ValidationUtils.rejectIfEmpty(e, "fullname", "fullname.empty");
        ValidationUtils.rejectIfEmpty(e, "birthdate", "birthdate.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "email.emptyOrWhitespace");
        Account account = (Account) target;
        if (account.getPassword().length() < 3)
            e.rejectValue("password", "password.less");

        if (account.getPassword().length() > 20)
            e.rejectValue("password", "password.more");

        if (account.getUsername().length() < 3)
            e.rejectValue("username", "username.less");

        if (account.getUsername().length() < 20)
            e.rejectValue("username", "username.more");

        if (account.getEmail().length() < 3)
            e.rejectValue("email", "email.less");

        if (account.getEmail().length() < 20)
            e.rejectValue("email", "email.more");

        if (account.getFullname().length() < 3)
            e.rejectValue("fullname", "fullname.less");

        if (account.getFullname().length() > 40)
            e.rejectValue("fullname", "fullname.more");
    }
}
