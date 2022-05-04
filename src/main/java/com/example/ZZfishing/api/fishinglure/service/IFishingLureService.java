package com.example.ZZfishing.api.fishinglure.service;

import com.example.ZZfishing.api.fishinglure.controller.dto.FishingLureDto;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;

import java.util.List;

public interface IFishingLureService {
    List<FishingLure> getFishLures();

    FishingLure addNewFishLure(FishingLure fishingLure);

    void deleteFishingLure(Long fishingLureId);

    FishingLure updateFishingLure(Long fishingLureId, FishingLure fishingLure);

    FishingLure getFishingLureById(Long fishingLureId);

    FishingLureDto getFishingLureDto(long id);
}
