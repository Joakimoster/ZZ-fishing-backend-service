package com.example.ZZfishing.api.history.controller;

import com.example.ZZfishing.api.history.service.HistoryService;
import com.example.ZZfishing.api.history.repository.entity.History;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    @Operation(summary = "Fetch all histories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All histories are fetched"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch all histories due to internal error")
    })
    public ResponseEntity<List<History>> getHistories() {
        List<History> history = historyService.getHistories().stream().toList();
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(history, httpStatus);
    }

    @GetMapping("{historyId}")
    @Operation(summary = "Get history by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History fetched by id"),
            @ApiResponse(responseCode = "500", description = "Unable to fetch history due to internal error")
    })
    public ResponseEntity<History> fetchHistoryById(
            @PathVariable("historyId") Long historyId) {
                History historyDB = historyService.fetchHistoryById(historyId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(historyDB, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Register a new history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New history was successfully created"),
            @ApiResponse(responseCode = "500", description = "Unable to create a new history due to internal error")
    })
    public ResponseEntity<History> registerNewHistory(@RequestBody History history) {
        History returnHistory = historyService.addNewHistory(history);
        HttpStatus httpStatus = HttpStatus.CREATED;
        return new ResponseEntity<>(returnHistory, httpStatus);
    }

    @DeleteMapping(path = "{historyId}")
    @Operation(summary = "Delete history by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History was successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Unable to delete the history due to internal error")
    })
    public ResponseEntity<History> deleteHistory(
            @PathVariable ("historyId") Long historyId) {
                historyService.deleteHistory(historyId);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(httpStatus);
    }

    @PutMapping(path = "{historyId}")
    @Operation(summary = "Update history by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History was successfully updated"),
            @ApiResponse(responseCode = "500", description = "Unable to update the history due to internal error")
    })
    public ResponseEntity<History> updateHistory(
            @PathVariable("historyId") Long historyId,
            @RequestBody History history) {
                History returnHistory = historyService.updateHistory(historyId, history);
                HttpStatus httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(returnHistory, httpStatus);
    }
}
