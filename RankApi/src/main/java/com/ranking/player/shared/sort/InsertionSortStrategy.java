package com.ranking.player.shared.sort;

import com.ranking.player.model.Player;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component("insertion")
public class InsertionSortStrategy implements SortStrategy<Player> {
    @Override
    public List<Player> sort(List<Player> list) {
        List<Player> lista = new ArrayList<>(list);
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            Player atual = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j).getScore() < atual.getScore()) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, atual);
        }
        return lista;
    }
}
