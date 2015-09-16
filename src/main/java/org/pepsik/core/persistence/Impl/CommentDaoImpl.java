package org.pepsik.core.persistence.Impl;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.pepsik.core.model.Comment;
import org.pepsik.core.persistence.CommentDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by pepsik on 5/23/15.
 */
@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @TriggersRemove(cacheName = "commentCache", removeAll = true)
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    @Cacheable(cacheName = "commentCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator",
            properties = @Property(name = "includeMethod", value = "false")))
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    @TriggersRemove(cacheName = "commentCache", removeAll = true)
    public void updateComment(Comment comment) {
        em.merge(comment);
    }

    @Override
    @TriggersRemove(cacheName = "commentCache", removeAll = true)
    public void deleteComment(long id) {
        em.remove(getCommentById(id));
    }
}
