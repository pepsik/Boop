package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Profile;
import org.pepsik.core.repositories.ProfileRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pepsik on 10/19/2015.
 */
@Repository
public class ProfileJpaRepo implements ProfileRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Profile create(Profile data) {
        em.persist(data);
        return data;
    }

    @Override
    public Profile findById(Long id) {
        return em.find(Profile.class, id);
    }

    @Override
    public Profile update(Long id, Profile data) {
        Profile profile = em.find(Profile.class, id);
        if (profile != null) {
            profile.setAbout(data.getAbout());
            profile.setBirthdate(data.getBirthdate());
            profile.setCountry(data.getCountry());
            profile.setCity(data.getCity());
            profile.setEmail(data.getEmail());
            profile.setFirstname(data.getFirstname());
            profile.setGender(data.getGender());
            profile.setJob(data.getJob());
            profile.setLastname(data.getLastname());
        }
        return profile;
    }

    @Override
    public Profile delete(Long id) {
        Profile profile = em.find(Profile.class, id);
        if (profile != null) {
            em.remove(profile);
        }
        return profile;
    }
}
