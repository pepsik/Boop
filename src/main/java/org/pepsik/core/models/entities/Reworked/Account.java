package org.pepsik.core.models.entities.Reworked;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by pepsik on 9/29/2015.
 */
public class Account {
    private Long id;
    private String username;
    private String login;
    private String password;

    public Account() {
    }

    public Account(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
