package com.example.ZZfishing.api.profile.repository.entity;

import com.example.ZZfishing.api.user.repository.entity.User;
import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties( {"id"} )
public class Profile extends IdEntity {

    private String firstName;
    private String lastName;
    private String email;
    private int age;

    @OneToOne(mappedBy = "profile",cascade= CascadeType.ALL)
    private User user;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
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

    @Override
    public String toString() {
        return "Profile{" +
                ", email='" + email + '\'' +
                ", email='" + firstName + '\'' +
                ", email='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
