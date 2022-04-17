package com.example.ZZfishing.api.fishinglure.controller;

import com.example.ZZfishing.api.fishinglure.mapper.FishingLureMapper;
import com.example.ZZfishing.api.fishinglure.service.FishingLureService;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Fetch all fishing lures")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All fishing lures are fetched"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch all fishing lures due to internal error")
    })
    public ResponseEntity<List<FishingLure>> getFishingLures() {
        List<FishingLure> fishingLures = fishingLureService.getFishLures().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(fishingLures, httpStatus);
    }

    @GetMapping("{fishingLureId}")
    @Operation(summary = "Get fishing lure by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fishing lure fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch fishing lure due to internal error")
    })
    public ResponseEntity<FishingLure> fetchFishingLureById(
            @PathVariable ("fishingLureId") Long fishingLureId) {
                FishingLure fishingLureDB = fishingLureService.fetchFishingLureById(fishingLureId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(fishingLureDB, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Register a new fishing lure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New fishing lure was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new fishing lure due to internal error")
    })
    public ResponseEntity<FishingLure> registerNewFishLure(@RequestBody FishingLure fishingLure) {
        FishingLure returnFishingLure = fishingLureService.addNewFishLure(fishingLure);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnFishingLure, httpStatus);
    }

    @DeleteMapping(path = "{fishingLureId}")
    @Operation(summary = "Delete fishing lure by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fishing lure was successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Unable to delete the fishing lure due to internal error")
    })
    public ResponseEntity<FishingLure> deleteFishLure(
            @PathVariable ("fishingLureId") Long fishingLureId) {
                fishingLureService.deleteFishingLure(fishingLureId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{fishingLureId}")
    @Operation(summary = "Update fishing lure by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fishing lure was successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to update the fishing lure due to internal error")
    })
    public ResponseEntity<FishingLure> updateFishingLure(
            @PathVariable ("fishingLureId") Long fishingLureId,
            @RequestBody FishingLure fishingLure) {
                FishingLure returnFishingLure = fishingLureService.updateFishingLure(fishingLureId, fishingLure);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnFishingLure, httpStatus);
    }
}
