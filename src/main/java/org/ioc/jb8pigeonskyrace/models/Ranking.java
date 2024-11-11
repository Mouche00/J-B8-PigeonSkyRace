package org.ioc.jb8pigeonskyrace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "rankings")
public class Ranking {
    @Id
    private String id;

    private LocalTime startTime;
    private double distance;
    private double adjustedSpeed;
    private double score;

    @DBRef
    private Pigeon pigeon;

    @DBRef
    private Race race;
}
