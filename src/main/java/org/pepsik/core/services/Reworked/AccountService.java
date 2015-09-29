package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Account;

import java.util.List;

/**
 * Created by pepsik on 9/29/2015.
 */
public interface AccountService {
    void createAccount(Account data);

    Account findAccountById(Long id);

    Account findAccountByLogin(String login);

    Account findAccountByUsername(String username);

    void updateAccount(Account data);

    void deleteAccount(Account data);

    List<Account> getAllAccounts();
}
