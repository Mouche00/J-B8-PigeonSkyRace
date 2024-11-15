package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.dtos.BreederDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.ioc.jb8pigeonskyrace.repositories.BreederRepository;
import org.ioc.jb8pigeonskyrace.services.BreederAuthService;
import org.ioc.jb8pigeonskyrace.utils.PasswordUtil;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.BreederMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreederAuthServiceImpl implements BreederAuthService {
    @Autowired
    private BreederRepository breederRepository;

    @Autowired
    private BreederMapper breederMapper;


    @Override
    public String register(Breeder breeder) {
        breeder.setPassword(PasswordUtil.hashPassword(breeder.getPassword()));
        breederRepository.save(breeder);
        return "Breeder registered successfully";
    }

    @Override
    public boolean login(String username, String password) {
        Breeder breeder = breederRepository.findByUsername(username);
        if (breeder != null) {
            return PasswordUtil.verifyPassword(password, breeder.getPassword());
        }
        return false;
    }

    @Override
    public List<BreederDTO> findAll() {
        return breederRepository.findAll().stream()
                .map(breederMapper::toDTO)
                .toList();
    }

}
