package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.repositories.PigeonRepository;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.ioc.jb8pigeonskyrace.utils.generators.Generator;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.PigeonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PigeonServiceImpl implements PigeonService {
    private final PigeonMapper pigeonMapper;
    private final PigeonRepository pigeonRepository;
    private final Generator<Object, String> bandNumberGenerator;

    @Autowired
    public PigeonServiceImpl(PigeonMapper pigeonMapper, PigeonRepository pigeonRepository, Generator<Object, String> bandNumberGenerator) {
        this.pigeonMapper = pigeonMapper;
        this.pigeonRepository = pigeonRepository;
        this.bandNumberGenerator = bandNumberGenerator;
    }

    @Override
    public PigeonDTO save(PigeonDTO pigeonDTO) {
        Pigeon pigeon = pigeonMapper.toPigeon(pigeonDTO);
        pigeon.setBandNumber(bandNumberGenerator.generate(pigeon.getGender(), pigeon.getBirthYear()));
        Pigeon savedPigeon = pigeonRepository.save(pigeon);
        return findById(savedPigeon.getId());
    }

    @Override
    public List<PigeonDTO> saveAll(List<PigeonDTO> pigeonDTOs) {
        return pigeonDTOs.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<PigeonDTO> findAll() {
        return pigeonRepository.findAll().stream()
                .map(pigeonMapper::toDTO)
                .toList();
    }

    @Override
    public PigeonDTO findById(String id) {
        Optional<Pigeon> foundPigeon = pigeonRepository.findById(id);
        return foundPigeon.map(pigeonMapper::toDTO).orElse(null);

    }
}
