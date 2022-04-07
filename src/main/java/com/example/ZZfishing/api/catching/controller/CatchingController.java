package com.example.ZZfishing.api.catching.controller;

import com.example.ZZfishing.api.catching.service.CatchingService;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/catching")
public class CatchingController {

    private final CatchingService catchingService;

    public CatchingController(CatchingService catchingService) {
        this.catchingService = catchingService;
    }

    @GetMapping
    public List<Catching> getCatchings() {
        return catchingService.getCatchings();
    }

    @GetMapping("{catchingId}")
    public Catching fetchCatchingById(@PathVariable("catchingId") Long catchingId) {
        return catchingService.fetchCatchingById(catchingId);
    }

    @PostMapping
    public void registerNewCatching(@RequestBody Catching catching) {
        catchingService.addNewCatching(catching);
    }

    @DeleteMapping(path = "{catchingId}")
    public void deleteCatching(@PathVariable ("catchingId") Long catchingId) {
        catchingService.deleteCatching(catchingId);
    }

    @PutMapping(path = "{catchingId}")
    public void updateCatching(@PathVariable("catchingId") Long catchingId, @RequestBody Catching catching) {
        catchingService.updateCatching(catchingId, catching);
    }
}
