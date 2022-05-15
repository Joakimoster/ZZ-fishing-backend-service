package com.example.ZZfishing.api.catching.mapper;

import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingResponseDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.repository.entity.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatchingMapper {


    Catching updateCatchingDtoToCatching(CatchingUpdateDto dto);

    CatchingUpdateDto catchingToUpdateCatching(Catching catching);

    Catching responseCatchingDtoToCatching(CatchingResponseDto dto);

    @Mapping(source = "fish.id", target = "fishId")
    CatchingResponseDto catchingToResponseCatching(Catching catching);

    //@Mapping(source = "fish.id", target = "fishId")
    List<CatchingResponseDto> catchingToResponseCatchings(List<Catching> catching);

    CatchingRequestBodyDto catchingToRequestBodyDto(Catching catching);

    //@Mapping(source = "profileId", target = "profile.id")
    Catching requestBodyDtoToCatching(CatchingRequestBodyDto dto);
}
