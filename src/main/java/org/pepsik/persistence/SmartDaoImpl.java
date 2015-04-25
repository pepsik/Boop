package org.pepsik.persistence;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.pepsik.model.*;
import org.pepsik.model.Thread;
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
    public static final String SELECT_ALL_THREADS = "SELECT thread FROM Thread thread ORDER BY thread.when DESC";
    public static final String SELECT_THREAD_BY_ID = "SELECT thread FROM Thread thread WHERE thread.id =:id";
    public static final String SELECT_POST_BY_ID = "SELECT post FROM Post post WHERE post.id = :id";
    public static final String SELECT_ACCOUNT_BY_USERNAME = "SELECT account FROM Account account WHERE account.username=:username";
    public static final String INSERT_ACCOUNT_AUTHORITY = "INSERT INTO ACCOUNTS_AUTHORITY (account_id, ROLE_ID)  values (:id , 2)";

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Thread> getAllThreads() {
        return em.createQuery(SELECT_ALL_THREADS).getResultList();
    }

    @Override
    public List<Thread> getThreadsByPage(int pageIndex, final int DEFAULT_THREADS_PER_PAGE) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Thread> criteriaQuery = criteriaBuilder.createQuery(Thread.class);
        Root<Thread> from = criteriaQuery.from(Thread.class);
        CriteriaQuery<Thread> select = criteriaQuery.orderBy(criteriaBuilder.desc(from.get("when")));

        TypedQuery<Thread> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult((pageIndex - 1) * DEFAULT_THREADS_PER_PAGE);
        typedQuery.setMaxResults(DEFAULT_THREADS_PER_PAGE);

        return typedQuery.getResultList();
    }

    @Override
    public long getThreadCount() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Thread.class)));
        return em.createQuery(countQuery).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "accountCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public void addAccount(Account account) {
        em.persist(account);
    }

    @Override
    @Cacheable(cacheName = "accountCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public Account getAccountById(long id) {
        return (Account) em.createQuery(SELECT_ACCOUNT_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    @Cacheable(cacheName = "accountCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public Account getAccountByUsername(String username) {
        return (Account) em.createQuery(SELECT_ACCOUNT_BY_USERNAME).setParameter("username", username).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "accountCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    public void deleteAccount(long id) {
//        em.remove(getAccountById(id));
//        em.getReference();
    }

    @Override
    public void setAccountAuthory(Account account) {
        em.createNativeQuery(INSERT_ACCOUNT_AUTHORITY).setParameter("id", account.getId()).executeUpdate();
    }

    @Override
    @TriggersRemove(cacheName = "threadCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public void addThread(Thread thread) {
        em.persist(thread);
    }

    @Override
    @Cacheable(cacheName = "threadCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public Thread getThreadById(long id) {
        return (Thread) em.createQuery(SELECT_THREAD_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "threadCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public void updateThread(Thread thread) {
        em.merge(thread);
    }

    @Override
    @TriggersRemove(cacheName = "threadCache",
            keyGenerator = @KeyGenerator(
                    name = "HashCodeCacheKeyGenerator",
                    properties = @Property(name = "includeMethod", value = "false")))
    public void deleteThread(long id) {
        em.remove(getThreadById(id));
    }

    @Override
    @TriggersRemove(cacheName = "threadCache", removeAll = true)
    public void addPost(Post post) {
        em.persist(post);
    }

    @Override
    public Post getPostById(long id) {
        return (Post) em.createQuery(SELECT_POST_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    @TriggersRemove(cacheName = "threadCache", removeAll = true)
    public void updatePost(Post post) {
        em.merge(post);
    }

    @Override
    @TriggersRemove(cacheName = "threadCache", removeAll = true)
    public void deletePost(long id) {
        em.remove(getPostById(id));
    }
}
