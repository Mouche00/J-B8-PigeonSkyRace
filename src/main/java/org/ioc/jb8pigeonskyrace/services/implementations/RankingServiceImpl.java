package org.ioc.jb8pigeonskyrace.services.implementations;

import com.opencsv.bean.CsvToBeanBuilder;
import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.ioc.jb8pigeonskyrace.models.Ranking;
import org.ioc.jb8pigeonskyrace.repositories.PigeonRepository;
import org.ioc.jb8pigeonskyrace.repositories.RaceRepository;
import org.ioc.jb8pigeonskyrace.repositories.RankingRepository;
import org.ioc.jb8pigeonskyrace.services.BreederAuthService;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.services.RankingService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.PigeonMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RaceMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;
    private final PigeonMapper pigeonMapper;
    private final PigeonService pigeonService;
    private final BreederAuthService breederAuthService;
    private final RaceService raceService;
    private final RaceMapper raceMapper;

    @Autowired
    public RankingServiceImpl(RaceService raceService,RaceMapper raceMapper,PigeonMapper pigeonMapper, RankingRepository rankingRepository,RankingMapper rankingMapper,PigeonService pigeonService,BreederAuthService breederAuthService) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
        this.pigeonService = pigeonService;
        this.pigeonMapper = pigeonMapper;
        this.breederAuthService = breederAuthService;
        this.raceService = raceService;
        this.raceMapper = raceMapper;
    }

    public RankingDTO addPigeonToRace(RankingDTO rankingDTO) {
        Ranking ranking = rankingMapper.toRanking(rankingDTO);
        Ranking savedRanking = rankingRepository.save(ranking);
        return rankingMapper.toDTO(savedRanking);
    }


    @Override
    public List<RankingDTO> saveAll(File csvFile) {
        List<Ranking> rankings = convertCSV(csvFile);
        List<BreederDTO> breeders = breederAuthService.findAll();
        int breederCount = breeders.size();
        int[] breederIndex = {0};
        rankings.forEach(ranking -> {
            if (ranking.getPigeon() != null) {
                String breederId = breeders.get(breederIndex[0] % breederCount).id();
                breederIndex[0]++;
                pigeonService.save(
                        pigeonMapper.toDTO(ranking.getPigeon()).withBreederId(breederId)
                );
            }
        });






        List<RaceDTO> races = raceService.findAll();
        List<PigeonDTO> pigeons = pigeonService.findAll();
        int raceCount = races.size();
        int pigeonCount = pigeons.size();
        return rankings.stream()
                .map(ranking -> {
                    // Determine the current pigeon and race in a round-robin manner
                    int index = rankings.indexOf(ranking); // Get the current ranking index
                    String pigeonId = pigeons.get(index % pigeonCount).id();
                    String raceId = races.get(index % raceCount).id();

                    // Map ranking and assign pigeon and race IDs
                    return rankingMapper.toDTO(ranking)
                            .withPigeonAndRaceIds(pigeonId, raceId);
                })
                .map(this::addPigeonToRace)
                .toList();
    }


    @Override
    public List<Ranking> convertCSV(File csvFile){
        try {
            List<Ranking> rankings = new CsvToBeanBuilder<Ranking>(new FileReader(csvFile)).withType(Ranking.class).build().parse();
            return rankings;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }












}
