package com.example.ZZfishing.api.fish.service;

import com.example.ZZfishing.api.catching.repository.entity.Catching;
import com.example.ZZfishing.api.fish.exception.FishNotDeletedException;
import com.example.ZZfishing.api.fish.exception.FishNotFoundException;
import com.example.ZZfishing.api.fish.repository.FishRepository;
import com.example.ZZfishing.api.fish.repository.entity.Fish;
import com.example.ZZfishing.api.fish.repository.enums.FishSpecies;
import com.example.ZZfishing.model.exception.IdNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FishServiceTest {

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = -1L;

    private final ApplicationEventPublisher publisher =
            mock(ApplicationEventPublisher.class);

    @Mock
    private FishRepository fishRepository;

    private FishService underTest;

    private List<Fish> fishList;

    @BeforeEach
    void setUp() {
        underTest = new FishService(fishRepository, publisher);
        this.fishList = new ArrayList<>();
        this.fishList.add(new Fish(5, 10, FishSpecies.SALMON, new Catching()));
        this.fishList.add(new Fish(2, 4, FishSpecies.TUNA, new Catching()));
        this.fishList.add(new Fish(3, 5, FishSpecies.PIKE, new Catching()));
    }

    @Test
    void canGetAllFish() {
        when(fishRepository.findAll()).thenReturn(fishList);

        assertThat(underTest.getFishes()).isEqualTo(fishList);
    }

    @Test
    void canGetFishById() {
        Fish fish = getFish(VALID_ID);

        when(fishRepository.findById(VALID_ID))
                .thenReturn(Optional.of(fish));

        assertThat(underTest.getFishById(VALID_ID)).isEqualTo(fish);
    }

    @Test
    void canAddNewFish() {
        //given
        Fish fish = getFish(VALID_ID);
        //when
        underTest.addNewFish(fish);

        //then
        ArgumentCaptor<Fish> fishArgumentCaptor =
                ArgumentCaptor.forClass(Fish.class);
        verify(fishRepository).save(fishArgumentCaptor.capture());

        Fish capturedFish = fishArgumentCaptor.getValue();
        assertThat(capturedFish).isEqualTo(fish);
    }

    @Test
    @Disabled
    void canDeleteFish() {
        Fish fish = getFish(VALID_ID);

        when(fishRepository.findById(VALID_ID))
                .thenReturn(Optional.of(fish));

        underTest.deleteFishById(VALID_ID);

        verify(fishRepository).deleteById(VALID_ID);
    }

    @Test
    void canDeleteFishById() {
        //given
        given(fishRepository.existsById(VALID_ID))
                .willReturn(true);

        //when
        underTest.deleteFish(VALID_ID);

        //then
        verify(fishRepository).deleteById(VALID_ID);
    }

    @Test
    void canUpdateFishById() {
        //given
        Fish fish = getFish(VALID_ID);
        fish.setFishSpecies(FishSpecies.SALMON);
        fish.setLength(11);
        fish.setWeight(20);

        given(fishRepository.findById(VALID_ID))
                .willReturn(Optional.of(fish));

        //when
        underTest.updateFish(VALID_ID, fish);

        verify(fishRepository).save(fish);
    }

    @Test
    void canFindFishByIdOrThrowFishNotFoundException() {
        assertThatExceptionOfType(FishNotFoundException.class).isThrownBy(
                () -> underTest.getFishById(INVALID_ID))
                .withMessage("Unable to find fish by id: " + INVALID_ID);
    }

    @Test
    void canDeleteFishByIdOrThrowFishNotDeletedException() {
        assertThatExceptionOfType(FishNotDeletedException.class).isThrownBy(
                () -> underTest.deleteFishById(INVALID_ID))
                .withMessage("Unable to delete fish by id: " + INVALID_ID);
    }

    @Test
    void canVerifyFishIdOrThrowInvalidFishId() {
        FishNotFoundException thrown =
                Assertions.assertThrows(
                        FishNotFoundException.class,
                        () -> {
                            underTest.getFishOrThrow(INVALID_ID);
                        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
        Assertions.assertEquals(
                String.format("Unable to find fish by id: %s", INVALID_ID),
                thrown.getMessage());
    }

    @Test
    void canVerifyInvalidId() {
        Assertions.assertThrows(IdNotValidException.class,
                () -> underTest.get(-1));
        Assertions.assertThrows(IdNotValidException.class,
                () -> underTest.get(0));
    }

    private void setupRepositoryList() {
        when(fishRepository.findAll()).thenReturn(getFishList());
    }

    private List<Fish> getFishList() {
        List <Fish> fishList = new ArrayList<>();
        fishList.add(getFish(1));
        fishList.add(getFish(2));
        return fishList;
    }

    private Fish getFish(long id) {
        Fish fish = new Fish();
        fish.setId(id);
        fish.setLength(10);
        fish.setWeight(25);
        fish.setFishSpecies(FishSpecies.SALMON);
        return fish;
    }

    private Fish getValidFish() {
        return getFish(VALID_ID);
    }
}