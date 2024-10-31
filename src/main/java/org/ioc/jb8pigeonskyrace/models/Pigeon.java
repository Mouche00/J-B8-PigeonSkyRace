package org.ioc.jb8pigeonskyrace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ioc.jb8pigeonskyrace.utils.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "pigeons")
@Data
public class Pigeon {
    @Id
    private String id;

    private String bandNumber;
    private Gender gender;
    private String color;
    private String image;

    @DBRef
    private Breeder breeder;
}
