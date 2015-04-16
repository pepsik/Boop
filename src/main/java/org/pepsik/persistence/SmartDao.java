package org.pepsik.persistence;

import org.pepsik.model.*;
import org.pepsik.model.Thread;

import java.util.List;

public interface SmartDao {

    List getAllThreads();

    List<Thread> getRecentThreads();

    void addAccount(Account account);

    Account getAccountById(long id);

    Account getAccountByUsername(String username);

    void updateAccount(Account account);

    void deleteAccount(long id);

    void setAccountAuthory(Account account);

    void addThread(Thread thread);

    Thread getThreadById(long id);

    void updateThread(Thread thread);

    void deleteThread(long id);

    void addPost(Post post);

    Post getPostById(long id);

    void updatePost(Post post);

    void deletePost(long id);
}
