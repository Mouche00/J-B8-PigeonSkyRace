package org.ioc.jb8pigeonskyrace.controllers;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pigeons")
public class PigeonController {
    private final PigeonService pigeonService;

    @Autowired
    public PigeonController(PigeonService pigeonService) {
        this.pigeonService = pigeonService;
    }

    @PostMapping("save")
    public ResponseEntity<PigeonDTO> save(@RequestBody PigeonDTO pigeonDTO) {
        PigeonDTO createdPigeon = pigeonService.save(pigeonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPigeon);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PigeonDTO> findAll() {
        return pigeonService.findAll();
    }

    @PostMapping("save-all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PigeonDTO> saveAll(@RequestBody List<PigeonDTO> pigeonDTOS) {
        return pigeonService.saveAll(pigeonDTOS);
    }
}
