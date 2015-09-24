package org.pepsik.rest.resources.asm;

import org.pepsik.core.models.entities.User;
import org.pepsik.rest.mvc.UserController;
import org.pepsik.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by pepsik on 9/22/2015.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<User, AccountResource> {

    public AccountResourceAsm() {
        super(UserController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(User account) {
        AccountResource res = new AccountResource();
        res.setUsername(account.getUsername());
//        res.setPassword(account.getUserPassword().getPassword());
        res.setRid(account.getId());
        res.add(linkTo(methodOn(UserController.class).getAccount(account.getId())).withSelfRel());
        return res;
    }
}
