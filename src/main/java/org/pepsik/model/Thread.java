package org.pepsik.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread extends MessageEntity {

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
