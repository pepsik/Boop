package org.pepsik.core.security;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.services.AccountService;
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
    private AccountService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = service.findAccountByUsername(username);

        if (account == null)
            throw new UsernameNotFoundException("no users found");

        return new AccountUserDetails(account);
    }
}
