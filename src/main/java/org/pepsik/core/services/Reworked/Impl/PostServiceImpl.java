package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.repositories.PostRepo;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.pepsik.core.services.Reworked.PostService;
import org.pepsik.rest.utilities.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by pepsik on 9/30/2015.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Post createPost(String loggedIn, Post data) {
        Account author = accountJpaRepo.findByUsername(loggedIn);
        data.setOwner(author);
        data.setWhen(LocalDateTime.now());
        return postRepo.create(data);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepo.findById(id);
    }

    @Override
    public PostList findAllPosts() {
        return new PostList(postRepo.findAll());
    }

    @Override
    @PreAuthorize("(principal.username == this.findPostById(#postId).getOwner().getUsername()) or hasRole('ROLE_ADMIN')")
    public Post updatePost(Long postId, Post data) {
        return postRepo.update(postId, data);
    }

    @Override
    @PreAuthorize("(principal.username == this.findPostById(#postId).getOwner().getUsername()) or hasRole('ROLE_ADMIN')")
    public Post deletePost(Long postId) {
        return postRepo.delete(postId);
    }
}
