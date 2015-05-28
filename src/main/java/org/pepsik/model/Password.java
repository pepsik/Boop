package org.pepsik.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pepsik on 5/23/15.
 */

@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @Column(name = "user_id_fk")
    private long id;

    @NotNull
    @Size(min = 3, max = 60)
    @Column(name = "password", nullable = false)
    private String password;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userPassword")
    @JoinColumn(name = "user_id_fk")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Password{" +
                ", password='" + password + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
