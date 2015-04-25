package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */
public interface SmartService {

    List<Thread> getAllThreads();

    List<Thread> getThreadsByPage(int pageIndex);

    List<String> getPagination(int pageIndex);

    Account getAccount(long id);

    Account getAccount(String username);

    void saveAccount(Account account);

    void deleteAccount(long id);

    Thread getThread(long id);

    void saveThread(Thread thread);

    void deleteThread(long id);

    Post getPost(long id);

    void savePost(Post message);

    void deletePost(long id);

    boolean IsExistUsername(String username);
}
