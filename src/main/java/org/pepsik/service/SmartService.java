package org.pepsik.service;

import org.pepsik.model.*;
import org.pepsik.model.Post;

import java.util.List;

/**
 * Created by pepsik on 4/9/15.
 */
public interface SmartService {

    List<Post> getAllPosts();

    List<Post> getPostsByPage(int pageIndex);

    List<String> getPagination(int pageIndex);

    Account getAccount(long id);

    Account getAccount(String username);

    void saveAccount(Account account);

    void deleteAccount(long id);

    void saveProfile(Profile profile);

    Profile getProfile(String username);

    void deleteProfile(String username);

    Post getPost(long id);

    void savePost(Post thread);

    void deletePost(long id);

    Comment getComment(long id);

    void saveComment(Comment message);

    void deleteComment(long id);

    boolean isExistComment(long id);

    boolean isExistUsername(String username);

    boolean isExistPost(long id);

    long getPagesCount();
}
