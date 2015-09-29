package org.pepsik.core.models.entities.Reworked;

import java.time.LocalDateTime;

/**
 * Created by pepsik on 9/29/2015.
 */
public class Comment {
    private Long id;
    private String text;
    private Account owner;
    private Post post;
    private LocalDateTime when;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
}
