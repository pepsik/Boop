package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Profile;
import org.pepsik.core.repositories.AccountRepo;
import org.pepsik.core.repositories.ProfileRepo;
import org.pepsik.core.security.AccountUserDetails;
import org.pepsik.core.services.Reworked.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pepsik on 10/19/2015.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Profile createProfile(Profile data) {
        return profileRepo.create(data);
    }

    @Override
    public Profile findProfileById(Long id) {
        return profileRepo.findById(id);
    }

    @Override
    public Profile findProfileByUsername(String username) {
        Account account = accountRepo.findByUsername(username);
        Profile profile = null;
        if (account != null) {
            profile = profileRepo.findById(account.getId());
        }
        return profile;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Profile updateProfile(Profile data) {
        String loggedIn = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Account account = accountRepo.findByUsername(loggedIn);
        return profileRepo.update(account.getId(), data);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canUpdateProfile(#id)")
    public Profile deleteProfile(Long id) {
        return profileRepo.delete(id);
    }
}
