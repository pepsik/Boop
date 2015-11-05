package org.pepsik.rest.mvc.reworked;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.models.entities.util.PaginationSupport;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.resources.PostListResource;
import org.pepsik.rest.resources.PostResource;
import org.pepsik.rest.resources.asm.PostListResourceAsm;
import org.pepsik.rest.resources.asm.PostResourceAsm;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pepsik on 9/30/2015.
 */

@Controller
@RequestMapping(value = "/rest/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostResource> createPost(@RequestBody PostResource sentPost) {
        Post createdPost = postService.createPost(sentPost.toPost());
        PostResource res = new PostResourceAsm().toResource(createdPost);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PaginationSupport> getPostCount() {
        PaginationSupport support = new PaginationSupport(postService.findAllPostCount());
        return new ResponseEntity<>(support, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = "page")
    public ResponseEntity<PostListResource> getPostsByPage(@RequestParam("page") Integer page) {
        PostList postList = postService.findPostsByPage(page);
        PostListResource res = new PostListResourceAsm().toResource(postList);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.PUT)
    public ResponseEntity<PostResource> updatePost(@PathVariable Long postId, @RequestBody PostResource sentPost) {
        Post updatedPost = postService.updatePost(postId, sentPost.toPost());
        if (updatedPost != null) {
            PostResource res = new PostResourceAsm().toResource(updatedPost);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<PostResource> deletePost(@PathVariable Long postId) {
        Post post = postService.deletePost(postId);
        if (post != null) {
            PostResource res = new PostResourceAsm().toResource(post);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
