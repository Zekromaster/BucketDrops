package net.zekromaster.games.bucketdrops.enums;

import com.badlogic.gdx.math.MathUtils;
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

    public static BucketColor random() {
        var values = values();
        return values[MathUtils.random(values.length - 1)];
    }
}
