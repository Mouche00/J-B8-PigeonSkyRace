package org.ioc.jb8pigeonskyrace.dtos;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record RankingCSVDTO(MultipartFile csv,
                            String raceId) {
}
