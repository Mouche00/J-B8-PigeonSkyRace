package org.ioc.jb8pigeonskyrace.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record BreederDTO(String id,
                         String username,
                         String password,
                         String loftName,
                         double loftLatitude,
                         double loftLongitude,
                         double finalScore,
                         List<PigeonDTO> pigeons) {
}
