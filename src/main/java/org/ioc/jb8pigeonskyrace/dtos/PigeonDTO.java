package org.ioc.jb8pigeonskyrace.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.ioc.jb8pigeonskyrace.utils.enums.Gender;

public record PigeonDTO(String id,
                        String bandNumber,
                        @NotEmpty Gender gender,
                        @NotBlank String birthYear,
                        String color,
                        String image,
                        BreederDTO breeder) {
}
