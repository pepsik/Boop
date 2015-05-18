package org.pepsik.model;

import javax.persistence.*;

@Entity
@Table(name = "Settings")
public class Settings {

    @Id
    @Column(name = "posts_per_page")
    private int defaultPostsPerPage;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id_fk", referencedColumnName = "account_id")
    private Account account;

    public int getDefaultPostsPerPage() {
        return defaultPostsPerPage;
    }

    public void setDefaultPostsPerPage(int defaultPostsPerPage) {
        this.defaultPostsPerPage = defaultPostsPerPage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "defaultPostsPerPage=" + defaultPostsPerPage +
                ", account=" + account +
                '}';
    }
}
