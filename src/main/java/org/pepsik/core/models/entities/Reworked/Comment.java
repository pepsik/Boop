package org.pepsik.core.models.entities.Reworked;

import org.pepsik.core.services.converters.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by pepsik on 9/29/2015.
 */
@Entity
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "account_id")
    private Account owner;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Column(name = "date")
    @Convert(converter = LocalDateTimePersistenceConverter.class)
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

    public void setPost(Post postId) {
        this.post = postId;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
}
