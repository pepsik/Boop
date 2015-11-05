package org.pepsik.core.services.Impl;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.models.entities.Profile;
import org.pepsik.core.repositories.AccountRepo;
import org.pepsik.core.repositories.ProfileRepo;
import org.pepsik.core.services.AccountService;
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
    private AccountRepo accountRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findByUsername(data.getUsername());
        if (account != null) {
            throw new AccountExistsException();
        }
        Account createdAccount = accountRepo.create(data);
        Profile profile = new Profile();
        profile.setId(createdAccount.getId());
        profileRepo.create(profile);
        return createdAccount;
    }

    @Override
    public Account findAccountById(Long id) {
        return accountRepo.findById(id);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAll());
    }

    @Override
    public void updateAccount(Account data) {
        accountRepo.update(data);
    }

    @Override
    public void deleteAccount(Account data) {
        accountRepo.delete(data);
    }
}
