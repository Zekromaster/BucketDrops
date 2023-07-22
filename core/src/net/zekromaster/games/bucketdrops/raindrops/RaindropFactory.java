package net.zekromaster.games.bucketdrops.raindrops;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import net.zekromaster.games.bucketdrops.components.FallingComponent;
import net.zekromaster.games.bucketdrops.components.HitComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.components.RenderableComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RaindropFactory {

    private final RaindropTextureCache raindropTextureCache;
    private final RaindropSoundCache raindropSoundCache;

    @Inject
    public RaindropFactory() {
        this.raindropTextureCache = new RaindropTextureCache();
        this.raindropSoundCache = new RaindropSoundCache();
    }

    private Entity createRaindrop(
        int x,
        int y,
        int speed,
        Texture texture,
        HitEffect type,
        int value,
        Sound sound
    ) {
        final var raindrop = new Entity();
        raindrop.add(
            new PositionComponent(
                x,
                y,
                64,
                64
            )
        );
        raindrop.add(
            new RenderableComponent(
                texture
            )
        );
        raindrop.add(
            new FallingComponent(speed)
        );
        raindrop.add(
            new HitComponent(
                type,
                value,
                sound
            )
        );

        return raindrop;
    }

    public Entity createRaindrop(RaindropType raindropType, int x, int y) {
        return this.createRaindrop(
            x,
            y,
            raindropType.speed(),
            raindropTextureCache.get(raindropType),
            raindropType.hitEffect(),
            raindropType.value(),
            raindropSoundCache.get(raindropType)
        );
    }

}
