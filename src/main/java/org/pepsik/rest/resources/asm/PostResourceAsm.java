package org.pepsik.rest.resources.asm;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.rest.mvc.Reworked.PostController;
import org.pepsik.rest.resources.PostResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by pepsik on 9/30/2015.
 */
public class PostResourceAsm extends ResourceAssemblerSupport<Post, PostResource> {

    public PostResourceAsm() {
        super(PostController.class, PostResource.class);
    }

    @Override
    public PostResource toResource(Post post) {
        PostResource res = new PostResource();
        res.setTitle(post.getTitle());
        res.setText(post.getText());
        res.setAuthor(post.getOwner().getUsername());
        res.setWhen(post.getWhen());
        res.setRid(post.getId());
        res.add(linkTo(methodOn(PostController.class).getPost(post.getId())).withSelfRel());
        return res;
    }
}
