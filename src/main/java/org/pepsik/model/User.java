package org.pepsik.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column
    private String username;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private UserPassword userPassword;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_favorites",
            joinColumns = {@JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id_fk", referencedColumnName = "post_id")})
    private Set<Post> favorites;

//    private Settings settings;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword password) {
        this.userPassword = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Post> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Post> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", profile=" + profile +
                ", favorites=" + favorites.size() +
                '}';
    }
}
