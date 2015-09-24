package org.pepsik.core.security;

import org.pepsik.core.models.entities.User;
import org.pepsik.core.services.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by pepsik on 9/23/2015.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SmartService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User account = service.getUser(username);

        if (account == null)
            throw new UsernameNotFoundException("no users found");

        return new AccountUserDetails(account);
    }
}
