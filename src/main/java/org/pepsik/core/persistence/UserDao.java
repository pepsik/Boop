package org.pepsik.core.persistence;

import org.pepsik.core.models.entities.Comment;
import org.pepsik.core.models.entities.Favorite;
import org.pepsik.core.models.entities.Post;
import org.pepsik.core.models.entities.User;

import java.util.List;

/**
 * Created by pepsik on 5/16/15.
 */
public interface UserDao {

    void addUser(User user);

    User getUserById(long id);

    User getUserByUsername(String username);

    void updateUser(User user);

    void deleteUser(long id);

    void setUserAuthority(User user);

    List<Post> getUserPosts(User user, int pageIndex, int postsPerPage);

    List<Comment> getUserComments(User user, int pageIndex, int commentsPerPage);

    List<Favorite> getUserFavorites(User user, int pageIndex, int favoritesPerPage);

    long getUserFavoritesCount(User user);

    long getUserPostCount(User user);

    long getUserCommentsCount(User user);

    void deleteUserPersistenceRememberMeTokens(String username);
}
