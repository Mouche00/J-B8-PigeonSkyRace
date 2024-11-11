package org.ioc.jb8pigeonskyrace.dto;

public record BreederDto(  String id,
                           String username,
                           String loftName,
                           double loftLatitude,
                           double loftLongitude,
                           double finalScore) {

}
