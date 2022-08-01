package com.example.ZZfishing.api.catching.repository.entity.dto;

import com.example.ZZfishing.api.catching.repository.enums.FishMethod;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import java.util.Date;

public class CatchingRequestBodyDto {

    private Long profileId;
    private Date catchingDate;
    private boolean released;
    private String location;
    private FishMethod fishMethod;
    private FishingLure fishingLure;
    private Fish fish;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Date getCatchingDate() {
        return catchingDate;
    }

    public void setCatchingDate(Date catchingDate) {
        this.catchingDate = catchingDate;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
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

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }
}
