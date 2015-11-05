package org.pepsik.rest.resources.asm;

import org.pepsik.core.models.entities.Comment;
import org.pepsik.rest.mvc.CommentController;
import org.pepsik.rest.resources.CommentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by pepsik on 10/3/2015.
 */
public class CommentResourceAsm extends ResourceAssemblerSupport<Comment, CommentResource> {

    public CommentResourceAsm() {
        super(CommentController.class, CommentResource.class);
    }

    @Override
    public CommentResource toResource(Comment comment) {
        CommentResource res = new CommentResource();
        res.setRid(comment.getId());
        res.setText(comment.getText());
        res.setWhen(comment.getWhen());
        res.setAuthor(comment.getOwner().getUsername());
        return res;
    }
}
