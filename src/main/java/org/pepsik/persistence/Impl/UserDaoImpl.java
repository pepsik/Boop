package org.pepsik.persistence.Impl;

import org.pepsik.model.Comment;
import org.pepsik.model.Favorite;
import org.pepsik.model.Post;
import org.pepsik.model.User;
import org.pepsik.persistence.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by pepsik on 5/23/15.
 */

@Repository
public class UserDaoImpl implements UserDao {

    public static final String SELECT_USER_BY_USERNAME = "SELECT user FROM User user WHERE user.username=:username";
    public static final String INSERT_USER_AUTHORITY = "INSERT INTO users_authority (user_id_fk, role_id_fk)  values (:id , 2)";
    public static final String REMOVE_USER_TOKENS_SQL = "delete from persistent_logins where username = :username";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) em.createQuery(SELECT_USER_BY_USERNAME).setParameter("username", username).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUserAuthority(User user) {
        em.createNativeQuery(INSERT_USER_AUTHORITY).setParameter("id", user.getId()).executeUpdate();
    }

    @Override
    public List<Post> getUserPosts(User user, int pageIndex, int postsPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> q = cb.createQuery(Post.class);
        Root<Post> from = q.from(Post.class);
        CriteriaQuery<Post> select = q.select(from).where(cb.equal(from.get("user").get("id"), user.getId())).orderBy(cb.desc(from.get("when")));
        TypedQuery<Post> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageIndex - 1) * postsPerPage);
        typedQuery.setMaxResults(postsPerPage);
        return typedQuery.getResultList();
    }

    @Override
    public long getUserPostCount(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Post> from = countQuery.from(Post.class);
        countQuery.select(criteriaBuilder.count(from)).where(criteriaBuilder.equal(from.get("user").get("id"), user.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public List<Comment> getUserComments(User user, int pageIndex, int commentsPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comment> q = cb.createQuery(Comment.class);
        Root<Comment> from = q.from(Comment.class);
        CriteriaQuery<Comment> select = q.select(from).where(cb.equal(from.get("user").get("id"), user.getId())).orderBy(cb.desc(from.get("when")));
        TypedQuery<Comment> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageIndex - 1) * commentsPerPage);
        typedQuery.setMaxResults(commentsPerPage);
        return typedQuery.getResultList();
    }

    @Override
    public long getUserCommentsCount(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Comment> from = countQuery.from(Comment.class);
        countQuery.select(criteriaBuilder.count(from)).where(criteriaBuilder.equal(from.get("user").get("id"), user.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public List<Favorite> getUserFavorites(User user, int pageIndex, int favoritesPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Favorite> q = cb.createQuery(Favorite.class);
        Root<Favorite> from = q.from(Favorite.class);
        CriteriaQuery<Favorite> select = q.select(from).where(cb.equal(from.get("user").get("id"), user.getId())).orderBy(cb.desc(from.get("addedDate")));
        TypedQuery<Favorite> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageIndex - 1) * favoritesPerPage);
        typedQuery.setMaxResults(favoritesPerPage);
        return typedQuery.getResultList();
    }

    @Override
    public long getUserFavoritesCount(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Favorite> from = countQuery.from(Favorite.class);
        countQuery.select(criteriaBuilder.count(from)).where(criteriaBuilder.equal(from.get("user").get("id"), user.getId()));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    public void deleteUserPersistenceRememberMeTokens(String username) {
        em.createNativeQuery("delete from persistent_logins where username = ?").setParameter(1, username).executeUpdate();
    }
}
