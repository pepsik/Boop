package org.pepsik.core.persistence;

import org.pepsik.core.models.entities.Profile;

/**
 * Created by pepsik on 5/23/15.
 */
public interface ProfileDao {

    void addProfile(Profile profile);

    Profile getProfile(long id);

    void updateProfile(Profile profile);

    void deleteProfile(String username);

}
