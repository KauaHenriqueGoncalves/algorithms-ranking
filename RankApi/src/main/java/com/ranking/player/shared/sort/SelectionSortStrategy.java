package com.ranking.player.shared.sort;

import com.ranking.player.model.Player;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component("selection")
public class SelectionSortStrategy implements SortStrategy<Player> {
    @Override
    public List<Player> sort(List<Player> list) {
        List<Player> arr = new ArrayList<>(list);
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int biggerIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).getScore() > arr.get(biggerIdx).getScore()) {
                    biggerIdx = j;
                }
            }
            Player temp = arr.get(i);
            arr.set(i, arr.get(biggerIdx));
            arr.set(biggerIdx, temp);
        }
        return arr;
    }
}
