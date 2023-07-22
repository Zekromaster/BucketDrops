package net.zekromaster.games.bucketdrops.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.LinkedHashMap;
import java.util.Map;

class RaindropSoundCache {

    private final Map<RaindropType, Sound> cache = new LinkedHashMap<>();

    public RaindropSoundCache() {
        // Default blank constructor
    }

    public Sound get(RaindropType raindropType) {
        return cache.computeIfAbsent(
            raindropType,
            type -> Gdx.audio.newSound(Gdx.files.internal(type.sound()))
        );
    }

}
