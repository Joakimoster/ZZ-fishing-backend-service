package com.example.ZZfishing.api.catching.controller;

import com.example.ZZfishing.api.catching.dto.CatchingReponseDto;
import com.example.ZZfishing.api.catching.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICatchingController {
    ResponseEntity<List<CatchingReponseDto>> getCatchings();

    ResponseEntity<CatchingReponseDto> getCatchingById(Long id);

    ResponseEntity<CatchingReponseDto> registerNewCatching(CatchingRequestBodyDto catching);

    ResponseEntity<Catching> deleteCatching(Long id);

    ResponseEntity<Catching> updateCatching(Long id, CatchingUpdateDto catching);
}
