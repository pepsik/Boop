package org.pepsik.core.repositories;

import org.pepsik.core.models.entities.Reworked.Comment;

import java.util.List;

/**
 * Created by pepsik on 10/11/2015.
 */
public interface CommentRepo {
    Comment create(Long postId, Comment data);

    Comment findById(Long id);

    List<Comment> findCommentsByPost(Long id);

    Comment update(Long id, Comment data);

    Comment delete(Long id);
}
