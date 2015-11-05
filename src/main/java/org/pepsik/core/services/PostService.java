package org.pepsik.core.services;

import org.pepsik.core.models.entities.Post;
import org.pepsik.rest.utilities.PostList;

/**
 * Created by pepsik on 9/30/2015.
 */
public interface PostService {
    Post createPost(Post data);
    Post findPostById(Long id);
    PostList findAllPosts();
    Post updatePost(Long postId, Post data);
    Post deletePost(Long postId);
    PostList findPostsByPage(Integer requestedPage);
    Long findAllPostCount();
}
