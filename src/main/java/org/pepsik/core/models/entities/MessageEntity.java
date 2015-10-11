package org.pepsik.core.models.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "messages")
public abstract class MessageEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    protected User user;

    @NotNull
    @Size(min = 2, max = 20000)
    @Column(name = "text")
    protected String text;

    @Column(name = "posted_time")
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "hh:mma MMM d, YYYY")
    protected DateTime when;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getWhen() {
        return when;
    }

    public void setWhen(DateTime when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "user=" + user.getId() +
                ", text='" + text.length() + '\'' +
                ", when=" + when +
                "} " + super.toString();
    }
}
