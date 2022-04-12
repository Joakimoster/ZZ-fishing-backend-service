package com.example.ZZfishing.api.fishinglure.controller;

import com.example.ZZfishing.api.fishinglure.mapper.FishingLureMapper;
import com.example.ZZfishing.api.fishinglure.service.FishingLureService;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/v1/fishingLure")
public class FishingLureController {

    private final FishingLureService fishingLureService;
    private final FishingLureMapper mapper;

    public FishingLureController(FishingLureService fishingLureService, FishingLureMapper mapper) {
        this.fishingLureService = fishingLureService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<FishingLure> getFishingLures() {
        return fishingLureService.getFishLures();
    }

    @GetMapping("{fishingLureId}")
    public FishingLure fetchFishingLureById(
            @PathVariable ("fishingLureId") Long fishingLureId) {
                return fishingLureService.fetchFishingLureById(fishingLureId);
    }

    @PostMapping
    public void registerNewFishLure(@RequestBody FishingLure fishingLure) {
        fishingLureService.addNewFishLure(fishingLure);
    }

    @DeleteMapping(path = "{fishingLureId}")
    public void deleteFishLure(
            @PathVariable ("fishingLureId") Long fishingLureId) {
                fishingLureService.deleteFishingLure(fishingLureId);
    }

    @PutMapping(path = "{fishingLureId}")
    public void updateFishingLure(
            @PathVariable ("fishingLureId") Long fishingLureId,
            @RequestBody FishingLure fishingLure) {
                fishingLureService.updateFishingLure(fishingLureId, fishingLure);
    }
}
