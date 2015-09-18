package org.pepsik.core.persistence;

import org.pepsik.core.models.entities.Favorite;

import java.util.List;

/**
 * Created by pepsik on 5/27/15.
 */
public interface FavoriteDao {

    void addFavorite(Favorite favorite);

    List<Favorite> getAllFavorites(long userId);

    void deleteFavorite(long postId, long userId);
}
