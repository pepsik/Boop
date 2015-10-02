package org.pepsik.rest.mvc.Reworked;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.resources.PostListResource;
import org.pepsik.rest.resources.PostResource;
import org.pepsik.rest.resources.asm.PostListResourceAsm;
import org.pepsik.rest.resources.asm.PostResourceAsm;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
//        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails details = (UserDetails)principal;
//        String loggedInUsername = details.getUsername();
        Post createdPost = postService.createPost("username3", sentPost.toPost());
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
    public ResponseEntity<PostListResource> getAllPosts() { //temp endpoint
        PostList postList = postService.findAllPosts();
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
