package com.example.ZZfishing.api.catching.service;

import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingResponseDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;

import java.util.List;

public interface ICatchingService {
    List<CatchingResponseDto> getCatchings();

    CatchingResponseDto addNewCatching(CatchingRequestBodyDto catching);

    void deleteCatching(Long catchingId);

    Catching updateCatching(Long catchingId, CatchingUpdateDto catching);

    CatchingResponseDto getCatchingById(Long catchingId);
}
