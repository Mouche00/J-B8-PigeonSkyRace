package org.ioc.jb8pigeonskyrace.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.ioc.jb8pigeonskyrace.utils.enums.Gender;

@Builder
public record PigeonDTO(String id,
                        String bandNumber,
                        @NotEmpty Gender gender,
                        @NotBlank String birthYear,
                        String color,
                        String image,
                        BreederDTO breeder) {

    public PigeonDTO withBreederId(String breederId) {
        return new PigeonDTO(id, bandNumber, gender, birthYear, color, image, BreederDTO.builder().id(breederId).build());
    }


}