package org.ioc.jb8pigeonskyrace.repositories;

import org.ioc.jb8pigeonskyrace.models.Ranking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends MongoRepository<Ranking, String> {
}
