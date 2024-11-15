package org.ioc.jb8pigeonskyrace.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.ApiResponse;
import org.ioc.jb8pigeonskyrace.utils.ResponseUtil;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.BreederMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    @Autowired
    private BreederMapper breederMapper;
    @Autowired
    private RaceService raceService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<RaceDTO>> save(
            @RequestBody RaceDTO raceDTO,
            HttpServletRequest request) {

        return ResponseEntity.ok(ResponseUtil.success(
                raceService.save(raceDTO),
                "Race saved successfully",
                request.getRequestURI()
        ));
    }
}