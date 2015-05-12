package org.pepsik.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "messages")
public abstract class MessageEntity extends IdEntity {

    @ManyToOne(targetEntity = Account.class)
    protected Account account;

    @NotNull
    @Size(min = 2, max = 20000)
    @Column(name = "text")
    protected String text;

    @Column(name = "posted_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "hh:mma MMM d, YYYY")
    protected DateTime when;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
                "account=" + account +
                ", text='" + text + '\'' +
                ", when=" + when +
                "} " + super.toString();
    }
}
