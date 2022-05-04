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
public class HistoryService implements IHistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(
            HistoryRepository historyRepository,
            ApplicationEventPublisher publisher) {
                this.historyRepository = historyRepository;
    }

    @Override
    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    @Override
    public History addNewHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public void deleteHistory(Long historyId) {
        boolean exists = historyRepository.existsById(historyId);

        if(!exists) {
            throw new HistoryNotDeletedException(historyId);
        }
        historyRepository.deleteById(historyId);
    }

    @Override
    public History updateHistory(Long historyId, History history) {
        History historyDB = historyRepository.findById(historyId).orElseThrow(
                () -> new ProfileNotFoundException(historyId));

        if (Objects.nonNull(history.getAge())) {
            historyDB.setAge(history.getAge());
        }
        if (Objects.nonNull(history.getCatchings())) {
            historyDB.setCatchings(history.getCatchings());
        }
        return historyRepository.save(historyDB);
    }

    @Override
    public History getHistoryById(Long historyId) {
        IdUtil.assertId(historyId);
        return historyRepository.findById(historyId).orElseThrow(
                () -> new HistoryNotFoundException(historyId));
    }
}
