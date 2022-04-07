package com.example.ZZfishing.api.fish.service;

import com.example.ZZfishing.api.fish.exception.FishNotDeletedException;
import com.example.ZZfishing.api.fish.exception.FishNotFoundException;
import com.example.ZZfishing.api.fish.repository.FishRepository;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FishService {

    private final FishRepository fishRepository;

    public FishService(
            FishRepository fishRepository,
            ApplicationEventPublisher publisher) {
                this.fishRepository = fishRepository;
    }

    public Optional<Fish> get(long id) {
        IdUtil.assertId(id);
        return fishRepository.findById(id);
    }

    public List<Fish> getFishes() {
        return fishRepository.findAll();
    }

    public Fish addNewFish(Fish fish) {
        return fishRepository.save(fish);
    }

    public void deleteFishById(Long fishId) {
        Optional<Fish> fish = fishRepository.findById(fishId);

        if (fish.isEmpty()) {
            throw new FishNotDeletedException(fishId);
        }
        fishRepository.deleteById(fishId);
    }

    public void deleteFish(Long fishId) {
        boolean exists = fishRepository.existsById(fishId);

        if (!exists) {
            throw new FishNotDeletedException(fishId);
        }
        fishRepository.deleteById(fishId);
    }

    public Fish updateFish(Long fishId, Fish fish) {
        Fish fishDB = fishRepository.findById(fishId).orElseThrow(
                () -> new FishNotFoundException(fishId));

        if (Objects.nonNull(fish.getFishSpecies()) &&
                !"".equalsIgnoreCase(fish.getFishSpecies())) {
                fishDB.setFishSpecies(fish.getFishSpecies());
        }
        if (fish.getLength() != 0) {
            fishDB.setLength(fish.getLength());
        }
        if (fish.getWeight() != 0) {
            fishDB.setWeight(fish.getWeight());
        }
        return fishRepository.save(fishDB);
    }

    public Fish fetchFishById(Long fishId) {
        //IdUtil.assertId(fishId);
        return fishRepository.findById(fishId).orElseThrow(
                () -> new FishNotFoundException(fishId));
    }

    public Fish getFishOrThrow(long fishId)
        throws FishNotFoundException {
            return fishRepository.findById(fishId)
                    .orElseThrow(() -> new FishNotFoundException(fishId));
        }
}
