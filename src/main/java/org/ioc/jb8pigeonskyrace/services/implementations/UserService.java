package org.ioc.jb8pigeonskyrace.services.implementations;

import org.ioc.jb8pigeonskyrace.models.User;
import org.ioc.jb8pigeonskyrace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

}