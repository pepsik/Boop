package org.pepsik.core.repositories;

import org.pepsik.core.models.entities.Reworked.Profile;

/**
 * Created by pepsik on 10/19/2015.
 */
public interface ProfileRepo {
    Profile create(Profile data);
    Profile findById(Long id);
    Profile update(Long id, Profile data);
    Profile delete(Long id);
}
