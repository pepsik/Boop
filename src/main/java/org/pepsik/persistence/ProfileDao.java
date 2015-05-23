package org.pepsik.persistence;

import org.pepsik.model.Profile;

/**
 * Created by pepsik on 5/23/15.
 */
public interface ProfileDao {

    void addProfile(Profile profile);

    Profile getProfile(long id);

    void updateProfile(Profile profile);

    void deleteProfile(String username);

}
