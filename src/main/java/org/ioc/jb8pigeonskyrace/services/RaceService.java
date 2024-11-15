package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;

import java.util.List;

public interface RaceService {
    public RaceDTO save(RaceDTO raceDTO);
    public RaceDTO findById(String id);
    public List<RaceDTO> findAll();
}
