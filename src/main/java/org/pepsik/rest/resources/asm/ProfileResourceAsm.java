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
    public ProfileResource toResource(Profile profile) {
        ProfileResource res = new ProfileResource();
        res.setAbout(profile.getAbout());
        res.setBirthdate(profile.getBirthdate());
        res.setCountry(profile.getCountry());
        res.setCity(profile.getCity());
        res.setEmail(profile.getEmail());
        res.setFirstname(profile.getFirstname());
        res.setGender(profile.getGender());
        res.setJob(profile.getJob());
        res.setLastname(profile.getLastname());
        res.add(linkTo(methodOn(ProfileController.class).getPublicProfile(profile.getOwner().getUsername())).withSelfRel());
        return res;
    }
}
