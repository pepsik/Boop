package org.pepsik.model;


import org.pepsik.model.support.PostLabel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@SqlResultSetMapping(name = "PostLabelResult", classes = {
        @ConstructorResult(targetClass = PostLabel.class,
                columns = {@ColumnResult(name = "post_id", type = long.class), @ColumnResult(name = "title", type = String.class)})
})
@Entity
@Table(name = "posts")
@PrimaryKeyJoinColumn(name = "post_id")
public class Post extends MessageEntity implements Serializable {

    @NotNull
    @Size(min = 3, max = 40)
    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Comment> comments;

    @Valid
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "tag_owners", joinColumns = {@JoinColumn(name = "post_id_fk", referencedColumnName = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id_fk", referencedColumnName = "tag_id")})
    private Set<Tag> tags;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Favorite> favorites;

    @Column(name = "favorite_count")
    private int favoriteCount;

    @Transient
    private boolean isFavorite = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Post target = (Post) object;
        if (target.getId() == this.getId())
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", isFavorite=" + isFavorite +
                "} ";
    }
}
