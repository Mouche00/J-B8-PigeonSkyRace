package org.ioc.jb8pigeonskyrace.utils.mappers.dtos;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PigeonMapper {
    PigeonMapper INSTANCE = Mappers.getMapper(PigeonMapper.class);

    // ResponseDTO
    PigeonDTO toDTO(Pigeon pigeon);

    // RequestDTO
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "bandNumber", ignore = true),
    })
    Pigeon toPigeon(PigeonDTO pigeonDTO);
}
