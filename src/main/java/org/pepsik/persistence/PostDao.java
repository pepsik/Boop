package org.pepsik.persistence;

import org.pepsik.model.Post;
import org.pepsik.model.Tag;

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

    Tag getTag(String name);
}
