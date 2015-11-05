package org.pepsik.core.services.Reworked;

import org.pepsik.core.models.entities.Reworked.Profile;
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

    PostList getUserPostsByPage(String username, Integer page);

    Long getUserPostsCount(String username);

    CommentList getUserCommentsByPage(String username, Integer page);

    Long getUserCommentsCount(String username);

    PostList getUserFavorites(String username, Integer page);

    Long getUserFavoritesCount(String username);
}
