package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class PostJpaRepo implements PostRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Post create(Post data) {
        em.persist(data);
        return data;
    }

    @Override
    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    @Override
    public List<Post> findAll() {
        Query query = em.createQuery("SELECT a FROM Post a order by a.when desc");
        return query.getResultList();
    }

    @Override
    public Post update(Long id, Post data) {
        Post post = em.find(Post.class, id);
        post.setText(data.getText());
        post.setTitle(data.getTitle());
        return post;
    }

    @Override
    public Post delete(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
        return post;
    }
}
