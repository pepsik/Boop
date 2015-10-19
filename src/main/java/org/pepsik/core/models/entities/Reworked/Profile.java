package org.pepsik.core.models.entities.Reworked;

import org.pepsik.core.services.converters.LocalDatePersistenceConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by pepsik on 10/19/2015.
 */
@Entity
public class Profile {
    @Id
    private long account_id;
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

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
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

    @Override
    public String toString() {
        return "Profile{" +
                "account_id=" + account_id +
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
