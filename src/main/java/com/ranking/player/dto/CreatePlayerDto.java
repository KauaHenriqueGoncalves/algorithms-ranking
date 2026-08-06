package com.ranking.player.dto;

public record CreatePlayerDto(
    String name,
    String category,
    int score
) { }
