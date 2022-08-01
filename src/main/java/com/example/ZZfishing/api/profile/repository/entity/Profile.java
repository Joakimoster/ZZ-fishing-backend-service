package com.example.ZZfishing.api.profile.repository.entity;

import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties( {"id"} )
public class Profile extends IdEntity {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String country;

    @OneToMany(mappedBy = "profile")
    private List<Catching> catchings;

    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, mappedBy = "profile")
    private User user;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String email, int age, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Catching> getCatchings() {
        return catchings;
    }

    public void setCatchings(List<Catching> catchings) {
        this.catchings = catchings;
    }

    @Override
    public String toString() {
        return "Profile{" +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", country=" + country +
                '}';
    }
}
