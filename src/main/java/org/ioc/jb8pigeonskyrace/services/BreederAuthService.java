package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.models.Breeder;

public interface BreederAuthService {
    public String register(Breeder breeder);
    public boolean login(String username, String password);
}
