package org.pepsik.core.persistence.Impl;

import org.pepsik.core.model.Favorite;
import org.pepsik.core.persistence.FavoriteDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by pepsik on 5/27/15.
 */

@Repository
public class FavoriteDaoImpl implements FavoriteDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addFavorite(Favorite favorite) {
        em.persist(favorite);
    }

    @Override
    public List<Favorite> getAllFavorites(long id) {
        return em.createQuery("SELECT favorite from Favorite favorite where favorite.user_id_fk = :user_id").setParameter("user_id", id).getResultList();
    }

    @Override
    public void deleteFavorite(long postId, long userId) {
        Favorite favorite = (Favorite) em.createQuery("select favorite from Favorite favorite where favorite.user.id = :user_id AND favorite.post.id = :post_id").
                setParameter("user_id", userId).setParameter("post_id", postId).getSingleResult();

        em.remove(favorite);
    }
}
