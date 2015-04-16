package org.pepsik.persistence;

import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public List<Thread> getRecentThreads() {
        return null;
    }

    @Override
    public void addAccount(Account account) {
        em.persist(account);
    }

    @Override
    public Account getAccountById(long id) {
        return (Account) em.createQuery(SELECT_ACCOUNT_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    public Account getAccountByUsername(String username) {
        return (Account) em.createQuery(SELECT_ACCOUNT_BY_USERNAME).setParameter("username", username).getSingleResult();
    }

    @Override
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    public void deleteAccount(long id) {
        em.remove(getAccountById(id));
    }

    @Override
    public void setAccountAuthory(Account account) {
        em.createNativeQuery(INSERT_ACCOUNT_AUTHORITY).setParameter("id", account.getId()).executeUpdate();
    }

    @Override
    public void addThread(Thread thread) {
        em.persist(thread);
    }

    @Override
    public Thread getThreadById(long id) {
        return (Thread) em.createQuery(SELECT_THREAD_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    public void updateThread(Thread thread) {
        em.merge(thread);
    }

    @Override
    public void deleteThread(long id) {
        em.remove(getThreadById(id));
    }

    @Override
    public void addPost(Post post) {
        em.persist(post);
    }

    @Override
    public Post getPostById(long id) {
        return (Post) em.createQuery(SELECT_POST_BY_ID).setParameter("id", id).getSingleResult();
    }

    @Override
    public void updatePost(Post post) {
        em.merge(post);
    }

    @Override
    public void deletePost(long id) {
        em.remove(getPostById(id));
    }
}
