package com.example.ZZfishing.api.history.service;

import com.example.ZZfishing.api.history.repository.entity.History;

import java.util.List;

public interface IHistoryService {
    List<History> getHistories();

    History addNewHistory(History history);

    void deleteHistory(Long historyId);

    History updateHistory(Long historyId, History history);

    History getHistoryById(Long historyId);
}
