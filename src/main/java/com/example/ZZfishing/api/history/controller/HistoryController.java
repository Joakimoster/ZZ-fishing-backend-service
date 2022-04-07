package com.example.ZZfishing.api.history.controller;

import com.example.ZZfishing.api.history.service.HistoryService;
import com.example.ZZfishing.api.history.repository.entity.History;
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
    public List<History> getHistories() {
        return historyService.getHistories();
    }

    @GetMapping("{historyId}")
    public History fetchHistoryById(@PathVariable("historyId") Long historyId) {
        return historyService.fetchHistoryById(historyId);
    }

    @PostMapping
    public void registerNewHistory(@RequestBody History history) {
        historyService.addNewHistory(history);
    }

    @DeleteMapping(path = "{historyId}")
    public void deleteProfile(@PathVariable ("historyId") Long historyId) {
        historyService.deleteHistory(historyId);
    }

    @PutMapping(path = "{historyId}")
    public void updateHistory(
            @PathVariable("historyId") Long historyId,
            @RequestBody History history) {
                historyService.updateHistory(historyId, history);
    }
}
