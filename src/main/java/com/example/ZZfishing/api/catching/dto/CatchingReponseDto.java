package com.example.ZZfishing.api.catching.dto;

import com.example.ZZfishing.api.fish.repository.entity.Fish;

import java.util.Date;

public class CatchingReponseDto {

    private String weather;
    private Long fishId;
    private Date catchDate;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public Date getCatchDate() {
        return catchDate;
    }

    public void setCatchDate(Date catchDate) {
        this.catchDate = catchDate;
    }
}
