package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Thread;
import org.pepsik.persistence.SmartDao;
import org.pepsik.web.AccountController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */

@Service
@Transactional
public class SmartServiceImpl implements SmartService {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final int DEFAULT_THREADS_PER_PAGE = 5;

    @Autowired
    private SmartDao smartDao;

    @Override
    @Transactional(readOnly = true)
    public List<Thread> getAllThreads() {
        return smartDao.getAllThreads();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Thread> getThreadsByPage(int pageIndex) {
        return smartDao.getThreadsByPage(pageIndex, DEFAULT_THREADS_PER_PAGE);
    }

    @Override
    public List<String> getPagination(final int pageIndex) {
        List<String> pagination = new ArrayList<>();
        long threadCount = smartDao.getThreadCount();
        long pagesCount = threadCount / DEFAULT_THREADS_PER_PAGE;

        if (threadCount % DEFAULT_THREADS_PER_PAGE != 0)
            pagesCount += 1;

        if (pagesCount <= 5)
            for (int i = 1; i <= pagesCount; i++) {
                pagination.add(Integer.toString(i));
            }
        else {
            for (int i = pageIndex - 2; i <= pageIndex + 2; i++) {
                pagination.add(Integer.toString(i));
            }
        }
        return pagination;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveThread(Thread thread) {
        if (thread.getId() == null) {
            String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
            Account account = getAccount(loggedUser);
            thread.setAccount(account);
            smartDao.addThread(thread);
        } else
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public void savePost(Post post) {
        if (post.getId() == null) {
            String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
            Account account = getAccount(loggedUser);
            post.setAccount(account);
            smartDao.addPost(post);
        } else
            smartDao.updatePost(post);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getPost(#id).account.username) or hasRole('ROLE_ADMIN')")
    public void deletePost(long id) {
        smartDao.deletePost(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean IsExistUsername(String username) {
        try {
            smartDao.getAccountByUsername(username);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }
}
