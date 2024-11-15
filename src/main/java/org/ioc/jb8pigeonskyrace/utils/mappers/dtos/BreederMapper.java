package org.ioc.jb8pigeonskyrace.utils.mappers.dtos;

import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BreederMapper {
    BreederMapper INSTANCE = Mappers.getMapper(BreederMapper.class);

    // ResponseDTO
    @Mapping(target = "password", ignore = true)
    BreederDTO toDTO(Breeder breeder);

    // RequestDTO
    @Mapping(target = "id", ignore = true)
    Breeder toBreeder(BreederDTO breederDTO);
}
