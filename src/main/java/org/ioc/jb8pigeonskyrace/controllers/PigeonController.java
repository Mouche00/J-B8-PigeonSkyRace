package org.ioc.jb8pigeonskyrace.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.exception.AuthenticationException;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.ioc.jb8pigeonskyrace.utils.ApiResponse;
import org.ioc.jb8pigeonskyrace.utils.ResponseUtil;
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
    public ResponseEntity<ApiResponse<PigeonDTO>> save(@RequestBody PigeonDTO pigeonDTO, HttpServletRequest request) {
        String breederId = (String) request.getSession().getAttribute("breederId");
        PigeonDTO createdPigeon = pigeonService.save(pigeonDTO.withBreederId(breederId));
        return ResponseEntity.ok(ResponseUtil.success(createdPigeon, "Pigeon saved successfully", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PigeonDTO>>> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(pigeonService.findAll(), "Pigeons retrieved successfully", request.getRequestURI()));
    }

    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<PigeonDTO>>> saveAll(@RequestBody List<PigeonDTO> pigeonDTOs, HttpServletRequest request) {
        String breederId = (String) request.getSession().getAttribute("breederId");
        pigeonDTOs = pigeonDTOs.stream().map(pigeonDTO -> pigeonDTO.withBreederId(breederId)).toList();
        return ResponseEntity.ok(ResponseUtil.success(pigeonService.saveAll(pigeonDTOs), "Pigeons saved successfully", request.getRequestURI()));
    }
}
