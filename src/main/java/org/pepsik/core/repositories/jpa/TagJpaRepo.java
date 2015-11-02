package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.models.entities.Reworked.Tag;
import org.pepsik.core.repositories.TagRepo;
import org.pepsik.rest.utilities.PostList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pepsik on 10/27/2015.
 */
@Repository
public class TagJpaRepo implements TagRepo {
    public static final String SELECT_TAG_BY_NAME = "SELECT tag FROM Tag tag where tag.name = :name";

    @PersistenceContext
    private EntityManager em;

    @Override
    public Tag create(Tag data) {
        return null;
    }

    @Override
    public Tag find(String name) {
        List<Tag> results = em.createQuery(SELECT_TAG_BY_NAME).setParameter("name", name).getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();
    }

    @Override
    public Tag findPosts(String name) {
        Tag tag = (Tag) em.createQuery(SELECT_TAG_BY_NAME).setParameter("name", name).getSingleResult();
        return tag;
    }

    @Override
    public Tag update(String name, Tag data) {
        Tag tag = (Tag) em.createQuery(SELECT_TAG_BY_NAME).setParameter("name", name).getSingleResult();
        if (tag != null){
            tag.setDescription(data.getDescription());
            tag.setImageUrl(data.getImageUrl());
        }
        return tag;
    }

    @Override
    public Tag delete(String name) {
        Tag tag = (Tag) em.createQuery(SELECT_TAG_BY_NAME).setParameter("name", name).getSingleResult();
        if (tag != null){
            em.remove(tag);
        }
        return tag;
    }
}
