package org.pepsik.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pepsik.core.models.entities.Password;
import org.pepsik.core.models.entities.User;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by pepsik on 9/17/2015.
 */

public class AccountResource extends ResourceSupport {

    private String username;

    private String password;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public User toAccount() {
        User user = new User();
        user.setUsername(username);
        user.setUserPassword(new Password(password));
        return user;
    }
}
