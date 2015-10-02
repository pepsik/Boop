package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.pepsik.core.repositories.jpa.PostJpaRepo;
import org.pepsik.core.services.Reworked.AccountService;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pepsik on 9/30/2015.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostJpaRepo postJpaRepo;

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    @Override
    public Post createPost(String loggedIn, Post data) {
        Account author = accountJpaRepo.findByUsername(loggedIn);
        data.setOwner(author);
        return postJpaRepo.create(data);
    }

    @Override
    public Post findPostById(Long id) {
        return postJpaRepo.findById(id);
    }

    @Override
    public PostList findAllPosts() {
        return new PostList(postJpaRepo.findAll());
    }

    @Override
    public Post updatePost(Long postId, Post data) {
        return postJpaRepo.update(postId, data);
    }

    @Override
    public Post deletePost(Long postId) {
        return postJpaRepo.delete(postId);
    }
}
