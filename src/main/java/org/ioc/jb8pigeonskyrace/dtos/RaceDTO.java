package org.ioc.jb8pigeonskyrace.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.ioc.jb8pigeonskyrace.utils.annotations.RefExists;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
public record RaceDTO(
        String id,
        @NotBlank String name,
        @NotNull Coordinates startCoordinates,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime startDate,
        @NotNull double targetDistance,
        double tolerance,
        double avgDistance,
        boolean autoAdj,

        @NotNull LocalDateTime closedAt,
        @RefExists(collection = "competitions")
        CompetitionDTO competition,
        List<RankingDTO> rankings) {
}
