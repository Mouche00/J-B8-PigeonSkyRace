package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.exception.ResourceNotFoundException;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.ioc.jb8pigeonskyrace.repositories.CompetitionRepository;
import org.ioc.jb8pigeonskyrace.services.CompetitionService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper, ApplicationEventPublisher eventPublisher) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CompetitionDTO save(CompetitionDTO competitionDTO) {
        Competition competition = competitionMapper.toCompetition(competitionDTO);
        competition = competitionRepository.save(competition);
        return competitionMapper.toDTO(competition);
    }

    @Override
    public CompetitionDTO update(String id, CompetitionDTO competitionDTO) {
        Competition competition = findEntityById(id);
        competitionMapper.updateCompetitionFromDto(competitionDTO, competition);
        competition = competitionRepository.save(competition);
        return competitionMapper.toDTO(competition);
    }

    @Override
    public CompetitionDTO close(String id) {
        Competition competition = findEntityById(id);
        CompetitionDTO competitionDTO = CompetitionDTO.builder().closedAt(LocalDateTime.now()).build();
        competitionMapper.updateCompetitionFromDto(competitionDTO, competition);
        competition = competitionRepository.save(competition);
        competitionDTO = competitionMapper.toDTO(competition);

//        eventPublisher.publishEvent(new CompetitionClosedEvent(this, competitionDTO));
        return competitionDTO;
    }

    public Competition findEntityById(String id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competition not found with id: " + id));
    }

    @Override
    public CompetitionDTO findById(String id) {
        return competitionMapper.toDTO(findEntityById(id));
    }

    @Override
    public List<CompetitionDTO> saveAll(List<CompetitionDTO> competitionDTOs) {
        return competitionDTOs.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<CompetitionDTO> findAll() {
        return competitionRepository.findAll().stream()
                .map(competitionMapper::toDTO)
                .toList();
    }
}
