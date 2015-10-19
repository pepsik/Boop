package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Profile;
import org.pepsik.core.repositories.ProfileRepo;
import org.pepsik.core.services.Reworked.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by pepsik on 10/19/2015.
 */
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public Profile createProfile(Profile data) {
        return profileRepo.create(data);
    }

    @Override
    public Profile findProfile(Long id) {
        return profileRepo.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canUpdateProfile(#id)")
    public Profile updateProfile(Long id, Profile data) {
        return profileRepo.update(id, data);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canUpdateProfile(#id)")
    public Profile deleteProfile(Long id) {
        return profileRepo.delete(id);
    }
}
