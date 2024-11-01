package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;

import java.util.List;

public interface PigeonService {
    PigeonDTO save(PigeonDTO pigeonDTO);
    List<PigeonDTO> saveAll(List<PigeonDTO> pigeonsDTOs);
    List<PigeonDTO> findAll();
}
