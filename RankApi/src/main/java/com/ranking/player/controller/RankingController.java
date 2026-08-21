package com.ranking.player.controller;

import com.ranking.player.dto.CreatePlayerDto;
import com.ranking.player.dto.PlayerResponseDto;
import com.ranking.player.model.Player;
import com.ranking.player.service.RankingService;
import com.ranking.player.shared.sort.SortEnum;
import com.ranking.player.shared.sort.SortStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RankingService rankingService;
    private final Map<String, SortStrategy<Player>> strategies; // string -> bean name

    public RankingController(RankingService rankingService, Map<String, SortStrategy<Player>> strategies) {
        this.rankingService = rankingService;
        this.strategies = strategies;
    }

    @PostMapping("/create")
    public ResponseEntity<PlayerResponseDto> create(@RequestBody CreatePlayerDto body) {
        Player response = rankingService.create(body.name(), body.category(), body.score());
        return ResponseEntity.ok(PlayerResponseDto.of(response));
    }

    @PostMapping("/generate")
    public ResponseEntity<PlayerResponseDto> generateRandom() {
        Player response = rankingService.generateRandom();
        return ResponseEntity.ok(PlayerResponseDto.of(response));
    }

    @PostMapping("/generate/{quant}")
    public ResponseEntity<List<PlayerResponseDto>> generateMany(@PathVariable int quant) {
        List<Player> response = rankingService.generateMany(quant);
        return ResponseEntity.ok(PlayerResponseDto.of(response));
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerResponseDto>> getByAll() {
        List<Player> response = rankingService.getAll();
        return ResponseEntity.ok(PlayerResponseDto.of(response));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PlayerResponseDto> getByName(@PathVariable String name) {
        Player response = rankingService.getByName(name);
        return ResponseEntity.ok(PlayerResponseDto.of(response));
    }

    @GetMapping("/sort/{strategy}")
    public ResponseEntity<List<PlayerResponseDto>> sort(@PathVariable String strategy) {
        SortEnum sortEnum = SortEnum.of(strategy);
        SortStrategy<Player> sortStrategy = strategies.get(sortEnum.getName());
        List<Player> response = rankingService.sortByScore(sortStrategy);
        return ResponseEntity.ok(PlayerResponseDto.of(response));
    }
}
