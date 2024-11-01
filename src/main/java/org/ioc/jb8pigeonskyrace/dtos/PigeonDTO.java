package org.ioc.jb8pigeonskyrace.dtos;

import org.ioc.jb8pigeonskyrace.utils.enums.Gender;

public record PigeonDTO(String id,
                        String bandNumber,
                        Gender gender,
                        String color,
                        String image,
                        String breederId) {
}
