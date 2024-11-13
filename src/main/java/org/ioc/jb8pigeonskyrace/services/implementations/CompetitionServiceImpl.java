package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.ioc.jb8pigeonskyrace.repositories.CompetitionRepository;
import org.ioc.jb8pigeonskyrace.services.CompetitionService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionMapper competitionMapper;  // Assuming you have a CompetitionMapper for converting DTO to entity

    public CompetitionDTO addCompetition(CompetitionDTO competitionDTO) {
        // Map the CompetitionDTO to Competition entity
        Competition competition = competitionMapper.toCompetition(competitionDTO);

        // Save the competition to the repository
        Competition savedCompetition = competitionRepository.save(competition);

        // Return the saved competition as a DTO
        return competitionMapper.toDTO(savedCompetition);
    }
}
