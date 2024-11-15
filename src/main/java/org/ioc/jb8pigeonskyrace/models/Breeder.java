package org.ioc.jb8pigeonskyrace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "breeders")
public class Breeder {
    @Id
    private String id;

    private String username;
    private String password;
    private String loftName;
    private Coordinates loftCoordinates;
    private double finalScore;

    @DBRef
    private List<Pigeon> pigeons;
}
