package org.pepsik.service;

import org.apache.commons.collections.ListUtils;
import org.pepsik.model.*;
import org.pepsik.model.Post;
import org.pepsik.model.Profile;
import org.pepsik.model.support.PostComparator;
import org.pepsik.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.*;

/**
 * Created by pepsik on 4/9/15.
 */

@Service
@Transactional(readOnly = true)
public class SmartServiceImpl implements SmartService {

    private static final int DEFAULT_POSTS_PER_PAGE = 7;
    private static final int DEFAULT_PAGINATION_ON_PAGE = 5;

    private static final Logger logger = LoggerFactory.getLogger(SmartServiceImpl.class);

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public List<Post> getPostsByPage(int pageIndex) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (loggedUser.equals("guest"))
            return postDao.getPostsByPage(pageIndex, DEFAULT_POSTS_PER_PAGE);

        List<Post> userFavorites = getFavorites(loggedUser);

        List<Post> postsByPage = postDao.getPostsByPage(pageIndex, DEFAULT_POSTS_PER_PAGE);
        List<Post> matches = ListUtils.retainAll(postsByPage, userFavorites);
        for (Post post : matches)
            post.setFavorite(true);

        for (Post post : postsByPage) //TODO:temp
            post.getComments().size();

        return postsByPage;
    }

    @Override
    public Post getPost(long id) {
        Post post = postDao.getPostById(id);
        post.getComments().size();
        return post;
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void savePost(Post post) {
        if (post.getId() == 0) {
            String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = getUser(loggedUser);
            post.setUser(user);

            Set<Tag> newPostTags = post.getTags();
            Set<Tag> finalTags = new HashSet<>();
            for (Tag tag : newPostTags)
                if (isExistTag(tag.getName()))
                    finalTags.add(postDao.getTag(tag.getName())); //fix?
                else {
                    finalTags.add(tag);

                    logger.info(tag.getName());
                    logger.info(Long.toString(tag.getId()));
                }
            logger.info(post.getTags().toString());
            post.setTags(finalTags);
            postDao.addPost(post);
        } else
            postDao.updatePost(post);
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getPost(#id).user.username) or hasRole('ROLE_ADMIN')")
    public void deletePost(long id) {
        postDao.deletePost(id);
    }

    @Override
    public boolean isExistPost(long id) {
        try {
            postDao.getPostById(id);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> getPagination(final int pageIndex) {
        List<String> pagination = new ArrayList<>();
        long postCount = postDao.getPostCount();
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
    public User getUser(long id) {
        return userAccountDao.getUserById(id);
    }

    @Override
    public User getUser(String username) {
        return userAccountDao.getUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveUser(User user) {
        if (user.getId() == 0) {
            userAccountDao.addUser(user);
            userAccountDao.setUserAuthority(user); // set ROLE_USER to all new accounts
        } else
            userAccountDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(long id) {
        userAccountDao.deleteUser(id);
    }

    @Override
    public boolean isExistUsername(String username) {
        try {
            userAccountDao.getUserByUsername(username);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == #profile.user.username)")
    public void saveProfile(Profile profile) {
        if (profile.getId() == 0) {
            profileDao.addProfile(profile);
            userAccountDao.setUserAuthority(profile.getUser());
        } else
            profileDao.updateProfile(profile);
    }

    @Override
    public Profile getProfile(String username) {
        final User user = userAccountDao.getUserByUsername(username);
        return profileDao.getProfile(user.getId());
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getPost(#id).user.username) or hasRole('ROLE_ADMIN')")
    public void deleteProfile(String username) {
        profileDao.deleteProfile(username);
    }

    @Override
    public Comment getComment(long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    @Transactional(readOnly = false)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveComment(Comment post) {
        if (post.getId() == 0) {
            String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = getUser(loggedUser);
            post.setUser(user);
            commentDao.addComment(post);
        } else
            commentDao.updateComment(post);
    }

    @Override
    @PreAuthorize("(hasRole('ROLE_USER') and principal.username == this.getComment(#id).user.username) or hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false)
    public void deleteComment(long id) {
        commentDao.deleteComment(id);
    }

    @Override
    public boolean isExistComment(long id) {
        try {
            commentDao.getCommentById(id);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    public long getPagesCount() {
        long postCount = postDao.getPostCount();

        if (postCount % DEFAULT_POSTS_PER_PAGE != 0)
            return postCount / DEFAULT_POSTS_PER_PAGE + 1;
        return postCount / DEFAULT_POSTS_PER_PAGE;
    }

    @Override
    @Transactional(readOnly = false)
    public void addFavorite(long postId) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUser(loggedUser);
        user.getFavorites().add(getPost(postId));
        userAccountDao.updateUser(user);
    }

    @Override
    public List<Post> getFavorites(String username) {
        User user = getUser(username);
        List<Post> postList = new LinkedList<>(user.getFavorites());
        Comparator comparator = new PostComparator();
        Collections.sort(postList, comparator);
        Collections.sort(postList, Collections.reverseOrder(comparator));
        for (Post post : postList)
            post.setFavorite(true);
        return postList;
    }

    @Override
    @Transactional(readOnly = false)
    public void removeFavorite(long postId) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUser(loggedUser);

        user.getFavorites().remove(getPost(postId));
        userAccountDao.updateUser(user);
    }

    public boolean isExistTag(String tagName) {
        try {
            postDao.getTag(tagName);
        } catch (NoResultException exception) {
            return false;
        }
        return true;
    }

}
