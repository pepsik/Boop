package org.pepsik.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 9/22/2015.
 */
public class AccountListResource extends ResourceSupport {
    private List<AccountResource> accounts = new ArrayList<>();

    public List<AccountResource> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResource> accounts) {
        this.accounts = accounts;
    }
}
