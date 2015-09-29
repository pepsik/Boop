package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Account;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class AccountJpaRepo {
    private Map<String, Account> accounts;

    public AccountJpaRepo() {
        accounts = new HashMap<>();
        accounts.put("username1", new Account("username1", "test1", "password"));
        accounts.put("username2", new Account("username2", "test2", "password"));
        accounts.put("username3", new Account("username3", "test3", "password"));
        accounts.put("username4", new Account("username4", "test4", "password"));
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public Account getAccountByUsername(String username) {
        return accounts.get(username);
    }
}
