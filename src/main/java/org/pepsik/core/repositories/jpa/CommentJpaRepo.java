package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Comment;
import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.repositories.CommentRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class CommentJpaRepo implements CommentRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment create(Long postId, Comment data) {
        Post post = em.find(Post.class, postId);
        data.setPost(post);
        data.setWhen(LocalDateTime.now());
        em.persist(data);
        return data;
    }

    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> findCommentsByPost(Long id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            return em.createQuery("SELECT c FROM Comment c where c.post = :post order by c.when asc")
                    .setParameter("post", post).getResultList();
        } else {
            return null;
        }
    }

    @Override
    public Comment update(Long id, Comment data) {
        Comment comment = em.find(Comment.class, id);
        comment.setText(data.getText());
        return comment;
    }

    @Override
    public Comment delete(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
        return comment;
    }
}
