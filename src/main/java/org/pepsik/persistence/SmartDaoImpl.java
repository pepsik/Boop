package org.pepsik.persistence;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.pepsik.model.*;
import org.pepsik.model.Post;
import org.pepsik.model.Account;
import org.pepsik.model.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by pepsik on 4/8/15.
 */

@Repository
public class SmartDaoImpl implements SmartDao {


    public static final String SELECT_ACCOUNT_BY_ID = "SELECT account FROM Account account WHERE account.id=:id";
    public static final String SELECT_ALL_POSTS = "SELECT post FROM Post post ORDER BY post.when DESC";
    public static final String SELECT_POST_BY_ID = "SELECT post FROM Post post WHERE post.id =:id";
    public static final String SELECT_COMMENT_BY_ID = "SELECT comment FROM Comment comment WHERE comment.id = :id";
    public static final String SELECT_ACCOUNT_BY_USERNAME = "SELECT account FROM Account account WHERE account.username=:username";
    public static final String INSERT_ACCOUNT_AUTHORITY = "INSERT INTO ACCOUNTS_AUTHORITY (account_id, ROLE_ID)  values (:id , 2)";
    public static final String SELECT_PROFILE_BY_USERNAME = "SELECT profile FROM Profile profile WHERE profile.id = :id";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Cacheable(cacheName = "postCache")
    public List<Post> getAllPosts() {
        return em.createQuery(SELECT_ALL_POSTS).getResultList();
    }

    @Override
    @Cacheable(cacheName = "postCache")
    public List<Post> getPostsByPage(int pageIndex, final int DEFAULT_POSTS_PER_PAGE) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> from = criteriaQuery.from(Post.class);
        CriteriaQuery<Post> select = criteriaQuery.orderBy(criteriaBuilder.desc(from.get("when")));

        TypedQuery<Post> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageIndex - 1) * DEFAULT_POSTS_PER_PAGE);
        typedQuery.setMaxResults(DEFAULT_POSTS_PER_PAGE);

        return typedQuery.getResultList();
    }

    @Override
    @Cacheable(cacheName = "postCache")
    public long getPostCount() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Post.class)));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "accountCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator",
            properties = @Property(name = "includeMethod", value = "false")))
    public void addAccount(Account account) {
        em.persist(account);
    }

    @Override
    @Cacheable(cacheName = "accountCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator",
            properties = @Property(name = "includeMethod", value = "false")))
    public Account getAccountById(long id) {
        return (Account) em.createQuery(SELECT_ACCOUNT_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    @Cacheable(cacheName = "accountCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator",
            properties = @Property(name = "includeMethod", value = "false")))
    public Account getAccountByUsername(String username) {
        return (Account) em.createQuery(SELECT_ACCOUNT_BY_USERNAME).setParameter("username", username).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "accountCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator",
            properties = @Property(name = "includeMethod", value = "false")))
    public void updateAccount(Account account) {
        em.merge(account);
        em.flush();
    }

    @Override
    public void deleteAccount(long id) {
//        em.remove(getAccountById(id));
//        em.getReference();
    }

    @Override
    public void addProfile(Profile profile) {
        em.persist(profile);
    }

    @Override
    public Profile getProfile(long id) {
        return (Profile) em.createQuery(SELECT_PROFILE_BY_USERNAME).setParameter("id", id).getSingleResult();
    }

    @Override
    public void updateProfile(Profile profile) {
        em.merge(profile);
        em.flush();
    }

    @Override
    public void deleteProfile(String username) {
//         em.remove(getProfile(username));
    }

    @Override
    public void setAccountAuthory(Account account) {
        em.createNativeQuery(INSERT_ACCOUNT_AUTHORITY).setParameter("id", account.getId()).executeUpdate();
    }

    @Override
    @TriggersRemove(cacheName = "postCache", removeAll = true)
    public void addPost(Post post) {
        em.persist(post);
    }

    @Override
    @Cacheable(cacheName = "postCache")
    public Post getPostById(long id) {
        return (Post) em.createQuery(SELECT_POST_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "postCache", removeAll = true)
    public void updatePost(Post post) {
        em.merge(post);
        em.flush();
    }

    @Override
    @TriggersRemove(cacheName = "postCache", removeAll = true)
    public void deletePost(long id) {
        em.remove(getPostById(id));
        em.flush();
    }

    @Override
    @TriggersRemove(cacheName = "commentCache", removeAll = true)
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    @Cacheable(cacheName = "commentCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator",
            properties = @Property(name = "includeMethod", value = "false")))
    public Comment getCommentById(long id) {
        return (Comment) em.createQuery(SELECT_COMMENT_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "commentCache", removeAll = true)
    public void updateComment(Comment comment) {
        em.merge(comment);
        em.flush();
    }

    @Override
    @TriggersRemove(cacheName = "commentCache", removeAll = true)
    public void deleteComment(long id) {
        em.remove(getCommentById(id));
        em.flush();
    }
}
