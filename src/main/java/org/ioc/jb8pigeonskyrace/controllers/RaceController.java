package org.ioc.jb8pigeonskyrace.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Competition;
import org.ioc.jb8pigeonskyrace.services.CompetitionService;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.ApiResponse;
import org.ioc.jb8pigeonskyrace.utils.ResponseUtil;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<RaceDTO>> addRace(
            @RequestBody RaceDTO raceDTO, HttpServletRequest request) {

        RaceDTO raceCreated = raceService.save(raceDTO);

        return ResponseEntity.ok(
                ResponseUtil.success(raceCreated, "Race added successfully!", request.getRequestURI())
        );
    }
}