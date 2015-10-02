package org.pepsik.rest.utilities;

import org.pepsik.core.models.entities.Reworked.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 10/1/2015.
 */
public class PostList {
    private List<Post> posts = new ArrayList<>();

    public PostList(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
