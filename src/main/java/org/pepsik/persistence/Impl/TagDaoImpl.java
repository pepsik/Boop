package org.pepsik.persistence.Impl;

import org.pepsik.model.Tag;
import org.pepsik.persistence.TagDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pepsik on 5/26/15.
 */
@Repository
public class TagDaoImpl implements TagDao {

    public static final String SELECT_TAG_BY_NAME = "SELECT tag FROM Tag tag where tag.name = :name";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createTag(Tag tag) {

    }

    @Override
    public Tag getTag(String tagName) {
        return (Tag) em.createQuery(SELECT_TAG_BY_NAME).setParameter("name", tagName).getSingleResult();
    }

    @Override
    public void updateTag(Tag tag) {

    }

    @Override
    public void deleteTag(Tag tag) {

    }
}
