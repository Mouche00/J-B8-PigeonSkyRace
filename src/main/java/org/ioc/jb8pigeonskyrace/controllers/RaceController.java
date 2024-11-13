package org.ioc.jb8pigeonskyrace.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.services.RaceService;
import org.ioc.jb8pigeonskyrace.utils.ApiResponse;
import org.ioc.jb8pigeonskyrace.utils.ResponseUtil;
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

    @PostMapping("save")
    public ResponseEntity<ApiResponse<RaceDTO>> addRace(
            @RequestBody RaceDTO raceDTO, HttpServletRequest request) {

        RaceDTO raceCreated = raceService.addRace(raceDTO.withCompetitionId(raceDTO.competition().id()));

        return ResponseEntity.ok(
                ResponseUtil.success(raceCreated, "Race added successfully!", request.getRequestURI())
        );
    }
}