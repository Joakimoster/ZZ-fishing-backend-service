package com.example.ZZfishing.api.user.repository.entity;

import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "\"User\"")
@JsonIgnoreProperties( {"id"} )
public class User extends IdEntity {

    private String password;
    private String email;

    @OneToOne(mappedBy = "user", cascade= CascadeType.ALL)
    private Profile profile;

    public User() {
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
