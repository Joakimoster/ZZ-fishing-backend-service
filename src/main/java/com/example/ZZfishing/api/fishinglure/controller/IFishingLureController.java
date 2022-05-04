package com.example.ZZfishing.api.fishinglure.controller;

import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFishingLureController {
    ResponseEntity<List<FishingLure>> getFishingLures();

    ResponseEntity<FishingLure> getFishingLureById(Long fishingLureId);

    ResponseEntity<FishingLure> registerNewFishingLure(FishingLure fishingLure);

    ResponseEntity<FishingLure> deleteFishingLure(Long id);

    ResponseEntity<FishingLure> updateFishingLure(Long id, FishingLure fishingLure);

}
