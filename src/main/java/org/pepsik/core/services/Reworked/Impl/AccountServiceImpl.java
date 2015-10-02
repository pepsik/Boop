package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.pepsik.core.services.Reworked.AccountService;
import org.pepsik.core.services.exceptions.AccountExistsException;
import org.pepsik.rest.utilities.AccountList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pepsik on 9/29/2015.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    @Override
    public Account createAccount(Account data) {
        Account account = accountJpaRepo.findByUsername(data.getUsername());
        if (account != null){
            throw new AccountExistsException();
        }
        return accountJpaRepo.create(data);
    }

    @Override
    public Account findAccountById(Long id) {
        return accountJpaRepo.findById(id);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountJpaRepo.findByUsername(username);
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountJpaRepo.findAll());
    }

    @Override
    public void updateAccount(Account data) {
        accountJpaRepo.update(data);
    }

    @Override
    public void deleteAccount(Account data) {
        accountJpaRepo.delete(data);
    }
}
