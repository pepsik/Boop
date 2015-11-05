package org.pepsik.rest.utilities;

import org.pepsik.core.models.entities.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 10/3/2015.
 */
public class CommentList {
    private List<Comment> comments = new ArrayList<>();

    public CommentList(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
