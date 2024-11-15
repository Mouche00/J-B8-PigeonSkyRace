package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.models.Race;
import org.ioc.jb8pigeonskyrace.repositories.RaceRepository;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RaceMapper raceMapper;  // Assuming you have a RaceMapper for converting DTO to entity

    public RaceDTO save(RaceDTO raceDTO) {
        Race race = raceMapper.toRace(raceDTO);

        Race savedRace = raceRepository.save(race);

        return findById(savedRace.getId());
    }

    @Override
    public RaceDTO findById(String id) {
        Optional<Race> foundRace = raceRepository.findById(id);
        return foundRace.map(raceMapper::toDTO).orElse(null);
    }

    @Override
    public List<RaceDTO> findAll() {
        return raceRepository.findAll().stream()
                .map(raceMapper::toDTO)
                .toList();
    }
}
