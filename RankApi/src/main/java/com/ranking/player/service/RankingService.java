package com.ranking.player.service;

import com.ranking.player.model.Player;
import com.ranking.player.shared.sort.SortStrategy;

import java.util.List;

public interface RankingService {
    Player generateRandom();
    List<Player> generateMany(int quant);
    List<Player> getAll();
    Player getByName(String name);
    Player create(String name, String category, int score);
    List<Player> sortByScore(SortStrategy<Player> sortStrategy);
}
