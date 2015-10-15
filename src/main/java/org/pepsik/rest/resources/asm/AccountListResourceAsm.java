package org.pepsik.rest.resources.asm;

import org.pepsik.rest.mvc.reworked.AccountController;
import org.pepsik.rest.resources.AccountListResource;
import org.pepsik.rest.resources.AccountResource;
import org.pepsik.rest.utilities.AccountList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by pepsik on 9/22/2015.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
