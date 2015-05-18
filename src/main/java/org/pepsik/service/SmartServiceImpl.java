package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Post;
import org.pepsik.model.Account;
import org.pepsik.model.Profile;
import org.pepsik.persistence.SmartDao;
import org.pepsik.web.UserController;
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
@Transactional(readOnly = true)
public class SmartServiceImpl implements SmartService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final int DEFAULT_POSTS_PER_PAGE = 7;
    private static final int DEFAULT_PAGINATION_ON_PAGE = 5;

    @Autowired
    private SmartDao smartDao;

    @Override
    public List<Post> getAllPosts() {
        return smartDao.getAllPosts();
    }

    @Override
    public List<Post> getPostsByPage(int pageIndex) {
        return smartDao.getPostsByPage(pageIndex, DEFAULT_POSTS_PER_PAGE);
    }

    @Override
    public List<String> getPagination(final int pageIndex) {
        List<String> pagination = new ArrayList<>();
        long postCount = smartDao.getPostCount();
        long pagesCount = postCount / DEFAULT_POSTS_PER_PAGE;

        if (postCount % DEFAULT_POSTS_PER_PAGE != 0)
            pagesCount += 1;

        if (pagesCount <= DEFAULT_PAGINATION_ON_PAGE) {
            for (int i = 1; i <= pagesCount; i++)
                pagination.add(Integer.toString(i));

            return pagination;
        }

        if (pageIndex <= 2) {
            for (int i = 1; i <= DEFAULT_PAGINATION_ON_PAGE; i++)
                pagination.add(Integer.toString(i));

            return pagination;
        }


        for (int i = pageIndex - 2; i <= pageIndex + 2; i++) {
            if (i > pagesCount)
                break;
            pagination.add(Integer.toString(i));
        }

        return pagination;
    }

    @Override
    public Account getAccount(long id) {
        return smartDao.getAccountById(id);
    }

    @Override
    public Account getAccount(String username) {
        return smartDao.getAccountByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveAccount(Account account) {
        if (account.getId() == 0) {
            smartDao.addAccount(account);
            smartDao.setAccountAuthory(account); // set ROLE_USER to all new accounts
        } else
            smartDao.updateAccount(account);
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(long id) {
        smartDao.deleteAccount(id);
    }

    @Override
    public boolean isExistUsername(String username) {
        try {
            smartDao.getAccountByUsername(username);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == #profile.account.username)")
    public void saveProfile(Profile profile) {
        if (profile.getId() == 0) {
            smartDao.addProfile(profile);
            smartDao.setAccountAuthory(profile.getAccount());
        }
        else
            smartDao.updateProfile(profile);
    }

    @Override
    public Profile getProfile(String username) {
        final Account account = smartDao.getAccountByUsername(username);
        return smartDao.getProfile(account.getId());
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getPost(#id).account.username) or hasRole('ROLE_ADMIN')")
    public void deleteProfile(String username) {
        smartDao.deleteProfile(username);
    }

    @Override
    public Post getPost(long id) {
        return smartDao.getPostById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public void savePost(Post post) {
        if (post.getId() == 0) {
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
    public boolean isExistPost(long id) {
        try {
            smartDao.getPostById(id);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Comment getComment(long id) {
        return smartDao.getCommentById(id);
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveComment(Comment post) {
        if (post.getId() == 0) {
            String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
            Account account = getAccount(loggedUser);
            post.setAccount(account);
            smartDao.addComment(post);
        } else
            smartDao.updateComment(post);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getComment(#id).account.username) or hasRole('ROLE_ADMIN')")
    public void deleteComment(long id) {
        smartDao.deleteComment(id);
    }

    @Override
    public boolean isExistComment(long id) {
        try {
            smartDao.getCommentById(id);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    public long getPagesCount() {
        long postCount = smartDao.getPostCount();

        if (postCount % DEFAULT_POSTS_PER_PAGE != 0)
            return postCount / DEFAULT_POSTS_PER_PAGE + 1;
        return postCount / DEFAULT_POSTS_PER_PAGE;
    }
}
