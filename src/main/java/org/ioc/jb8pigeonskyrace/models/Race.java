package org.ioc.jb8pigeonskyrace.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Document(collection = "races")
public class Race {
    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private LocalDateTime startDate;
    private double avgDistance;
    private LocalDateTime closedAt;

    @DBRef
    private Competition competition;

    @DBRef
    private List<Ranking> rankings;
}
