package org.pepsik.rest.resources;

import org.pepsik.core.models.entities.Tag;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

/**
 * Created by pepsik on 10/27/2015.
 */
public class TagResource extends ResourceSupport {
    private Long rid;
    private String name;
    private String author;
    private String description;
    private String imageUrl;
    private int postsCount;
    private LocalDateTime createDate;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Tag toTag() {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setDescription(description);
        tag.setImageUrl(imageUrl);
        return tag;
    }
}
