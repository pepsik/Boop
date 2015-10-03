package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Comment;
import org.pepsik.rest.utilities.CommentList;

/**
 * Created by pepsik on 10/3/2015.
 */
public interface CommentService {
    Comment createComment(Long postid, String author, Comment data);

    CommentList findAllCommentsByPost(Long postId);

    Comment updateComment(Long commentId, Comment data);

    Comment deleteComment(Long commentId);
}
