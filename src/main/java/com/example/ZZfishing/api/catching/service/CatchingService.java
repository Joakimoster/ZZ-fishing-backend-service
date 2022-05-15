package com.example.ZZfishing.api.catching.service;

import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingResponseDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.exception.CatchingNotFoundException;
import com.example.ZZfishing.api.catching.mapper.CatchingMapper;
import com.example.ZZfishing.api.catching.repository.CatchingRepository;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.utils.IdUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatchingService implements ICatchingService {

    private final CatchingRepository catchingRepository;
    private final CatchingMapper mapper;

    public CatchingService(
            CatchingRepository catchingRepository,
            ApplicationEventPublisher publisher, CatchingMapper mapper) {
                this.catchingRepository = catchingRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CatchingResponseDto> getCatchings() {
        return mapper.catchingToResponseCatchings(catchingRepository.findAll());
    }

    @Override
    public CatchingResponseDto addNewCatching(CatchingRequestBodyDto catching) {
        return mapper.catchingToResponseCatching(catchingRepository.save(mapper.requestBodyDtoToCatching(catching)));
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
    public CatchingResponseDto getCatchingById(Long catchingId) {
        IdUtil.assertId(catchingId);
        return mapper.catchingToResponseCatching(catchingRepository.findById(catchingId).orElseThrow(
                () -> new CatchingNotFoundException(catchingId)));
    }

    public void updateCatchingFields(Catching catching, CatchingUpdateDto updateCatching) {
        catching.setCatchingDate(updateCatching.getCatchDate());
        catching.setFish(updateCatching.getFish());
    }
}
