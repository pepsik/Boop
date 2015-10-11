package org.pepsik.core.repositories.Mocks;

import org.pepsik.core.models.entities.Reworked.Account;
import org.pepsik.core.models.entities.Reworked.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by pepsik on 10/11/2015.
 */
public class CommentJpaMock {
    private Map<Long, Comment> commentsById = new HashMap<>();
    private Map<Long, List<Comment>> commentsByPost = new HashMap<>();
    private Long idCounter;

    public CommentJpaMock() {
        Account account1 = new Account(1L, "username1", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF.");
        Account account2 = new Account(2L, "username2", "password");
        Account account3 = new Account(3L, "username3", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF.");
        Account account4 = new Account(4L, "username4", "$2a$10$CChiQYJx3Y11lgzXt9mBx.adLHFrJh0W9jbPqs4IJOfNRcTKgEMF.");

        Comment commentA = new Comment();
        Comment commentB = new Comment();
        Comment commentC = new Comment();
        Comment commentD = new Comment(/*4L, "comment4", account4, 2L, LocalDateTime.now()*/);

        commentsById.put(1L, commentA);
        commentsById.put(2L, commentB);
        commentsById.put(3L, commentC);
        commentsById.put(4L, commentD);

        commentsByPost.put(1L, new ArrayList<>(asList(commentA, commentB, commentC)));
        commentsByPost.put(2L, new ArrayList<>(asList(commentD)));

        idCounter = (long) commentsById.size();
    }

    public Comment create(Long postId, Comment data) { //in serviceComment checkout if post exist
        data.setId(++idCounter);
        data.setWhen(LocalDateTime.now());
        List<Comment> comments = commentsByPost.get(postId);
        if (comments != null) {
            comments.add(data);
        } else {
            comments = new ArrayList<>(asList(data));
            commentsByPost.put(postId, comments);
        }
        commentsById.put(data.getId(), data);
//        data.setPost(postId);
        return data;
    }

    public Comment findById(Long id) {
        return commentsById.get(id);
    }

    public List<Comment> findCommentsByPost(Long id) {
        List<Comment> list = commentsByPost.get(id);
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    public Comment update(Long id, Comment data) {
        Comment comment = commentsById.get(id);
        comment.setText(data.getText());
        return comment;
    }

    public Comment delete(Long id) {
        Comment comment = commentsById.get(id);
        commentsById.remove(id);
        commentsByPost.get(comment.getPost()).remove(comment);
        return comment;
    }
}
