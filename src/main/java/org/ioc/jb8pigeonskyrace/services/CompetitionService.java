package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;

import java.util.List;

public interface CompetitionService {
    CompetitionDTO save(CompetitionDTO competitionDTO);
    CompetitionDTO update(String id, CompetitionDTO competitionDTO);
    CompetitionDTO close(String id);
    List<CompetitionDTO> saveAll(List<CompetitionDTO> competitionDTOs);
    List<CompetitionDTO> findAll();
    CompetitionDTO findById(String id);
}
