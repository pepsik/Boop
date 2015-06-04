package org.pepsik.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pepsik on 6/2/15.
 */

@Entity
@Table(name = "private_messages")
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pmessage_id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id_fk", referencedColumnName = "user_id")
    private User sender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id_fk", referencedColumnName = "user_id")
    private User recipient;

    @NotNull
    @Size(min = 5, max = 1000)
    @Column
    private String text;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "dispatch_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private DateTime dispatchDate;

    @Column
    private boolean isRead;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(DateTime dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateMessage that = (PrivateMessage) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "id=" + id +
//                ", sender=" + sender.getUsername() +
//                ", recipient=" + recipient.getUsername() +
                ", text='" + text + '\'' +
                ", dispatchDate=" + dispatchDate +
                ", isRead=" + isRead +
                '}';
    }
}
