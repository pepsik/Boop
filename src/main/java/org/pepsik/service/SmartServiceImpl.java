package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.pepsik.persistence.SmartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */

@Service
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
    @Transactional
    public void saveAccount(Account account) {
        if (account.getId() == null)
            smartDao.addAccount(account);
        else
            smartDao.updateAccount(account);
    }

    @Override
    @Transactional
    public void deleteAccount(long id) {
        smartDao.deleteAccount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Thread getThread(long id) {
        return smartDao.getThreadById(id);
    }

    @Override
    @Transactional
    public void saveThread(Thread thread) {
        if (thread.getId() == null)
            smartDao.addThread(thread);
        else
            smartDao.updateThread(thread);
    }

    @Override
    @Transactional
    public void deleteThread(long id) {
        smartDao.deleteThread(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPost(long id) {
        return null;
    }

    @Override
    @Transactional
    public void savePost(Post post) {
        if (post.getId() == null)
            smartDao.addPost(post);
        else
            smartDao.updatePost(post);
    }

    @Override
    @Transactional
    public void deletePost(long id) {
        smartDao.deletePost(id);
    }
}
