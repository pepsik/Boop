package org.pepsik.validator;

import org.pepsik.model.support.PasswordForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by pepsik on 5/20/15.
 */
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm password = (PasswordForm) target;
        if (password.getNew_password().length() < 3) {
            errors.rejectValue("new_password", "password.short");
        }

        if (password.getNew_password().length() > 20) {
            errors.rejectValue("new_password", "password.tooLarge");
        }

        if (!password.getNew_password().equals(password.getRepeat_new_password()))
            errors.rejectValue("new_password", "password.confirmPasswordNotMatch");
    }
}
