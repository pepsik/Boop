package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;

import java.util.List;

public interface SmartService {

    List<Thread> getAllThreads();

    List<Thread> getRecentThreads();

    void saveAccount(Account account);

    void saveThread(Thread thread);

    void saveMessage(Post message);

    Thread getThreadById(long id);

    Post getMessageById(long id);

}
