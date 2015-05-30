package org.pepsik.persistence;

import org.pepsik.model.Comment;

/**
 * Created by pepsik on 5/16/15.
 */
public interface CommentDao {

    void addComment(Comment post);

    Comment getCommentById(long id);

    void updateComment(Comment post);

    void deleteComment(long id);
}