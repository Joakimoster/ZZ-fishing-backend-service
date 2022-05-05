package com.example.ZZfishing.api.fishinglure.service;

import com.example.ZZfishing.api.fishinglure.controller.dto.FishingLureDto;
import com.example.ZZfishing.api.fishinglure.exception.FishingLureNotFoundException;
import com.example.ZZfishing.api.fishinglure.mapper.FishingLureMapper;
import com.example.ZZfishing.api.fishinglure.repository.FishingLureRepository;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("junit")

public class FishingLureServiceTest {

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = -1L;

    private final FishingLureRepository repository = mock(FishingLureRepository.class);
    private final ApplicationEventPublisher eventPublisher = mock(ApplicationEventPublisher.class);
    private final FishingLureMapper mapper = mock(FishingLureMapper.class);
    private final FishingLureService service = new FishingLureService(repository, eventPublisher, mapper);

    @Test
    void canGetAllFishingLures() {
        when(repository.findAll()).thenReturn(getFishingLureList());

        List<FishingLure> fishingLureList = service.getFishLures();
        assertThat(fishingLureList.size()).isNotZero();
    }

    @Test
    void canGetFishingLureById() {
        FishingLure fishingLure = getFishingLure(VALID_ID);

        when(repository.findById(VALID_ID))
                .thenReturn(Optional.of(fishingLure));
        assertThat(service.getFishingLureById(VALID_ID)).isEqualTo(fishingLure);
    }

    @Test
    void canGetFishingLureDtoById() {
        FishingLureDto dto = getFishingLureDto(VALID_ID);

        when(repository.existsById(VALID_ID))
                .thenReturn(true);
        when(mapper.toFishingLureDto(repository.getById(VALID_ID)))
                .thenReturn(dto);

        FishingLureDto test = service.getFishingLureDto(VALID_ID);
        assertEquals(dto.getId(), test.getId());
    }

    @Test
    void canVerifyNotExistingFishingLure() {
        FishingLure fishingLure = getFishingLure(VALID_ID);

        when(repository.findById(VALID_ID))
                .thenReturn(Optional.of(fishingLure));
        assertThrows(FishingLureNotFoundException.class,
                () -> service.getFishingLureById(INVALID_ID));
    }

    @Test
    void canThrowFishingLureNotFoundException() {
        assertThatExceptionOfType(FishingLureNotFoundException.class).isThrownBy(
                () -> service.getFishingLureById(INVALID_ID))
                .withMessage("Unable to find fishing lure by id: " + INVALID_ID);
    }

    private FishingLureDto getFishingLureDto(long id) {
        FishingLureDto dto = new FishingLureDto();
        dto.setId(id);
        dto.setLabel("Perch Fight");
        dto.setType("Strong lure");
        return dto;
    }

    private FishingLure getFishingLure(long id) {
        FishingLure fishingLure = new FishingLure();
        fishingLure.setId(id);
        fishingLure.setLabel("Perch Fight");
        fishingLure.setType("Strong lure");
        fishingLure.setLength(10);
        fishingLure.setWeight(5);
        return fishingLure;
    }

    private List<FishingLure> getFishingLureList() {
        List<FishingLure> list = new ArrayList<>();
        list.add(new FishingLure("Perch Fight", 5, 10, "Strong lure"));
        list.add(new FishingLure("Salmon High", 3, 12, "Shallow water lure"));
        return list;
    }
}