package org.pepsik.core.repositories.Mocks;

import org.pepsik.core.models.entities.Account;
import org.pepsik.core.models.entities.Post;
import org.pepsik.core.repositories.jpa.AccountJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pepsik on 10/11/2015.
 */
public class PostJpaMock {
    private List<Post> posts;
    private Long idCounter = 3L;

    @Autowired
    private AccountJpaRepo accountJpaRepo;

    public PostJpaMock() {
        posts = new ArrayList<>();
        posts.add(new Post(1L, "post1", "textpost",
                new Account(1L, "username1", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."), LocalDateTime.now()));
        posts.add(new Post(2L, "post2", "textpost",
                new Account(3L, "username3", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF."), LocalDateTime.now()));
    }

    public Post create(Post data) {
        data.setId(idCounter++);
        data.setWhen(LocalDateTime.now());
        posts.add(data);
        return data;
    }

    public Post findById(Long id) {
        return posts.get(id.intValue() - 1);
    }

    public List<Post> findAll() {
        List returnedData = new ArrayList<>(posts);
        Collections.reverse(returnedData);
        return returnedData;
    }

    public Post update(Long postId, Post data) {
        Post post = posts.get(postId.intValue() - 1);
        post.setTitle(data.getTitle());
        post.setText(data.getText());
        return post;
    }

    public Post delete(Long postId) {
        Post post = posts.get(postId.intValue() - 1);
        posts.remove(postId.intValue() - 1);
        idCounter--;
        return post;
    }
}
