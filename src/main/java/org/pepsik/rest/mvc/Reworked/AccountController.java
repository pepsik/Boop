package org.pepsik.rest.mvc.Reworked;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.services.Reworked.AccountService;
import org.pepsik.core.services.exceptions.AccountExistsException;
import org.pepsik.rest.resources.AccountListResource;
import org.pepsik.rest.resources.AccountResource;
import org.pepsik.rest.resources.asm.AccountListResourceAsm;
import org.pepsik.rest.resources.asm.AccountResourceAsm;
import org.pepsik.rest.utilities.AccountList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping(value = "/rest/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        logger.debug("Attempt to create account with username " + sentAccount.getUsername() + " and password " + sentAccount.getPassword());
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            logger.debug("User successful created!");
            return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
        } catch (AccountExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> getAccounts(@RequestParam(required = false) String username) {
        AccountList list;
        if (username == null) {
            list = accountService.findAllAccounts();
        } else {
            list = new AccountList(new ArrayList<>());
            Account account = accountService.findAccountByUsername(username);
            if (account != null)
                list.setAccounts(Collections.singletonList(account));
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        AccountListResource resources = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<>(resources, HttpStatus.OK);
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
