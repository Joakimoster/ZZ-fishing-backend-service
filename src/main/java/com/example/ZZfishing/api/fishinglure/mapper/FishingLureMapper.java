package com.example.ZZfishing.api.fishinglure.mapper;

import com.example.ZZfishing.api.fishinglure.controller.dto.FishingLureDto;
import com.example.ZZfishing.api.fishinglure.repository.entity.FishingLure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FishingLureMapper {

    FishingLureDto toFishingLureDto(FishingLure fishingLure);

    FishingLure toFishingLure(FishingLureDto dto);
}
