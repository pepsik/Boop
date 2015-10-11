package org.pepsik.core.models.entities;

import javax.persistence.*;

//@Entity
//@Table(name = "Settings")
public class Settings {

    @Id
    @Column(name = "posts_per_page")
    private int defaultPostsPerPage;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    private User user;

    public int getDefaultPostsPerPage() {
        return defaultPostsPerPage;
    }

    public void setDefaultPostsPerPage(int defaultPostsPerPage) {
        this.defaultPostsPerPage = defaultPostsPerPage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "defaultPostsPerPage=" + defaultPostsPerPage +
                ", user=" + user.getId() +
                '}';
    }
}
