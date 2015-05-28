package org.pepsik.model.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by pepsik on 5/29/15.
 */
public class EmailForm {

    @NotNull
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
