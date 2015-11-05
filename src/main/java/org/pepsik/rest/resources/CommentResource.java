package org.pepsik.rest.resources;

import org.pepsik.core.models.entities.Comment;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

/**
 * Created by pepsik on 9/29/2015.
 */
public class CommentResource extends ResourceSupport {
    private Long rid;
    private String text;
    private String author;
    private LocalDateTime when;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public Comment toComment(){
        Comment comment = new Comment();
        comment.setText(text);
        return comment;
    }
}
