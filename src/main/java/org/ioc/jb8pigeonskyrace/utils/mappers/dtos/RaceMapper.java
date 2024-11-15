package org.ioc.jb8pigeonskyrace.utils.mappers.dtos;

import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDTO(Race race);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Race toRace(RaceDTO raceDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRaceFromDto(RaceDTO dto, @MappingTarget Race entity);
}
