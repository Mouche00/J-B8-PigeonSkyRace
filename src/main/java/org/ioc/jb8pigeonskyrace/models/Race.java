package org.ioc.jb8pigeonskyrace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ioc.jb8pigeonskyrace.utils.Coordinates;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "races")
public class Race {
    @Id
    private String id;
    private String name;
    private Coordinates startCoordinates;
    private LocalDateTime startDate;
    private double targetDistance;
    private double tolerance;
    private double avgDistance;
    private LocalDateTime closedAt;
    private boolean autoAdj;

    @DBRef(lazy = false)
    private Competition competition;

    @DBRef
    private List<Ranking> rankings;
}
