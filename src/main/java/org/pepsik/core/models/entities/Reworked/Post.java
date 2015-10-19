package org.pepsik.core.models.entities.Reworked;

import org.pepsik.core.services.converters.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by pepsik on 9/29/2015.
 */
@Entity
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "account_id")
    private Account owner;
    @Column(name = "date")
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime when;

    public Post() {
    }

    public Post(Long id, String title, String text, Account owner, LocalDateTime when) { //temp
        this.id = id;
        this.title = title;
        this.text = text;
        this.owner = owner;
        this.when = when;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", owner=" + owner +
                ", when=" + when +
                '}';
    }
}
