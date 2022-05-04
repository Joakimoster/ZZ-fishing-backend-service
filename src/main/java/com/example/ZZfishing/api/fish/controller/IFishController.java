package com.example.ZZfishing.api.fish.controller;

import com.example.ZZfishing.api.fish.repository.entity.Fish;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFishController {
    ResponseEntity<List<Fish>> getFishes();

    ResponseEntity<Fish> getFishById(Long fishId);

    ResponseEntity<Fish> registerNewFish(Fish fish);

    ResponseEntity<Fish> deleteFish(Long id);

    ResponseEntity<Fish> updateFish(Long id, Fish fish);
}
