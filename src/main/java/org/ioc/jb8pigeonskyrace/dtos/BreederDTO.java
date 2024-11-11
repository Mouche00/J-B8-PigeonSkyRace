package org.ioc.jb8pigeonskyrace.dtos;

import java.util.List;

public record BreederDTO(String id,
                         String username,
                         String password,
                         String loftName,
                         double loftLatitude,
                         double loftLongitude,
                         double finalScore,
                         List<PigeonDTO> pigeons) {
}
