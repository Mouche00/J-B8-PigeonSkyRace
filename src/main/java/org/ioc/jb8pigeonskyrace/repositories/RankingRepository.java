package org.ioc.jb8pigeonskyrace.repositories;

import org.ioc.jb8pigeonskyrace.models.Ranking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends MongoRepository<Ranking, String> {
    @Query("{ 'race.$id' : ?0 }")
    List<Ranking> findByRaceId(String raceId);
}
