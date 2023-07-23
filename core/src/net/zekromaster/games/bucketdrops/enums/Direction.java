package net.zekromaster.games.bucketdrops.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Optional;

@Getter
@Accessors(fluent = true)
public enum Direction {
    LEFT(-1),
    RIGHT(+1);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public static Optional<Direction> fromInput(BucketInput input) {
        return switch (input) {
            case LEFT -> Optional.of(Direction.LEFT);
            case RIGHT -> Optional.of(Direction.RIGHT);
            default -> Optional.empty();
        };
    }

}
