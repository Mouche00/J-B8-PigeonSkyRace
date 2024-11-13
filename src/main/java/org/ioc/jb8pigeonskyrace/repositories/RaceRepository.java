package org.ioc.jb8pigeonskyrace.repositories;

import org.ioc.jb8pigeonskyrace.models.Race;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends MongoRepository<Race, String> {
}
