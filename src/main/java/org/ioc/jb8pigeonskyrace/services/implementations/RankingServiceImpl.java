package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.ioc.jb8pigeonskyrace.models.Ranking;
import org.ioc.jb8pigeonskyrace.repositories.PigeonRepository;
import org.ioc.jb8pigeonskyrace.repositories.RaceRepository;
import org.ioc.jb8pigeonskyrace.repositories.RankingRepository;
import org.ioc.jb8pigeonskyrace.services.RankingService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.PigeonMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RaceMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class RankingServiceImpl implements RankingService {

    private final PigeonMapper pigeonMapper;
    private final RaceMapper raceMapper;
    private final RankingRepository rankingRepository;
    private final PigeonRepository pigeonRepository;
    private final RaceRepository raceRepository;
    private final RankingMapper rankingMapper;

    @Autowired
    public RankingServiceImpl(PigeonMapper pigeonMapper,RaceMapper raceMapper, RankingRepository rankingRepository,
                          PigeonRepository pigeonRepository,
                          RaceRepository raceRepository,
                          RankingMapper rankingMapper) {
        this.pigeonMapper = pigeonMapper;
        this.raceMapper = raceMapper;
        this.rankingRepository = rankingRepository;
        this.pigeonRepository = pigeonRepository;
        this.raceRepository = raceRepository;
        this.rankingMapper = rankingMapper;
    }

    public RankingDTO addPigeonToRace(RankingDTO rankingDTO) {
        Ranking ranking = rankingMapper.toRanking(rankingDTO);
        Ranking savedRanking = rankingRepository.save(ranking);
        return rankingMapper.toDTO(savedRanking);
    }








}
