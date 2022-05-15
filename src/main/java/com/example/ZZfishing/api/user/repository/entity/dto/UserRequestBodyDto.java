package com.example.ZZfishing.api.user.repository.entity.dto;

import com.example.ZZfishing.api.profile.repository.entity.Profile;

public class UserRequestBodyDto {

    //private Long profileId;
    private String email;
    private String password;
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /*public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
