package org.pepsik.core.models.entities.Reworked;

/**
 * Created by pepsik on 9/29/2015.
 */
public class Account {
    private Long id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(Long id, String username, String password) { // temp
        this.id = id;
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
