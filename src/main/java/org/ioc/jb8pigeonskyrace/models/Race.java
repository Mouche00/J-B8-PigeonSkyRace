package org.ioc.jb8pigeonskyrace.models;

import lombok.Data;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

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

    @DBRef
    private Competition competition;

    @DBRef
    private List<Ranking> rankings;
}
