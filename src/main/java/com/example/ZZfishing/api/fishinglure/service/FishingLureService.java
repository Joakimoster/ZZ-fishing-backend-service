package com.example.ZZfishing.api.fishinglure.service;

import com.example.ZZfishing.api.fish.exception.FishNotFoundException;
import com.example.ZZfishing.api.fishinglure.exception.FishingLureNotDeletedException;
import com.example.ZZfishing.api.fishinglure.exception.FishingLureNotFoundException;
import com.example.ZZfishing.api.fishinglure.repository.FishingLureRepository;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FishingLureService {

    private final FishingLureRepository fishingLureRepository;

    public FishingLureService(
            FishingLureRepository fishingLureRepository) {
                this.fishingLureRepository = fishingLureRepository;
    }

    public List<FishingLure> getFishLures() {
        return fishingLureRepository.findAll();
    }

    public void addNewFishLure(FishingLure fishingLure) {
        fishingLureRepository.save(fishingLure);
    }

    public void deleteFishingLure(Long fishingLureId) {
        boolean exists = fishingLureRepository.existsById(fishingLureId);

        if (!exists) {
            throw new FishingLureNotDeletedException(fishingLureId);
        }
        fishingLureRepository.deleteById(fishingLureId);
    }

    public void updateFishingLure(Long fishingLureId, FishingLure fishingLure) {
        FishingLure fishingLureDB = fishingLureRepository.findById(fishingLureId).
                orElseThrow(() -> new FishingLureNotFoundException(fishingLureId));

        if (Objects.nonNull(fishingLure.getType()) &&
                !"".equalsIgnoreCase(fishingLure.getType())) {
                fishingLureDB.setType(fishingLure.getType());
        }
        if (Objects.nonNull(fishingLure.getLabel()) &&
                !"".equalsIgnoreCase(fishingLure.getLabel())) {
                fishingLureDB.setLabel(fishingLure.getLabel());
        }
        if (fishingLure.getLength() != 0) {
            fishingLureDB.setLength(fishingLure.getLength());
        }
        if (fishingLure.getWeight() != 0) {
            fishingLureDB.setWeight(fishingLure.getWeight());
        }
        fishingLureRepository.save(fishingLureDB);
    }

    public FishingLure fetchFishingLureById(Long fishingLureId) {
        IdUtil.assertId(fishingLureId);
        return fishingLureRepository.findById(fishingLureId).orElseThrow(
                () -> new FishNotFoundException(fishingLureId));
    }
}
