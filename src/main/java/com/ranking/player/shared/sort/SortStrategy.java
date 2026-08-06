package com.ranking.player.shared.sort;

import java.util.List;

public interface SortStrategy<T> {
    List<T> sort(List<T> list);
}
