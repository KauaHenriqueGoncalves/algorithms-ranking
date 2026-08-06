package com.ranking.player.shared.sort;

import com.ranking.player.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("bubble")
public class BubbleSortStrategy implements SortStrategy<Player> {
    @Override
    public List<Player> sort(List<Player> list) {
        List<Player> arr = new ArrayList<>(list);
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr.get(j).getScore() < arr.get(j + 1).getScore()) {
                    Player temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
        return arr;
    }
}
