package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Profile;

/**
 * Created by pepsik on 10/19/2015.
 */
public interface ProfileService {
    Profile createProfile(Profile data);

    Profile findProfileById(Long id);

    Profile findProfileByUsername(String username);

    Profile updateProfile(Profile data);

    Profile deleteProfile(Long id);
}
