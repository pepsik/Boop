package org.pepsik.core.services;

import org.pepsik.core.models.entities.*;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */
public interface SmartService {

    List<Post> getAllPosts();

    List<Post> getPostsByPage(int pageIndex);

    void saveFavorite(long postId);

    void removeFavorite(long id);

    List<String> getPagination(int pageIndex, long postCount);

    long getAllPostsCount();

    long getPagesCount(long postCount);

    boolean isExistPost(long id);

    User getUser(long id);

    User getUser(String username);

    void saveUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    List<Post> getUserPosts(String username, int page);

    long getUserPostsCount(String username);

    List<Comment> getUserComments(String username, int page);

    long getUserCommentsCount(String username);

//    List<Favorite> getAllUserFavorites(String username);

    List<Favorite> getUserFavorites(String username, int page);

    long getUserFavoritesCount(String username);

    boolean isExistUsername(String username);

    boolean isExistUsername(long id);

    void saveProfile(Profile profile);

    Profile getProfile(String username);

    void deleteProfile(String username);

    Post getPost(long id);

    void savePost(Post thread);

    void deletePost(long id);

    Comment getComment(long id);

    void saveComment(Comment message);

    void deleteComment(long id);

    boolean isExistComment(long id);

    void saveTag(Tag tag);

    Tag getTag(String name);

    boolean isExistTag(String tag);

    void sendPrivateMessage(PrivateMessage message);

    List<PrivateMessage> getOutputPrivateMessages();

    List<PrivateMessage> getInputPrivateMessages();

    List<Post> getSimilarPosts(String name);

    long getInputPMCount();

    long getOutputPMCount();

    void removeUserRememberMeTokens(String username);
}