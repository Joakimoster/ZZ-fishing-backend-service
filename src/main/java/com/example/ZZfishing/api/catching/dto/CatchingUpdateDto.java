package com.example.ZZfishing.api.catching.dto;

import com.example.ZZfishing.api.fish.repository.entity.Fish;

import java.util.Date;

public class CatchingUpdateDto {

    private String weather;
    private Fish fish;
    private Date catchDate;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Date getCatchDate() {
        return catchDate;
    }

    public void setCatchDate(Date catchDate) {
        this.catchDate = catchDate;
    }
}
