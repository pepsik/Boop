package org.pepsik.core.model.support;

import org.pepsik.rest.mvc.UserSettingsController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Support class which used in {@link UserSettingsController} as mapping email
 * POST form for easier Spring {@code @Valid} process
 */

public class EmailForm {

    @NotNull
    @Size(min = 8, max = 40)
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailForm{" +
                "email='" + email + '\'' +
                '}';
    }
}
