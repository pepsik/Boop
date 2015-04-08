package org.pepsik.model;


import java.util.List;

public class Thread extends MessageEntity {

    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
