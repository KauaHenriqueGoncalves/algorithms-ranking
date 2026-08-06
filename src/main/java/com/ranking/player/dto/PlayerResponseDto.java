package com.ranking.player.dto;

import com.ranking.player.model.Player;

import java.util.List;
import java.util.UUID;

public record PlayerResponseDto(
        UUID id,
        String name,
        String category,
        int score
) {
    public static PlayerResponseDto of(Player p) {
        return new PlayerResponseDto(p.getId(), p.getName(), p.getCategory(), p.getScore());
    }

    public static List<PlayerResponseDto> of(List<Player> list) {
        return list.stream().map(p -> new PlayerResponseDto(p.getId(), p.getName(), p.getCategory(), p.getScore())).toList();
    }
}
