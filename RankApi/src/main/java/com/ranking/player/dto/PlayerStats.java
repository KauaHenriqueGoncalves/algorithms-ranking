package com.ranking.player.dto;

import java.util.Map;

public record PlayerStats(
        double highestScore,
        double lowestScore,
        double averageScore,
        long totalCount,
        Map<String, Long> countByCategory
) { }
