package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;

import java.util.List;

public interface BreederAuthService {
    String register(Breeder breeder);
    boolean login(String username, String password);
    List<BreederDTO> findAll();
}
