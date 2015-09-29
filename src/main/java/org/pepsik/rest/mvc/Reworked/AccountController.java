package org.pepsik.rest.mvc.Reworked;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.services.Reworked.AccountService;
import org.pepsik.rest.resources.AccountListResource;
import org.pepsik.rest.resources.AccountResource;
import org.pepsik.rest.resources.asm.AccountListResourceAsm;
import org.pepsik.rest.resources.asm.AccountResourceAsm;
import org.pepsik.rest.utilities.AccountList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by pepsik on 9/29/2015.
 */
@RequestMapping(value = "/rest/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> getAccounts(@RequestParam(required = false) String login) {
        AccountList list;
        if (login == null) {
            list = new AccountList(accountService.getAllAccounts());
        } else {
            list = new AccountList(new ArrayList<>());
            Account account = accountService.findAccountByLogin(login);
            if (account != null)
                list.setAccounts(Collections.singletonList(account));
        }
        AccountListResource resources = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        logger.debug("Attempt to create account with login " + sentAccount.getLogin() + " and password " + sentAccount.getPassword());
        accountService.createAccount(sentAccount.toAccount());
        logger.debug("User successful created!");
        return new ResponseEntity<>(sentAccount, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        if (account != null) {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
