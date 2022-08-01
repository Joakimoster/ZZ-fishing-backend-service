package com.example.ZZfishing.api.catching.repository.entity.dto;

import java.util.Date;

public class CatchingResponseDto {

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
