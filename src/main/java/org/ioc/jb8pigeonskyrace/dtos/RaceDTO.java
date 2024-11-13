package org.ioc.jb8pigeonskyrace.dtos;

import java.time.LocalDateTime;

public record RaceDTO (String id,
                       String name,
                       String latitude,
                       String longitude,
                       LocalDateTime startDate,
                       double avgDistance,
                       LocalDateTime closedAt,
                       CompetitionDTO competition) {
}
