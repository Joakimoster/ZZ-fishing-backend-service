package com.example.ZZfishing.api.catching.service;

import com.example.ZZfishing.api.catching.repository.entity.Catching;

import java.util.List;

public interface ICatchingService {
    List<Catching> getCatchings();

    Catching addNewCatching(Catching catching);

    void deleteCatching(Long catchingId);

    Catching updateCatching(Long catchingId, Catching catching);

    Catching getCatchingById(Long catchingId);
}
