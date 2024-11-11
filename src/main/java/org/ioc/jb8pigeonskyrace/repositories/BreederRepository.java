package org.ioc.jb8pigeonskyrace.repositories;

import org.ioc.jb8pigeonskyrace.models.Breeder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BreederRepository extends MongoRepository<Breeder, String> {
     Breeder findByUsername(String username);
}
