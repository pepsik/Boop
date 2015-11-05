package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Profile;
import org.pepsik.core.repositories.AccountRepo;
import org.pepsik.core.repositories.ProfileRepo;
import org.pepsik.core.security.AccountUserDetails;
import org.pepsik.core.services.Reworked.ProfileService;
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
    private static final int DEFAULT_POSTS_PER_PAGE = 7;
    private static final int DEFAULT_COMMENTS_PER_PAGE = 20;
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
        String loggedIn = ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Account account = accountRepo.findByUsername(loggedIn);
        return profileRepo.update(account.getId(), data);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @securityService.canUpdateProfile(#id)")
    public Profile deleteProfile(Long id) {
        return profileRepo.delete(id);
    }

    @Override
    public PostList getUserPostsByPage(String username, Integer requestedPage) {
        Account account = accountRepo.findByUsername(username);
        return new PostList(profileRepo.getPosts(account, requestedPage, DEFAULT_POSTS_PER_PAGE));
    }

    @Override
    public Long getUserPostsCount(String username) {
        return null;
    }

    @Override
    public CommentList getUserCommentsByPage(String username, Integer requestedPage) {
        Account account = accountRepo.findByUsername(username);
        return new CommentList(profileRepo.getComments(account, requestedPage, DEFAULT_COMMENTS_PER_PAGE));
    }

    @Override
    public Long getUserCommentsCount(String username) {
        return null;
    }

    @Override
    public PostList getUserFavorites(String username, Integer page) {
        return null;
    }

    @Override
    public Long getUserFavoritesCount(String username) {
        return null;
    }
}
