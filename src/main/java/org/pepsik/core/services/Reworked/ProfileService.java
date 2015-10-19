package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Profile;

/**
 * Created by pepsik on 10/19/2015.
 */
public interface ProfileService {
    Profile createProfile(Profile data);

    Profile findProfile(Long id);

    Profile updateProfile(Long id, Profile data);

    Profile deleteProfile(Long id);
}
