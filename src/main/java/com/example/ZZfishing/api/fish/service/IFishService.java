package com.example.ZZfishing.api.fish.service;

import com.example.ZZfishing.api.fish.exception.FishNotFoundException;
import com.example.ZZfishing.api.fish.repository.entity.Fish;

import java.util.List;
import java.util.Optional;

public interface IFishService {
    Optional<Fish> get(long id);

    List<Fish> getFishes();

    Fish addNewFish(Fish fish);

    void deleteFishById(Long fishId);

    void deleteFish(Long fishId);

    Fish updateFish(Long fishId, Fish fish);

    Fish getFishById(Long fishId);

    Fish getFishOrThrow(long fishId)
            throws FishNotFoundException;
}
