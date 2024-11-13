package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.ioc.jb8pigeonskyrace.repositories.RaceRepository;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RaceMapper raceMapper;  // Assuming you have a RaceMapper for converting DTO to entity

    public RaceDTO addRace(RaceDTO raceDTO) {
        // Map the RaceDTO to Race entity
        Race race = raceMapper.toRace(raceDTO);

        // Save the race to the repository
        Race savedRace = raceRepository.save(race);

        // Return the saved race as a DTO
        return raceMapper.toDTO(savedRace);
    }
}
