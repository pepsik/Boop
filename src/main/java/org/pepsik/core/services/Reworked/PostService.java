package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.rest.utilities.PostList;

import java.util.List;

/**
 * Created by pepsik on 9/30/2015.
 */
public interface PostService {
    Post createPost(String author, Post data);

    Post findPostById(Long id);

    PostList findAllPosts();

    Post updatePost(Long postId, Post data);

    Post deletePost(Long postId);
}
