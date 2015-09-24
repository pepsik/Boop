package org.pepsik.rest.utilities;

import org.pepsik.core.models.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 9/22/2015.
 */
public class AccountList {

    private List<User> accounts = new ArrayList<>();

    public AccountList(List<User> accounts) {
        this.accounts = accounts;
    }

    public List<User> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<User> accounts) {
        this.accounts = accounts;
    }
}
