package org.pepsik.core.services.Impl;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.models.entities.Profile;
import org.pepsik.core.repositories.AccountRepo;
import org.pepsik.core.repositories.ProfileRepo;
import org.pepsik.core.services.ProfileService;
import org.pepsik.rest.utilities.CommentList;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pepsik on 10/19/2015.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private static final int DEFAULT_POSTS_PER_PAGE = 3;
    private static final int DEFAULT_COMMENTS_PER_PAGE = 1;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Profile createProfile(Profile data) {
        return profileRepo.create(data);
    }

    @Override
    public Profile findProfileById(Long id) {
        return profileRepo.findById(id);
    }

    @Override
    public Profile findProfileByUsername(String username) {
        Account account = accountRepo.findByUsername(username);
        Profile profile = null;
        if (account != null) {
            profile = profileRepo.findById(account.getId());
        }
        return profile;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Profile updateProfile(Profile data) {
        String loggedIn = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepo.findByUsername(loggedIn);
        return profileRepo.update(account.getId(), data);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canUpdateProfile(#id)")
    public Profile deleteProfile(Long id) {
        return profileRepo.delete(id);
    }

    @Override
    public PostList findUserPostsByPage(String username, Integer requestedPage) {
        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            return new PostList(profileRepo.getPosts(account, requestedPage, DEFAULT_POSTS_PER_PAGE));
        } else {
            return null;
        }
    }

    @Override
    public Long findUserPostsCount(String username) {
        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            return profileRepo.getPostCount(account);
        } else {
            return null;
        }
    }

    @Override
    public CommentList findUserCommentsByPage(String username, Integer requestedPage) {
        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            return new CommentList(profileRepo.getComments(account, requestedPage, DEFAULT_COMMENTS_PER_PAGE));
        } else {
            return null;
        }
    }

    @Override
    public Long findUserCommentsCount(String username) {
        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            return profileRepo.getCommentCount(account);
        } else {
            return null;
        }
    }

    @Override
    public PostList findUserFavorites(String username, Integer page) {
        return null;
    }

    @Override
    public Long findUserFavoritesCount(String username) {
        return null;
    }
}
