package org.pepsik.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class MessageEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "account_id")
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

}
