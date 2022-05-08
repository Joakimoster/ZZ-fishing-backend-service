package com.example.ZZfishing.api.catching.controller;

import com.example.ZZfishing.api.catching.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICatchingController {
    ResponseEntity<List<Catching>> getCatchings();

    ResponseEntity<Catching> getCatchingById(Long id);

    ResponseEntity<Catching> registerNewCatching(Catching catching);

    ResponseEntity<Catching> deleteCatching(Long id);

    ResponseEntity<Catching> updateCatching(Long id, CatchingUpdateDto catching);
}
