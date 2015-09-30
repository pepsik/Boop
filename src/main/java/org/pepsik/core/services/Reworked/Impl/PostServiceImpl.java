package org.pepsik.core.services.Reworked.Impl;

import org.pepsik.core.models.entities.Reworked.Post;
import org.pepsik.core.repositories.jpa.PostJpaRepo;
import org.pepsik.core.services.Reworked.PostService;
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

    @Override
    public Post createPost(Post data) {
        return postJpaRepo.create(data);
    }

    @Override
    public Post findPostById(Long id) {
        return postJpaRepo.findById(id);
    }

    @Override
    public List<Post> findAllPosts() {
        return postJpaRepo.findAll();
    }

    @Override
    public void updatePost(Post data) {
        postJpaRepo.update(data);
    }

    @Override
    public void deletePost(Post data) {
      postJpaRepo.delete(data);
    }
}
