package com.example.ZZfishing.api.catching.mapper;

import com.example.ZZfishing.api.catching.dto.CatchingUpdateDto;
import com.example.ZZfishing.api.catching.repository.entity.Catching;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatchingMapper {

    Catching updateCatchingDtoToCatching(CatchingUpdateDto dto);

    CatchingUpdateDto catchingToUpdateCatching(Catching catching);
}
