package com.example.ZZfishing.api.fishinglure.service;

import com.example.ZZfishing.api.fish.repository.FishRepository;
import com.example.ZZfishing.api.fishinglure.repository.FishingLureRepository;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FishingLureServiceTest {

    @Mock private FishingLureRepository fishingLureRepository;
    private AutoCloseable autoCloseable;
    private FishingLureService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new FishingLureService(fishingLureRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllFishingLures() {
        underTest.getFishLures();

        verify(fishingLureRepository.findAll());
    }
}