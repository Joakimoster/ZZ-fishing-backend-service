package com.example.ZZfishing.api.catching.mapper;

import com.example.ZZfishing.api.catching.dto.CatchingReponseDto;
import com.example.ZZfishing.api.catching.dto.CatchingRequestBodyDto;
import com.example.ZZfishing.api.catching.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatchingMapper {


    Catching updateCatchingDtoToCatching(CatchingUpdateDto dto);

    CatchingUpdateDto catchingToUpdateCatching(Catching catching);

    Catching responseCatchingDtoToCatching(CatchingReponseDto dto);

    @Mapping(source = "fish.id", target = "fishId")
    CatchingReponseDto catchingToResponseCatching(Catching catching);

    //@Mapping(source = "fish.id", target = "fishId")
    List<CatchingReponseDto> catchingToResponseCatchings(List<Catching> catching);

    CatchingRequestBodyDto catchingToRequestBodyDto(Catching catching);

    @Mapping(source = "profileId", target = "profile.id")
    Catching requestBodyDtoToCatching(CatchingRequestBodyDto dto);
}
