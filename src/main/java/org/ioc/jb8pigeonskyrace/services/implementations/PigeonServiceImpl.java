package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.repositories.PigeonRepository;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.PigeonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PigeonServiceImpl implements PigeonService {
    private final PigeonMapper pigeonMapper;
    private final PigeonRepository pigeonRepository;

    @Autowired
    public PigeonServiceImpl(PigeonRepository pigeonRepository, PigeonMapper pigeonMapper){
        this.pigeonRepository = pigeonRepository;
        this.pigeonMapper = pigeonMapper;
    }


    @Override
    public PigeonDTO save(PigeonDTO pigeonDTO) {
        Pigeon pigeon = pigeonMapper.toPigeon(pigeonDTO);
        Pigeon savedPigeon = pigeonRepository.save(pigeon);
        return pigeonMapper.toDTO(savedPigeon);
    }

    @Override
    public List<PigeonDTO> saveAll(List<PigeonDTO> pigeonDTOs) {
        return List.of();
    }

    @Override
    public List<PigeonDTO> findAll() {
        return List.of();
    }
}
