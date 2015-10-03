package org.pepsik.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 10/3/2015.
 */
public class CommentListResource extends ResourceSupport {
    private List<CommentResource> comments = new ArrayList<>();

    public List<CommentResource> getComments() {
        return comments;
    }

    public void setComments(List<CommentResource> comments) {
        this.comments = comments;
    }
}
