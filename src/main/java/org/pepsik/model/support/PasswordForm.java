package org.pepsik.model.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Support class which used in {@link org.pepsik.web.UserSettingsController} as mapping password
 * POST form for easier Spring {@code @Valid} process
 */

public class PasswordForm {

    @NotNull
    private String old_password;

    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String new_password;

    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String repeat_new_password;

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getRepeat_new_password() {
        return repeat_new_password;
    }

    public void setRepeat_new_password(String repeat_new_password) {
        this.repeat_new_password = repeat_new_password;
    }

    @Override
    public String toString() {
        return "ChangePassword{" +
                "old_password='" + old_password + '\'' +
                ", new_password='" + new_password + '\'' +
                ", repeat_new_password='" + repeat_new_password + '\'' +
                '}';
    }
}
