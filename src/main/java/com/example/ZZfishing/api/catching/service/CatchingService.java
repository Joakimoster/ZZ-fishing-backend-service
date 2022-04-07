package com.example.ZZfishing.api.catching.service;

import com.example.ZZfishing.api.catching.exception.CatchingNotFoundException;
import com.example.ZZfishing.api.catching.repository.CatchingRepository;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CatchingService {

    private final CatchingRepository catchingRepository;

    public CatchingService(
            CatchingRepository catchingRepository,
            ApplicationEventPublisher publisher) {
                this.catchingRepository = catchingRepository;
    }

    public List<Catching> getCatchings() {
        return catchingRepository.findAll();
    }

    public void addNewCatching(Catching catching) {
        catchingRepository.save(catching);
    }

    public void deleteCatching(Long catchingId) {
        boolean exists = catchingRepository.existsById(catchingId);

        if (!exists) {
            throw new CatchingNotFoundException(catchingId);
        }
        catchingRepository.deleteById(catchingId);
    }

    public void updateCatching(Long catchingId, Catching catching) {
        Catching catchingDB = catchingRepository.findById(catchingId).orElseThrow(
                () -> new CatchingNotFoundException(catchingId));

        if (Objects.nonNull(catching.getFish()) &&
                !"".equalsIgnoreCase(catching.getFish())) {
                catchingDB.setFish(catching.getFish());
        }
        catchingRepository.save(catchingDB);
    }

    public Catching fetchCatchingById(Long catchingId) {
        IdUtil.assertId(catchingId);
        return catchingRepository.findById(catchingId).orElseThrow(
                () -> new CatchingNotFoundException(catchingId));
    }
}
