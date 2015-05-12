package org.pepsik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account extends IdEntity {

    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

//    @NotNull
//    @Size(min = 3, max = 40)
//    @Pattern(regexp = "^[a-zA-Z]+\\s?[a-zA-Z]+$")
//    @Column(name = "fullname", nullable = false)
//    private String fullname;

    @NotNull
    @Size(min = 3, max = 60)
    @Column(name = "password", nullable = false)
    private String password;

//    @NotNull
//    @Size(min = 3, max = 20)
//    @Column(name = "email")
//    private String email;

//    @NotNull
//    @Column(name = "birth_date")
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private DateTime birthdate;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getFullname() {
//        return fullname;
//    }
//
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }

    //    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public DateTime getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(DateTime birthdate) {
//        this.birthdate = birthdate;
//    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", fullname='" + fullname + '\'' +
//                ", password='" + password + '\'' +
//                ", birthdate=" + birthdate +
//                '}';
//    }
}
