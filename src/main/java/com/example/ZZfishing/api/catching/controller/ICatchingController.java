package com.example.ZZfishing.api.catching.controller;

import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingResponseDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICatchingController {
    ResponseEntity<List<CatchingResponseDto>> getCatchings();

    ResponseEntity<CatchingResponseDto> getCatchingById(Long id);

    ResponseEntity<CatchingResponseDto> registerNewCatching(CatchingRequestBodyDto catching);

    ResponseEntity<Catching> deleteCatching(Long id);

    ResponseEntity<Catching> updateCatching(Long id, CatchingUpdateDto catching);
}
