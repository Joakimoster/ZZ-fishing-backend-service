package com.example.ZZfishing.api.fish.controller;

import com.example.ZZfishing.api.fish.service.FishService;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fish")
public class FishController implements IFishController {

    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    @Operation(summary = "Fetch all fishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All fishes are fetched"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch all fishes due to internal error")
    })
    public ResponseEntity<List<Fish>> getFishes() {
        List<Fish> fishList = fishService.getFishes().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(fishList, httpStatus);
    }

    @GetMapping("{fishId}")
    @Operation(summary = "Get fish by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fish fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch fish due to internal error")
    })
    public ResponseEntity<Fish> getFishById(
            @PathVariable("fishId") Long fishId) {
                Fish fishDB = fishService.getFishById(fishId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(fishDB, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Register a new fish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New fish was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new fish due to internal error")
    })
    public ResponseEntity<Fish> registerNewFish(@RequestBody Fish fish) {
        fishService.addNewFish(fish);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(fish, httpStatus);
    }

    @DeleteMapping(path = "{fishId}")
    @Operation(summary = "Delete fish by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fish was successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Unable to delete the fish due to internal error")
    })
    public ResponseEntity<Fish> deleteFish(@PathVariable ("fishId") Long fishId) {
        fishService.deleteFish(fishId);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{fishId}")
    @Operation(summary = "Update fish by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fish was successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to update the fish due to internal error")
    })
    public ResponseEntity<Fish> updateFish(
            @PathVariable ("fishId") Long fishId,
            @RequestBody Fish fish) {
                fishService.updateFish(fishId, fish);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(fish, httpStatus);
    }
}
