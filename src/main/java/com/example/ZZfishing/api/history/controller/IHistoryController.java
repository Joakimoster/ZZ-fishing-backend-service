package com.example.ZZfishing.api.history.controller;

import com.example.ZZfishing.api.history.repository.entity.History;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHistoryController {
    ResponseEntity<List<History>> getHistories();

    ResponseEntity<History> getHistoryById(Long Id);

    ResponseEntity<History> registerNewHistory(History history);

    ResponseEntity<History> deleteHistory(Long id);

    ResponseEntity<History> updateHistory(Long id, History history);
}
