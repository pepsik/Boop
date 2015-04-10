package org.pepsik.persistence;

import org.pepsik.model.*;
import org.pepsik.model.Thread;

import java.util.List;

public interface SmartDao {

    List<Thread> getAllThreads();

    List<Thread> getRecentThreads();

    void addAccount(Account account);

    void addThread(Thread thread);

    void addMessage(Post message);

    Thread getThreadById(long id);

    Post getMessageById(long id);

}
