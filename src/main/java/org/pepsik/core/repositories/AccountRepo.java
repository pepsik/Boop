package org.pepsik.core.repositories;

import org.pepsik.core.models.entities.Reworked.Account;

import java.util.List;

/**
 * Created by pepsik on 10/11/2015.
 */
public interface AccountRepo {
    Account create(Account data);

    Account findById(Long id);

    Account findByUsername(String username);

    List<Account> findAll();

    Account update(Account data);

    Account delete(Account data);
}
