package org.ioc.jb8pigeonskyrace.dtos;

public record BreederDTO(String id,
                         String username,
                         String password,
                         String loftName,
                         double loftLatitude,
                         double loftLongitude) {
}
