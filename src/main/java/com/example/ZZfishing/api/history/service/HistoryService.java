package com.example.ZZfishing.api.history.service;

import com.example.ZZfishing.api.history.exception.HistoryNotDeletedException;
import com.example.ZZfishing.api.history.exception.HistoryNotFoundException;
import com.example.ZZfishing.api.history.repository.HistoryRepository;
import com.example.ZZfishing.api.history.repository.entity.History;
import com.example.ZZfishing.api.profile.exception.ProfileNotFoundException;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(
            HistoryRepository historyRepository,
            ApplicationEventPublisher publisher) {
                this.historyRepository = historyRepository;
    }

    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    public void addNewHistory(History history) {
        historyRepository.save(history);
    }

    public void deleteHistory(Long historyId) {
        boolean exists = historyRepository.existsById(historyId);

        if(!exists) {
            throw new HistoryNotDeletedException(historyId);
        }
        historyRepository.deleteById(historyId);
    }

    public void updateHistory(Long historyId, History history) {
        History historyDB = historyRepository.findById(historyId).orElseThrow(
                () -> new ProfileNotFoundException(historyId));

        if (Objects.nonNull(history.getAge())) {
            historyDB.setAge(history.getAge());
        }
        if (Objects.nonNull(history.getCatchings())) {
            historyDB.setCatchings(history.getCatchings());
        }
        historyRepository.save(historyDB);
    }

    public History fetchHistoryById(Long historyId) {
        IdUtil.assertId(historyId);
        return historyRepository.findById(historyId).orElseThrow(
                () -> new HistoryNotFoundException(historyId));
    }
}
