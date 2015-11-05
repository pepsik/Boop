package org.pepsik.core.models.entities;

import org.pepsik.core.services.converters.LocalDatePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by pepsik on 10/19/2015.
 */
@Entity
public class Profile {
    @Id @Column(name = "profile_id")
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate birthdate;
    private String gender;
    private String country;
    private String city;
    private String job;
    private String about;
    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "account_id")
    private Account owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", job='" + job + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
