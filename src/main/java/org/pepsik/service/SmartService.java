package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */
public interface SmartService {

    List<Thread> getAllThreads();

    List<Thread> getRecentThreads();

    void saveAccount(Account account);

    void saveThread(Thread thread);

    void saveMessage(Post message);

    Thread getThread(long id);

    Post getMessage(long id);

    void deleteThread(long id);

    void deleteMessage(long id);
}
