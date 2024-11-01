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

    @Mappings({
        @Mapping(target = "breederId", source = "breeder.id")
    })
    PigeonDTO toDTO(Pigeon pigeon);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "breeder.id", source = "breederId")
    })
    Pigeon toPigeon(PigeonDTO pigeonDTO);
}
