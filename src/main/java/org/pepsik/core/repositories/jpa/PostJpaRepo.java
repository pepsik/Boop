package org.pepsik.core.repositories.jpa;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepsik on 9/29/2015.
 */
@Repository
public class PostJpaRepo {
    private List<Post> posts;
    private Long idCounter = 3L;

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    public PostJpaRepo() {
        posts = new ArrayList<>();
        posts.add(new Post(1L, "post1", "textpost",
                new Account(1L, "username1", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."), LocalDateTime.now()));
        posts.add(new Post(2L, "post2", "textpost",
                new Account(3L, "username3", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."), LocalDateTime.now()));
    }

    public Post create(Post post) {
        post.setId(idCounter++);
        post.setOwner(accountJpaRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        post.setWhen(LocalDateTime.now());
        posts.add(post);
        return post;
    }

    public Post findById(Long id) {
        return posts.get(id.intValue() - 1);
    }

    public List<Post> findAll() {
        return posts;
    }

    public void update(Post data) {
    }

    public void delete(Post data) {
    }
}
