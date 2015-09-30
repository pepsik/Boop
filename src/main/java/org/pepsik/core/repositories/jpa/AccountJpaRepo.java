package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Account;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class AccountJpaRepo {
    private Map<String, Account> accounts;
    private Long idCounter = 5L;

    public AccountJpaRepo() {
        accounts = new HashMap<>();
        accounts.put("username1", new Account(1L, "username1", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."));
        accounts.put("username2", new Account(2L, "username2", "password"));
        accounts.put("username3", new Account(3L, "username3", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."));
        accounts.put("username4", new Account(4L, "username4", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."));
    }

    public Account create(Account data) {
        data.setId(idCounter++);
        accounts.put(data.getUsername(), data);
        return data;
    }

    public Account findById(Long id) {
        return null;
    }

    public Account findByUsername(String username) {
        return accounts.get(username);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public void update(Account data){
    }

    public void delete(Account data) {
    }
}
