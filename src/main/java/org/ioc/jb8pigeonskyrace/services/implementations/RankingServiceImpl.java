package org.ioc.jb8pigeonskyrace.services.implementations;

import com.opencsv.bean.CsvToBeanBuilder;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingDTO;
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
    public List<RankingDTO> saveAll(File csvFile) {
        List<Ranking> rankings = convertCSV(csvFile);
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
