package org.ioc.jb8pigeonskyrace.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record CompetitionDTO (String id,
                              String name,
                              LocalDateTime closedAt,
                              List<RaceDTO> races) {
}
