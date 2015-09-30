package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Post;

import java.util.List;

/**
 * Created by pepsik on 9/30/2015.
 */
public interface PostService {
    Post createPost(Post data);

    Post findPostById(Long id);

    List<Post> findAllPosts();

    void updatePost(Post data);

    void deletePost(Post data);
}
