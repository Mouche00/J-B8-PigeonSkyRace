package org.ioc.jb8pigeonskyrace.services;

import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.ioc.jb8pigeonskyrace.dtos.RankingDTO;
import org.ioc.jb8pigeonskyrace.models.Ranking;

import java.io.File;
import java.util.List;

public interface RankingService {
    public RankingDTO addPigeonToRace(RankingDTO rankingDTO);
    public List<RankingDTO> saveAll(File csvFile);

    List<Ranking>  convertCSV(File file);
}
