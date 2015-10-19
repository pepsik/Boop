package org.pepsik.rest.resources.asm;

import org.pepsik.core.models.entities.Reworked.Profile;
import org.pepsik.rest.mvc.reworked.ProfileController;
import org.pepsik.rest.resources.ProfileResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by pepsik on 10/19/2015.
 */
public class ProfileResourceAsm extends ResourceAssemblerSupport<Profile, ProfileResource> {

    public ProfileResourceAsm() {
        super(ProfileController.class, ProfileResource.class);
    }

    @Override
    public ProfileResource toResource(Profile data) {
        ProfileResource res = new ProfileResource();
        res.setAbout(data.getAbout());
        res.setBirthdate(data.getBirthdate());
        res.setCountry(data.getCountry());
        res.setCity(data.getCity());
        res.setEmail(data.getEmail());
        res.setFirstname(data.getFirstname());
        res.setGender(data.getGender());
        res.setJob(data.getJob());
        res.setLastname(data.getLastname());
        res.add(linkTo(methodOn(ProfileController.class).getProfile(data.getAccount_id())).withSelfRel());
        return res;
    }
}
