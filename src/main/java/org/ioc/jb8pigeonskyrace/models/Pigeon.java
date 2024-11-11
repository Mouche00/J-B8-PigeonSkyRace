package org.ioc.jb8pigeonskyrace.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ioc.jb8pigeonskyrace.utils.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pigeons")
@Data
public class Pigeon {
    @Id
    private String id;

    private String bandNumber;

    @NotEmpty
    private Gender gender;

    @NotBlank
    private String birthYear;
    private String color;
    private String image;

    @DBRef(lazy = false)
    private Breeder breeder;

    @DBRef
    private List<Ranking> rankings;
}
