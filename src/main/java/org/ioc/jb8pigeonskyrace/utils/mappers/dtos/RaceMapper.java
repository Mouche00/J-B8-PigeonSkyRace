package org.ioc.jb8pigeonskyrace.utils.mappers.dtos;

import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDTO(Race race);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "rankings", ignore = true),
            @Mapping(target = "competition", ignore = true)
    })
    Race toRace(RaceDTO raceDTO);
}
