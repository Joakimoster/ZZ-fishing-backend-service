package com.example.ZZfishing.api.catching.controller;

import com.example.ZZfishing.api.catching.service.CatchingService;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/catching")
public class CatchingController implements ICatchingController{

    private final CatchingService catchingService;

    public CatchingController(CatchingService catchingService) {
        this.catchingService = catchingService;
    }

    @GetMapping
    @Operation(summary = "Fetch all catchings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All catchings are fetched"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch catchings due to internal error")
    })
    public ResponseEntity<List<Catching>> getCatchings() {
        List<Catching> catchings = catchingService.getCatchings().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(catchings, httpStatus);
    }

    @GetMapping("{catchingId}")
    @Operation(summary = "Get catching by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catching fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch a catching due to internal error")
    })
    public ResponseEntity<Catching> getCatchingById(
            @PathVariable("catchingId") Long catchingId) {
                Catching catchingDB = catchingService.getCatchingById(catchingId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(catchingDB, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Register a new catching")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New catching was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new catching due to internal error")
    })
    public ResponseEntity<Catching> registerNewCatching(@RequestBody Catching catching) {
        Catching returnCatching = catchingService.addNewCatching(catching);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnCatching, httpStatus);
    }

    @DeleteMapping(path = "{catchingId}")
    @Operation(summary = "Delete catching by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catching was successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Unable to delete the catching due to internal error")
    })
    public ResponseEntity<Catching> deleteCatching(
            @PathVariable ("catchingId") Long catchingId) {
                catchingService.deleteCatching(catchingId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{catchingId}")
    @Operation(summary = "Update catching by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catching was successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to update the catching due to internal error")
    })
    public ResponseEntity<Catching> updateCatching(
            @PathVariable("catchingId") Long catchingId,
            @RequestBody Catching catching) {
                Catching returnCatching = catchingService.updateCatching(catchingId, catching);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnCatching, httpStatus);
    }
}
