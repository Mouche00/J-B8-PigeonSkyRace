package org.ioc.jb8pigeonskyrace.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ioc.jb8pigeonskyrace.dtos.CompetitionDTO;
import org.ioc.jb8pigeonskyrace.services.CompetitionService;
import org.ioc.jb8pigeonskyrace.utils.ApiResponse;
import org.ioc.jb8pigeonskyrace.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<CompetitionDTO>> addCompetition(
            @RequestBody CompetitionDTO competitionDTO, HttpServletRequest request) {

        CompetitionDTO competitionCreated = competitionService.addCompetition(competitionDTO);

        return ResponseEntity.ok(
                ResponseUtil.success(competitionCreated, "Competition added successfully!", request.getRequestURI())
        );
    }
}
