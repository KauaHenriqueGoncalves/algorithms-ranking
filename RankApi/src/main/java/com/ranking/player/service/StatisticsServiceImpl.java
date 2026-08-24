package com.ranking.player.service;

import com.ranking.player.dto.PlayerStats;
import com.ranking.player.model.Player;
import com.ranking.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final PlayerRepository playerRepository;

    public StatisticsServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerStats generate() {
        List<Player> players = playerRepository.findAll();
        double highest = players.stream()
                .mapToDouble(Player::getScore)
                .max().orElse(0);
        double lowest = players.stream()
                .mapToDouble(Player::getScore)
                .min().orElse(0);
        double average = players.stream()
                .mapToDouble(Player::getScore)
                .average().orElse(0);
        Map<String, Long> countByCategory = players.stream()
                .collect(Collectors.groupingBy(Player::getCategory, Collectors.counting()));
        return new PlayerStats(highest, lowest, average, players.size(), countByCategory);
    }
}
