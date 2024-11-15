package org.ioc.jb8pigeonskyrace.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.ioc.jb8pigeonskyrace.utils.Coordinates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
public record RaceDTO(
        String id,
        @NotBlank String name,
        @NotNull Coordinates startCoordinates,
        @NotNull LocalDateTime startDate,
        @NotNull double targetDistance,
        double avgDistance,
        @NotNull LocalDate closedAt,
        List<RankingDTO> rankings ,
        CompetitionDTO competition){

    public RaceDTO withCompetitionId(String competitionId) {
        return new RaceDTO(id, name, startCoordinates, startDate, targetDistance,avgDistance, closedAt,rankings,
                CompetitionDTO.builder().id(competitionId).build());
    }
}
