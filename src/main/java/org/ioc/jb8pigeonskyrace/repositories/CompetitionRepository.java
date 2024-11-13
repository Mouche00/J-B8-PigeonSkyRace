package org.ioc.jb8pigeonskyrace.repositories;

import org.ioc.jb8pigeonskyrace.models.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends MongoRepository<Competition, String> {
}
