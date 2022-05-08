package com.example.ZZfishing.api.catching.service;

import com.example.ZZfishing.api.catching.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.exception.CatchingNotFoundException;
import com.example.ZZfishing.api.catching.repository.CatchingRepository;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CatchingService implements ICatchingService {

    private final CatchingRepository catchingRepository;

    public CatchingService(
            CatchingRepository catchingRepository,
            ApplicationEventPublisher publisher) {
                this.catchingRepository = catchingRepository;
    }

    @Override
    public List<Catching> getCatchings() {
        return catchingRepository.findAll();
    }

    @Override
    public Catching addNewCatching(Catching catching) {
        return catchingRepository.save(catching);
    }

    @Override
    public void deleteCatching(Long catchingId) {
        boolean exists = catchingRepository.existsById(catchingId);

        if (!exists) {
            throw new CatchingNotFoundException(catchingId);
        }
        catchingRepository.deleteById(catchingId);
    }

    @Override
    public Catching updateCatching(Long catchingId, CatchingUpdateDto updatedCatching) {
        Catching catchingDB = catchingRepository.findById(catchingId).orElseThrow(
                () -> new CatchingNotFoundException(catchingId));

        /*if (Objects.nonNull(catching.getFish()) &&
                !"".equalsIgnoreCase(catching.getFish())) {
                catchingDB.setFish(catching.getFish());
        }*/

        updateCatchingFields(catchingDB,updatedCatching);
        return catchingRepository.save(catchingDB);
    }

    @Override
    public Catching getCatchingById(Long catchingId) {
        IdUtil.assertId(catchingId);
        return catchingRepository.findById(catchingId).orElseThrow(
                () -> new CatchingNotFoundException(catchingId));
    }

    public void updateCatchingFields(Catching catching, CatchingUpdateDto updateCatching) {
        catching.setCatchDate(updateCatching.getCatchDate());
        catching.setFish(updateCatching.getFish());
        catching.setWeather(updateCatching.getWeather());
    }
}
