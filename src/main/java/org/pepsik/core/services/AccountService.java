package org.pepsik.core.services;

import org.pepsik.core.models.entities.Account;
import org.pepsik.rest.utilities.AccountList;

/**
 * Created by pepsik on 9/29/2015.
 */
public interface AccountService {
    Account createAccount(Account data);

    Account findAccountById(Long id);

    Account findAccountByUsername(String username);

    AccountList findAllAccounts();

    void updateAccount(Account data);

    void deleteAccount(Account data);
}
