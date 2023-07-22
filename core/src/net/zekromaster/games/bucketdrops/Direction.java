package net.zekromaster.games.bucketdrops;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public enum Direction {
    LEFT(-1),
    RIGHT(+1);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

}
