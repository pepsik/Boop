package org.pepsik.core.repositories;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.models.entities.Post;
import org.pepsik.core.models.entities.Profile;

import java.util.List;

/**
 * Created by pepsik on 10/19/2015.
 */
public interface ProfileRepo {
    Profile create(Profile data);
    Profile findById(Long id);
    Profile update(Long id, Profile data);
    Profile delete(Long id);
    List<Post> getPosts(Account account, Integer requestedPage, Integer postsPerPage);
    Long getPostCount(Account account);
    List<Comment> getComments(Account account, Integer requestedPage, Integer postsPerPage);
    Long getCommentCount(Account account);
}
