package org.ioc.jb8pigeonskyrace.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record CompetitionDTO (String id,
                              String name,
                              @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
                              LocalDateTime closedAt,
                              List<RaceDTO> races) {
}
