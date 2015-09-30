package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Account;

import java.util.List;

/**
 * Created by pepsik on 9/29/2015.
 */
public interface AccountService {
    Account createAccount(Account data);

    Account findAccountById(Long id);

    Account findAccountByUsername(String username);

    List<Account> findAllAccounts();

    void updateAccount(Account data);

    void deleteAccount(Account data);
}
