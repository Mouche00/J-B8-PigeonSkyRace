package org.ioc.jb8pigeonskyrace.services.implementations;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingCSVDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingDTO;
import org.ioc.jb8pigeonskyrace.events.RaceClosedEvent;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.ioc.jb8pigeonskyrace.models.Ranking;
import org.ioc.jb8pigeonskyrace.repositories.PigeonRepository;
import org.ioc.jb8pigeonskyrace.repositories.RaceRepository;
import org.ioc.jb8pigeonskyrace.repositories.RankingRepository;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.ioc.jb8pigeonskyrace.services.RankingService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.PigeonMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RaceMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;
    private final PigeonMapper pigeonMapper;
    private final PigeonService pigeonService;

    @Autowired
    public RankingServiceImpl(PigeonMapper pigeonMapper, RankingRepository rankingRepository,RankingMapper rankingMapper,PigeonService pigeonService) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
        this.pigeonService = pigeonService;
        this.pigeonMapper = pigeonMapper;
    }

    public RankingDTO addPigeonToRace(RankingDTO rankingDTO) {
        Ranking ranking = rankingMapper.toRanking(rankingDTO);
        Ranking savedRanking = rankingRepository.save(ranking);
        return rankingMapper.toDTO(savedRanking);
    }


    @Override
    public List<RankingDTO> saveAll(RankingCSVDTO rankingCSVDTO) {
        List<Ranking> rankings = convertCSV(rankingCSVDTO.csv());
        rankings.forEach(ranking -> {
            if (ranking.getPigeon() != null) {
                pigeonService.save(pigeonMapper.toDTO(ranking.getPigeon()));
            }
        });
        return rankings.stream()
                .map(rankingMapper::toDTO)
                .map(this::addPigeonToRace)
                .toList();
    }

    @EventListener
    public List<RankingDTO> handleRaceClosedEvent(RaceClosedEvent raceClosedEvent) {
        String raceId = raceClosedEvent.getRace().id();
        if(raceId != null) {
            return calculateAndRank(raceId);
        }
        return Collections.emptyList();
    }

    @Override
    public List<RankingDTO> calculateAndRank(String raceId) {
        return rankingMapper.toRankingDTOs(rankingRepository.findByRaceId(raceId));
    }


    @Override
    public List<Ranking> convertCSV(MultipartFile csv){
//        try {
//            List<Ranking> rankings = new CsvToBeanBuilder<Ranking>(new FileReader(csvFile)).withType(Ranking.class).build().parse();
//            return rankings;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        try (InputStreamReader reader = new InputStreamReader(csv.getInputStream())) {
            return new CsvToBeanBuilder<Ranking>(reader)
                    .withType(Ranking.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process CSV file", e);
        }
    }












}
