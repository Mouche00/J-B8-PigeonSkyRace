package org.ioc.jb8pigeonskyrace.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.ioc.jb8pigeonskyrace.exception.ResourceNotFoundException;
import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.ioc.jb8pigeonskyrace.models.LoginRequest;
import org.ioc.jb8pigeonskyrace.repositories.BreederRepository;
import org.ioc.jb8pigeonskyrace.services.BreederAuthService;
import org.ioc.jb8pigeonskyrace.services.implementations.BreederAuthServiceImpl;
import org.ioc.jb8pigeonskyrace.utils.ApiResponse;
import org.ioc.jb8pigeonskyrace.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breeders")
public class BreederController {

    @Autowired
    private BreederAuthService breederAuthService;

    @Autowired
    private BreederRepository breederRepository;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Breeder>> register(@RequestBody Breeder breeder, HttpServletRequest request) {
        request.getSession().setAttribute("breederId", breeder.getId());
        breederAuthService.register(breeder);
        return ResponseEntity.ok(ResponseUtil.success(breeder, "Breeder registered successfully", request.getRequestURI()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Breeder>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (breederAuthService.login(username, password)) {
            Breeder breeder = breederRepository.findByUsername(username);
            request.getSession().setAttribute("breederId", breeder.getId());
            return ResponseEntity.ok(ResponseUtil.success(breeder, "Breeder logged in successfully", request.getRequestURI()));
        } else {
            throw new ResourceNotFoundException("Breeder Username or password not valid" );
        }
    }

}
