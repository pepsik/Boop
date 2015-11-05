package org.pepsik.rest.resources;

import org.pepsik.core.models.entities.Post;
import org.pepsik.core.models.entities.Tag;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pepsik on 9/29/2015.
 */
public class PostResource extends ResourceSupport {
    private Long rid;
    private String title;
    private String text;
    private Set<String> tagNames;
    private String author;
    private LocalDateTime when;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
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

    public Set<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(Set<String> tags) {
        this.tagNames = tags;
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

    public Post toPost() {
        Post post = new Post();
        post.setTitle(title);
        post.setText(text);

        Set<Tag> tags = new HashSet<>();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tags.add(tag);
            }
        }
        post.setTags(tags);
        return post;
    }
}
