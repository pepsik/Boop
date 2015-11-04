package org.pepsik.core.models.entities.Reworked;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pepsik.core.services.converters.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pepsik on 10/26/2015.
 */
@Entity
public class Tag {
    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Account author;
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "posts_count")
    private int postsCount;
    @Column(name = "date")
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime createDate;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Post> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", postsCount=" + postsCount +
                ", createDate=" + createDate +
                '}';
    }
}
