package org.pepsik.core.repositories;

import org.pepsik.core.models.entities.Reworked.Post;

import java.util.List;

/**
 * Created by pepsik on 10/11/2015.
 */
public interface PostRepo {
    Post create(Post data);

    Post findById(Long id);

    List<Post> findAll();

    Post update(Long postId, Post data);

    Post delete(Long postId);
}
