package org.pepsik.core.models.entities.Reworked;

import java.time.LocalDateTime;

/**
 * Created by pepsik on 9/29/2015.
 */
public class Comment {
    private Long id;
    private String text;
    private Account owner;
    private Long postId;
    private LocalDateTime when;

    public Comment() {
    }

    public Comment(Long id, String text, Account owner, Long postId, LocalDateTime when) { //temp
        this.id = id;
        this.text = text;
        this.owner = owner;
        this.postId = postId;
        this.when = when;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Long getPost() {
        return postId;
    }

    public void setPost(Long postId) {
        this.postId = postId;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
}
