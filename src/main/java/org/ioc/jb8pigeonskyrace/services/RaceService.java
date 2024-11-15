package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;

import java.util.List;

import java.util.List;

public interface RaceService {
    RaceDTO save(RaceDTO raceDTO);
    RaceDTO update(String id, RaceDTO raceDTO);
    List<RaceDTO> saveAll(List<RaceDTO> raceDTOs);
    List<RaceDTO> findAll();
    RaceDTO findById(String id);
    RaceDTO close(String id);
    double calculateAvgDistance(String id);
    List<Breeder> getParticipatingBreeders(String id);
}
