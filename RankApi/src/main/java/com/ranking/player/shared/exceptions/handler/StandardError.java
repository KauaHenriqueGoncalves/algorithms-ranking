package com.ranking.player.shared.exceptions.handler;

public record StandardError(
        int status,
        String error,
        String message
) { }
