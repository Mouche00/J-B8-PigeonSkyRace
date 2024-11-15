package org.ioc.jb8pigeonskyrace.dtos;

import lombok.Builder;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;

import java.util.List;

@Builder
public record BreederDTO(String id,
                         String username,
                         String password,
                         String loftName,
                         Coordinates loftCoordinates,
                         double finalScore,
                         List<PigeonDTO> pigeons) {
}
