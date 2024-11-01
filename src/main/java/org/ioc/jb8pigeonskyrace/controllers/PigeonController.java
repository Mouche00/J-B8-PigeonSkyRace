package org.ioc.jb8pigeonskyrace.controllers;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pigeons")
public class PigeonController {
    private final PigeonService pigeonService;

    @Autowired
    public PigeonController(PigeonService pigeonService) {
        this.pigeonService = pigeonService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PigeonDTO> create(@RequestBody PigeonDTO pigeonDTO) {
        PigeonDTO createdPigeon = pigeonService.save(pigeonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPigeon);
    }
}
