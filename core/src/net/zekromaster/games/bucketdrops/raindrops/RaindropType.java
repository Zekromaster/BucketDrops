package net.zekromaster.games.bucketdrops.raindrops;

import com.badlogic.gdx.math.MathUtils;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public enum RaindropType {
    GOOD("drop.png", "get.wav", 200, HitEffect.GAIN_POINTS, 10),
    EVIL("evil_drop.png", "get.wav", 250, HitEffect.GAIN_POINTS, -5);

    private final String texture;
    private final String sound;
    private final int speed;
    private final HitEffect hitEffect;
    private final int value;

    RaindropType(String texture, String sound, int speed, HitEffect hitEffect, int value) {
        this.texture = texture;
        this.sound = sound;
        this.speed = speed;
        this.hitEffect = hitEffect;
        this.value = value;
    }

    public static RaindropType random() {
        var values = values();
        return values[MathUtils.random(values.length - 1)];
    }
}
