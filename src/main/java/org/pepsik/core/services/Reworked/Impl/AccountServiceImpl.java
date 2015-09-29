package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.pepsik.core.services.Reworked.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pepsik on 9/29/2015.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    @Override
    public void createAccount(Account data) {
    }

    @Override
    public Account findAccountById(Long id) {
        return null;
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountJpaRepo.getAccountByUsername(username);
    }

    @Override
    public Account findAccountByLogin(String login) {
        return null;
    }

    @Override
    public void deleteAccount(Account data) {

    }

    @Override
    public void updateAccount(Account data) {

    }

    @Override
    public List<Account> getAllAccounts() {
        return accountJpaRepo.getAllAccounts();
    }
}
