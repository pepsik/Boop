package org.pepsik.rest.resources;

import org.pepsik.core.models.entities.Reworked.Profile;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * Created by pepsik on 10/19/2015.
 */
public class ProfileResource extends ResourceSupport {
    private Long rid;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
    private String country;
    private String city;
    private String job;
    private String about;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
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

    public Profile toProfile(){
        Profile profile = new Profile();
        profile.setAbout(about);
        profile.setBirthdate(birthdate);
        profile.setCountry(country);
        profile.setCity(city);
        profile.setEmail(email);
        profile.setFirstname(firstname);
        profile.setGender(gender);
        profile.setJob(job);
        profile.setLastname(lastname);
        return profile;
    }
}
