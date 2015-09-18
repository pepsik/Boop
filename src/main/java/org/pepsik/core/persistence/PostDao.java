package org.pepsik.core.persistence;

import org.pepsik.core.models.entities.Post;
import org.pepsik.core.models.entities.support.PostLabel;

import java.util.List;

/**
 * Created by pepsik on 5/16/15.
 */
public interface PostDao {

    List getAllPosts();

    List<Post> getPostsByPage(int pageIndex, int threadsPerPage);

    long getPostCount();

    void addPost(Post post);

    Post getPostById(long id);

    void updatePost(Post post);

    void deletePost(long id);

    List<PostLabel> getSimilarPosts(String name);
}
