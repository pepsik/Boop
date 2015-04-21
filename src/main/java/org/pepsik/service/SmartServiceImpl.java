package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.pepsik.persistence.SmartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */

@Service
@Transactional
public class SmartServiceImpl implements SmartService {

    @Autowired
    private SmartDao smartDao;

    @Override
    @Transactional(readOnly = true)
    public List<Thread> getAllThreads() {
        return smartDao.getAllThreads();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Thread> getRecentThreads() {
        return smartDao.getRecentThreads();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(long id) {
        return smartDao.getAccountById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(String username) {
        return smartDao.getAccountByUsername(username);
    }

    @Override
    public void saveAccount(Account account) {
        if (account.getId() == null) {
            smartDao.addAccount(account);
            smartDao.setAccountAuthory(account); // set ROLE_USER to all new accounts
        } else
            smartDao.updateAccount(account);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(long id) {
        smartDao.deleteAccount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Thread getThread(long id) {
        return smartDao.getThreadById(id);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == #thread.account.username) or hasRole('ROLE_ADMIN')")
    public void saveThread(Thread thread) {
        if (thread.getId() == null)
            smartDao.addThread(thread);
        else
            smartDao.updateThread(thread);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getThread(#id).account.username) or hasRole('ROLE_ADMIN')")
    public void deleteThread(long id) {
        smartDao.deleteThread(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPost(long id) {
        return smartDao.getPostById(id);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == #post.account.username) or hasRole('ROLE_ADMIN')")
    public void savePost(Post post) {
        if (post.getId() == null)
            smartDao.addPost(post);
        else
            smartDao.updatePost(post);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getPost(#id).account.username) or hasRole('ROLE_ADMIN')")
    public void deletePost(long id) {
        smartDao.deletePost(id);
    }
}
