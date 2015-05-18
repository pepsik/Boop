package org.pepsik.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "profile_id")
    private long profile_id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id", referencedColumnName = "account_id")
    private Account account;

    @Size(min = 3, max = 20)
    @Column(name = "email")
    private String email;

    @Size(min = 3, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+\\s?[a-zA-Z]+$")
    @Column(name = "fullname")
    private String fullname;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private DateTime birthdate;

    @Column(name = "gender")
    private String gender; //enum?

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "job")
    private String job;

    @Column(name = "about")
    private String about;

    public long getId() {
        return profile_id;
    }

    public void setId(long id) {
        this.profile_id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public DateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Profile{" +
                ", account=" + account +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", job='" + job + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
