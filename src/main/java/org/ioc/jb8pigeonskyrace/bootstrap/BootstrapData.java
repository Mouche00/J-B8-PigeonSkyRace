package org.ioc.jb8pigeonskyrace.bootstrap;

import lombok.RequiredArgsConstructor;
import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.ioc.jb8pigeonskyrace.services.BreederAuthService;
import org.ioc.jb8pigeonskyrace.services.CompetitionService;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final CompetitionService competitionService;
    private final RaceService raceService;
    private final BreederAuthService breederAuthService;

    @Override
    public void run(String... args) throws Exception {
        loadCompetitionAndRaces();
        loadBreeders();
    }

    private void loadCompetitionAndRaces() {
        CompetitionDTO competitionDTO = CompetitionDTO.builder()
                .name("National Championship")
                .closedAt(LocalDateTime.now().plusMonths(1)) // Competition closes in 1 month
                .build();

        CompetitionDTO savedCompetition = competitionService.save(competitionDTO);
        System.out.println("Competition saved: " + savedCompetition.id());


        List<RaceDTO> raceDTOs = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            RaceDTO raceDTO = RaceDTO.builder()
                    .name("Race " + i)
                    .startCoordinates(new Coordinates(40.7128, -74.0060))
                    .startDate(LocalDateTime.now().plusDays(i))
                    .targetDistance(500 + (i * 50))
                    .closedAt(LocalDateTime.now().plusDays(i + 10))
                    .competition(savedCompetition)
                    .build();

            RaceDTO savedRace = raceService.save(raceDTO.withCompetitionId(savedCompetition.id()));
            raceDTOs.add(savedRace);
            System.out.println("Race saved: " + savedRace);
        }
    }

    private void loadBreeders() {
        for (int i = 1; i <= 11; i++) {
            Breeder breeder = new Breeder();
            breeder.setUsername("breeder" + i);
            breeder.setPassword("password" + i);
            breeder.setLoftName("Loft " + i);
            breeder.setLoftCoordinates(new Coordinates(34.0522, -118.2437)); // Example loft coordinates
            breeder.setFinalScore(0.0);

            String message = breederAuthService.register(breeder);
            System.out.println(message + ": " + breeder.getUsername());
        }
    }
}