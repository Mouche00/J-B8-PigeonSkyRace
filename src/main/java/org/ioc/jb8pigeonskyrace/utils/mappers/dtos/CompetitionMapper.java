package org.ioc.jb8pigeonskyrace.utils.mappers.dtos;

import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionDTO toDTO(Competition competition);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "races", ignore = true)
    })
    Competition toCompetition(CompetitionDTO competitionDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompetitionFromDto(CompetitionDTO dto, @MappingTarget Competition entity);

}
