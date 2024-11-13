package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingDTO;

public interface RankingService {
    public RankingDTO addPigeonToRace(RankingDTO rankingDTO);
}
