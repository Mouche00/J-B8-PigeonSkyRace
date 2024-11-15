package org.ioc.jb8pigeonskyrace.services.implementations;


import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;

import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.exception.ResourceNotFoundException;
import org.ioc.jb8pigeonskyrace.models.Breeder;

import org.ioc.jb8pigeonskyrace.models.Race;
import org.ioc.jb8pigeonskyrace.repositories.RaceRepository;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.GeoUtil;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.BreederMapper;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.RaceMapper;
import org.ioc.jb8pigeonskyrace.utils.records.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class RaceServiceImpl implements RaceService {

    private RaceRepository raceRepository;
    private RaceMapper raceMapper;
    private GeoUtil geoUtil;
    private BreederMapper breederMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceMapper raceMapper, GeoUtil geoUtil) {
        this.raceRepository = raceRepository;
        this.raceMapper = raceMapper;
        this.geoUtil = geoUtil;
    }


    @Override
    public RaceDTO save(RaceDTO raceDTO) {
        Race race = raceMapper.toRace(raceDTO);
        Race savedRace = raceRepository.save(race);
        return findById(savedRace.getId());
    }

    @Override
    public RaceDTO update(String id, RaceDTO raceDTO) {
        Race race = findEntityById(id);
        raceMapper.updateRaceFromDto(raceDTO, race);
        race = raceRepository.save(race);
        return raceMapper.toDTO(race);
    }

    @Override
    public List<RaceDTO> saveAll(List<RaceDTO> raceDTOs) {
        return raceDTOs.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<RaceDTO> findAll() {
        return raceRepository.findAll().stream()
                .map(raceMapper::toDTO)
                .toList();
    }

    public Race findEntityById(String id) {
        Optional<Race> race = raceRepository.findById(id);
        if(race.isPresent()){
            return race.get();
        } else {
            throw new ResourceNotFoundException("Race not found");
        }
    }

    @Override
    public RaceDTO findById(String id) {
        return raceMapper.toDTO(findEntityById(id));
    }

    @Override
    public RaceDTO close(String id) {
        double avgDistance = calculateAvgDistance(id);
        RaceDTO newRaceDTO = RaceDTO.builder().closedAt(LocalDateTime.now()).avgDistance(avgDistance).build();
        return update(id, newRaceDTO);
    }

    @Override
    public double calculateAvgDistance(String id) {
        List<Breeder> breeders = getParticipatingBreeders(id);
        RaceDTO raceDTO = findById(id);
        return breeders.stream().mapToDouble(loft -> geoUtil.haversine(raceDTO.startCoordinates(), loft.getLoftCoordinates())).average().orElse(0.0);
    }


    @Override
    public List<Breeder> getParticipatingBreeders(String id) {
        return raceRepository.findDistinctLoftsByRaceId(id);

    }
}
