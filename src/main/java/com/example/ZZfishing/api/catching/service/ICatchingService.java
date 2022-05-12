package com.example.ZZfishing.api.catching.service;

import com.example.ZZfishing.api.catching.dto.CatchingReponseDto;
import com.example.ZZfishing.api.catching.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;

import java.util.List;

public interface ICatchingService {
    List<CatchingReponseDto> getCatchings();

    CatchingReponseDto addNewCatching(CatchingRequestBodyDto catching);

    void deleteCatching(Long catchingId);

    Catching updateCatching(Long catchingId, CatchingUpdateDto catching);

    CatchingReponseDto getCatchingById(Long catchingId);
}
