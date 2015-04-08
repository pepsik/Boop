package org.pepsik.service;

import org.joda.time.DateTime;
import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.*;

/**
 * Created by pepsik on 4/8/15.
 */

@Service
public class SmartServiceImpl implements SmartService {

    @Override
    public List<Thread> getAllThreads() {
        Thread thread1 = new Thread();
        thread1.setText("This is thread number 1");
        thread1.setWhen(new DateTime());

        Thread thread2 = new Thread();
        thread2.setText("This is thread number 2");
        return asList(thread1, thread2);
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

    }

    @Override
    public void saveMessage(Post message) {

    }

    @Override
    public Thread getThreadById(long id) {
        return null;
    }

    @Override
    public Post getMessageById(long id) {
        return null;
    }
}
