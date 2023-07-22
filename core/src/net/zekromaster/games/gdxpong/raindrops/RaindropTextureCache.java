package net.zekromaster.games.gdxpong.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.LinkedHashMap;
import java.util.Map;

class RaindropTextureCache {

    private final Map<RaindropType, Texture> cache = new LinkedHashMap<>();

    public RaindropTextureCache() {
        // Default blank constructor
    }

    public Texture get(RaindropType raindropType) {
        return cache.computeIfAbsent(
            raindropType,
            type -> new Texture(Gdx.files.internal(type.texture()))
        );
    }

}
