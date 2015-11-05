package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.models.entities.Post;
import org.pepsik.core.repositories.CommentRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

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
        if (post != null) {
            data.setPost(post);
            data.setWhen(LocalDateTime.now());
            em.persist(data);
            return data;
        } else {
            return null;
        }
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
    public Comment update(Long commentId, Long postId, Comment data) {
        Comment comment = em.find(Comment.class, commentId);
        if (comment != null) {
            if (comment.getPost().getId() == postId) {
                comment.setText(data.getText());
            } else {
                return null;
            }
        }
        return comment;
    }

    @Override
    public Comment delete(Long commentId, Long postId) {
        Comment comment = em.find(Comment.class, commentId);
        if (comment != null) {
            if (comment.getPost().getId() == postId) {
                em.remove(comment);
            } else {
                return null;
            }
        }
        return comment;
    }
}
