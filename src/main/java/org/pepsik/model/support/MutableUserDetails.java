package org.pepsik.model.support;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by pepsik on 5/29/15.
 */
public class MutableUserDetails implements UserDetails {

    private UserDetails userDetails;
    private String username;

    public MutableUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        setUsername(userDetails.getUsername());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    @Override
    public String toString() {
        return "MutableUserDetails{" +
                "userDetails=" + userDetails +
                ", username='" + username + '\'' +
                '}';
    }
}