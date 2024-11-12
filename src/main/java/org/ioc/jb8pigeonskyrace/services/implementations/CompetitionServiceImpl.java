package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.ioc.jb8pigeonskyrace.repositories.CompetitionRepository;
import org.ioc.jb8pigeonskyrace.services.CompetitionService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public CompetitionDTO save(CompetitionDTO competitionDTO) {
        Competition competition = competitionMapper.toCompetition(competitionDTO);
        competition = competitionRepository.save(competition);
        return competitionMapper.toDTO(competition);
    }

    @Override
    public CompetitionDTO update(CompetitionDTO competitionDTO) {
        Competition competition = competitionMapper.toCompetition(competitionDTO);
        competition = competitionRepository.save(competition);
        return competitionMapper.toDTO(competition);
    }

    @Override
    public List<CompetitionDTO> saveAll(List<CompetitionDTO> competitionDTOs) {
        return List.of();
    }

    @Override
    public List<CompetitionDTO> findAll() {
        return List.of();
    }

    @Override
    public CompetitionDTO findById(String id) {
        return null;
    }
}
