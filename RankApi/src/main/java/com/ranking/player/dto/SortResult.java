package com.ranking.player.dto;

import com.ranking.player.model.Player;
import java.util.List;

public record SortResult(
        String strategy,
        long elapsedTimeNanos,
        double elapsedTimeMillis,
        List<Player> players
) { }
