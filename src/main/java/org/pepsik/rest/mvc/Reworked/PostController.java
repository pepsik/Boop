package org.pepsik.rest.mvc.Reworked;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.resources.PostResource;
import org.pepsik.rest.resources.asm.PostResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pepsik on 9/30/2015.
 */

@Controller
@RequestMapping(value = "/rest/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public ResponseEntity<PostResource> getPost(@PathVariable Long postId) {
        Post post = postService.findPostById(postId);
        if (post != null) {
            PostResource res = new PostResourceAsm().toResource(post);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
