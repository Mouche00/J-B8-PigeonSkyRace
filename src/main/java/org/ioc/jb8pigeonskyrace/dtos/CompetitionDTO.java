package org.ioc.jb8pigeonskyrace.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CompetitionDTO(
        String id,
        @NotBlank String name,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime closedAt,
        List<RaceDTO> races) {

    public CompetitionDTO withRaceIds(List<String> raceIds) {
        List<RaceDTO> raceDTOs = raceIds.stream()
                .map(raceId -> RaceDTO.builder().id(raceId).build())
                .toList();
        return new CompetitionDTO(id, name, closedAt, raceDTOs);
    }
}
