package org.pepsik.persistence;

import org.pepsik.model.*;
import org.pepsik.model.Post;

import java.util.List;

public interface SmartDao {

    List getAllPosts();

    List<Post> getPostsByPage(int pageIndex, int threadsPerPage);

    long getPostCount();

    void addAccount(Account account);

    Account getAccountById(long id);

    Account getAccountByUsername(String username);

    void updateAccount(Account account);

    void deleteAccount(long id);

    void addProfile(Profile profile);

    Profile getProfile(long id);

    void updateProfile(Profile profile);

    void deleteProfile(String username);

    void setAccountAuthory(Account account);

    void addPost(Post thread);

    Post getPostById(long id);

    void updatePost(Post thread);

    void deletePost(long id);

    void addComment(Comment post);

    Comment getCommentById(long id);

    void updateComment(Comment post);

    void deleteComment(long id);
}
