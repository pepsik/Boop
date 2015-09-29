package org.pepsik.rest.utilities;

import org.pepsik.core.models.entities.Reworked.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 9/22/2015.
 */
public class AccountList {

    private List<Account> accounts = new ArrayList<>();

    public AccountList(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
