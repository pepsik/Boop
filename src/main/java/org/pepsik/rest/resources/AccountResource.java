package org.pepsik.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pepsik.core.models.entities.Reworked.Account;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by pepsik on 9/17/2015.
 */

public class AccountResource extends ResourceSupport {

    private String username;

    private String login;

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
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setUsername(username);
        account.setLogin(login);
        account.setPassword(encoder.encode(password));
        return account;
    }
}
