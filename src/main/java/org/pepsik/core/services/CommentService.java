package org.pepsik.core.services;

import org.pepsik.core.models.entities.Comment;
import org.pepsik.rest.utilities.CommentList;

/**
 * Created by pepsik on 10/3/2015.
 */
public interface CommentService {
    Comment createComment(Long postid, Comment data);

    CommentList findAllCommentsByPost(Long postId);

    Comment findCommentById(Long id);

    Comment updateComment(Long commentId, Long postId, Comment data);

    Comment deleteComment(Long commentId, Long postId);
}
