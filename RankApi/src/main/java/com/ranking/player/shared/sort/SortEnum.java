package com.ranking.player.shared.sort;

import com.ranking.player.shared.exceptions.NotFoundException;

public enum SortEnum {
    BUBBLE("bubble"),
    SELECTION("selection"),
    INSERTION("insertion");

    private final String name;

    SortEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static SortEnum of(String name) {
        for (SortEnum sort : SortEnum.values()) {
            if (sort.getName().equals(name)) {
                return sort;
            }
        }
        throw new NotFoundException("No found sort Enum");
    }
}
