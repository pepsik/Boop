package org.pepsik.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 10/1/2015.
 */
public class PostListResource extends ResourceSupport {
    private List<PostResource> posts = new ArrayList<>();

    public List<PostResource> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResource> posts) {
        this.posts = posts;
    }
}
