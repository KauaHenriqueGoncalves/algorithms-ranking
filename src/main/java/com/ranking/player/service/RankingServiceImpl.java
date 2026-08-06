package com.ranking.player.service;

import com.ranking.player.model.Player;
import com.ranking.player.repository.PlayerRepository;
import com.ranking.player.shared.exceptions.NotFoundException;
import com.ranking.player.shared.sort.SortStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RankingServiceImpl implements RankingService {
    private final PlayerRepository repository;
    private final Random random = new Random();

    private static final String[] NAMES = {"Ana", "Bruno", "Carla", "Diego", "Elis", "Fabio", "Gabi", "Heitor", "Ines", "Joao", "Karen", "Lucas", "Marcia", "Nando"};
    private static final String[] CATEGORIES = {"Ouro", "Prata", "Bronze"};

    public RankingServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initialSeed() {
        if (repository.count() == 0) {
            for (int i = 0; i < 5; i++) {
                this.generateRandom();
            }
        }
    }

    @Override
    public Player generateRandom() {
        String nome = NAMES[random.nextInt(NAMES.length)];
        String categoria = CATEGORIES[random.nextInt(CATEGORIES.length)];
        int score = random.nextInt(1000);
        return repository.save(new Player(nome, categoria, score));
    }

    @Override
    public List<Player> generateMany(int quant) {
        for (int i = 0; i < quant; i++) {
            this.generateRandom();
        }
        return repository.findAll();
    }

    @Override
    public List<Player> getAll() {
        return repository.findAll();
    }

    @Override
    public Player getByName(String name) {
        for (Player p : repository.findAll()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new NotFoundException("Not found player by name");
    }

    @Override
    public Player create(String name, String category, int score) {
        return repository.save(new Player(name, category, score));
    }

    @Override
    public List<Player> sortByScore(SortStrategy<Player> sortStrategy) {
        return sortStrategy.sort(repository.findAll());
    }
}
