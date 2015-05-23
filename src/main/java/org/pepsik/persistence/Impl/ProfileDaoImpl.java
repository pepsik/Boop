package org.pepsik.persistence.Impl;

import org.pepsik.model.Profile;
import org.pepsik.persistence.ProfileDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pepsik on 5/23/15.
 */

@Repository
public class ProfileDaoImpl implements ProfileDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addProfile(Profile profile) {
        em.persist(profile);
    }

    @Override
    public Profile getProfile(long id) {
        return em.find(Profile.class, id);
    }

    @Override
    public void updateProfile(Profile profile) {
        em.merge(profile);
        em.flush();
    }

    @Override
    public void deleteProfile(String username) {
//         em.remove(getProfile(username));
    }

}
