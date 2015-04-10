package org.pepsik.model;

import org.joda.time.DateTime;

public class MessageEntity extends BaseEntity {

    protected Account account;

    protected String text;

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
