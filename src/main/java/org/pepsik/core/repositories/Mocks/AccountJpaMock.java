package org.pepsik.core.repositories.Mocks;

import org.pepsik.core.models.entities.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pepsik on 10/11/2015.
 */
public class AccountJpaMock {
    private Map<String, Account> accountsByUsername;
    private Map<Long, Account> accountsById;
    private Long idCounter;

    public AccountJpaMock() {
        Account account1 = new Account(1L, "username1", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF.");
        Account account2 = new Account(2L, "username2", "password");
        Account account3 = new Account(3L, "username3", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF.");
        Account account4 = new Account(4L, "username4", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF.");

        accountsByUsername = new HashMap<>();
        accountsByUsername.put("username1", account1);
        accountsByUsername.put("username2", account2);
        accountsByUsername.put("username3", account3);
        accountsByUsername.put("username4", account4);

        accountsById = new HashMap<>();
        accountsById.put(1L, account1);
        accountsById.put(2L, account2);
        accountsById.put(3L, account3);
        accountsById.put(4L, account4);

        idCounter = (long) accountsById.size();
    }

    public Account create(Account data) {
        data.setId(idCounter++);
        accountsByUsername.put(data.getUsername(), data);
        accountsById.put(data.getId(), data);
        return data;
    }

    public Account findById(Long id) {
        return accountsById.get(id);
    }

    public Account findByUsername(String username) {
        return accountsByUsername.get(username);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accountsByUsername.values());
    }

    public Account update(Account data) {
        return null;
    }

    public Account delete(Account data) {
        return null;
    }
}
