package org.pepsik.core.services;

import org.pepsik.core.models.entities.Profile;
import org.pepsik.rest.utilities.CommentList;
import org.pepsik.rest.utilities.PostList;

/**
 * Created by pepsik on 10/19/2015.
 */
public interface ProfileService {
    Profile createProfile(Profile data);

    Profile findProfileById(Long id);

    Profile findProfileByUsername(String username);

    Profile updateProfile(Profile data);

    Profile deleteProfile(Long id);

    PostList findUserPostsByPage(String username, Integer page);

    Long findUserPostsCount(String username);

    CommentList findUserCommentsByPage(String username, Integer page);

    Long findUserCommentsCount(String username);

    PostList findUserFavorites(String username, Integer page);

    Long findUserFavoritesCount(String username);
}
