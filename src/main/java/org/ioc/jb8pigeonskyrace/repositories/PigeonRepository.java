package org.ioc.jb8pigeonskyrace.repositories;

import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigeonRepository extends MongoRepository<Pigeon, String> {
}
