package net.zekromaster.games.bucketdrops.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public enum BucketColor {
    BLUE("blue"),
    RED("red"),
    YELLOW("yellow"),
    GREEN("green");

    private final String bucketTexture;
    private final String dropTexture;

    BucketColor(String colorName) {
        this.bucketTexture = colorName + "/bucket.png";
        this.dropTexture = colorName + "/drop.png";
    }
}
