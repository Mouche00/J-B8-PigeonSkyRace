package org.ioc.jb8pigeonskyrace.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public record RaceDTO(
        String id,
        @NotBlank String name,
        @NotNull double latitude,
        @NotNull double longitude,
        @NotNull LocalTime startTime,
        @NotNull double targetDistance,
        @NotNull LocalDate closedAt,
        CompetitionDTO competition,
        List<RankingDTO> rankings) {

    public RaceDTO withCompetitionId(String competitionId) {
        return new RaceDTO(id, name, latitude, longitude, startTime, targetDistance, closedAt,
                CompetitionDTO.builder().id(competitionId).build(), rankings);
    }
}
