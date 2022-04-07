package com.example.ZZfishing.api.fish.controller;

import com.example.ZZfishing.api.fish.service.FishService;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fish")
public class FishController {

    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public ResponseEntity<List<Fish>> getFishes() {
        List<Fish> fishList = fishService.getFishes().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(fishList, httpStatus);
    }

    @GetMapping("{fishId}")
    public ResponseEntity<Fish> fetchFishById(@PathVariable("fishId") Long fishId) {
        Fish fishDB= fishService.fetchFishById(fishId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(fishDB, httpStatus);
    }

    @PostMapping
    public ResponseEntity<Fish> registerNewFish(@RequestBody Fish fish) {
        fishService.addNewFish(fish);
        return new ResponseEntity<>(fish, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{fishId}")
    public ResponseEntity<Fish> deleteFish(@PathVariable ("fishId") Long fishId) {
        fishService.deleteFish(fishId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{fishId}")
    public ResponseEntity<Fish> updateFish(
            @PathVariable ("fishId") Long fishId,
            @RequestBody Fish fish) {
                fishService.updateFish(fishId, fish);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(fish, httpStatus);
    }
}
