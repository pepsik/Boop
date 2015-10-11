package org.pepsik.core.models.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

/**
 * Created by pepsik on 5/27/15.
 */
//@Entity
//@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private long id;

//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "added_date")
    private DateTime addedDate;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id_fk", referencedColumnName = "post_id")
    private Post post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(DateTime addedDate) {
        this.addedDate = addedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;
        return post.equals(favorite.post);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + post.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", addedDate=" + addedDate +
                ", user=" + user.getUsername() +
                ", post=" + post.id +
                '}';
    }
}
