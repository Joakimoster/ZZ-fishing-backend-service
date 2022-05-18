package com.example.ZZfishing.api.catching.repository.entity;

import com.example.ZZfishing.api.catching.repository.enums.FishMethod;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import com.example.ZZfishing.api.profile.repository.entity.Profile;
import com.example.ZZfishing.utils.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Catching extends IdEntity{

    @CreationTimestamp private Date catchingDate;
    private boolean released;
    private String location;

    @Enumerated(EnumType.STRING)
    private FishMethod fishMethod;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishingLure_id")
    private FishingLure fishingLure;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fish_id")
    private Fish fish;

    /*@ManyToOne
    @JoinColumn(name = "history_id")
    private History history;*/

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    public Catching() {
    }

    public Catching(Date catchingDate, boolean released, String location, FishMethod fishMethod, FishingLure fishingLure, Fish fish, Profile profile) {
        this.catchingDate = catchingDate;
        this.released = released;
        this.location = location;
        this.fishMethod = fishMethod;
        this.fishingLure = fishingLure;
        this.fish = fish;
        this.profile = profile;
    }
    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    /*public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }*/

    public Date getCatchingDate() {
        return catchingDate;
    }

    public void setCatchingDate(Date catchingDate) {
        this.catchingDate = catchingDate;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean thrownBack) {
        this.released = thrownBack;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public FishMethod getFishMethod() {
        return fishMethod;
    }

    public void setFishMethod(FishMethod fishMethod) {
        this.fishMethod = fishMethod;
    }

    public FishingLure getFishingLure() {
        return fishingLure;
    }

    public void setFishingLure(FishingLure fishingLure) {
        this.fishingLure = fishingLure;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


}
