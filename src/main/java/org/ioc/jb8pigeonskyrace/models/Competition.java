package org.ioc.jb8pigeonskyrace.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "competitions")
public class Competition {
    @Id
    private String id;
    private String name;
    private LocalDate closedAt;

    @DBRef
    private List<Race> races;
}
