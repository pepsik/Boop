package org.pepsik.model;

import org.joda.time.DateTime;

public class MessageEntity extends BaseEntity {

    protected Account userAccount;

    protected String text;

    protected DateTime when;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
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
