package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.pepsik.persistence.SmartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */

@Service
public class SmartServiceImpl implements SmartService {

    @Autowired
    private SmartDao smartDao;

    @Override
    public List<Thread> getAllThreads() {
        return smartDao.getAllThreads();
    }

    @Override
    public List<Thread> getRecentThreads() {
        return null;
    }

    @Override
    public void saveAccount(Account account) {

    }

    @Override
    public void saveThread(Thread thread) {
        smartDao.addThread(thread);
    }

    @Override
    public void saveMessage(Post message) {

    }

    @Override
    public Thread getThread(long id) {
        return smartDao.getThreadById(id);
    }

    @Override
    public Post getMessage(long id) {
        return null;
    }

    @Override
    public void deleteThread(long id) {

    }

    @Override
    public void deleteMessage(long id) {

    }
}
