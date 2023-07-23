package net.zekromaster.games.bucketdrops.factories;

import com.badlogic.ashley.core.Entity;
import net.zekromaster.games.bucketdrops.components.*;
import net.zekromaster.games.bucketdrops.enums.BucketColor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedHashMap;
import java.util.Map;

@Singleton
public class RaindropFactory {

    private final Map<BucketColor, RenderableComponent> renderableComponentCache;
    private final Map<BucketColor, RaindropComponent> raindropComponentFache;

    private static final CatchableComponent RAINDROP_CATCHABLE_COMPONENT = new CatchableComponent("get.wav");

    @Inject
    public RaindropFactory() {
        this.renderableComponentCache = new LinkedHashMap<>();
        this.raindropComponentFache = new LinkedHashMap<>();
    }

    public Entity createRaindrop(
        int x,
        int y,
        int speed,
        BucketColor color
    ) {

        final var raindrop = new Entity();
        raindrop.add(
            this.raindropComponentFache.computeIfAbsent(
                color,
                RaindropComponent::new
            )
        );
        raindrop.add(
            new PositionComponent(
                x,
                y,
                64,
                64
            )
        );
        raindrop.add(
            this.renderableComponentCache.computeIfAbsent(
                color,
                (BucketColor bucketColor) -> new RenderableComponent(bucketColor.dropTexture())
            )
        );
        raindrop.add(
            new FallingComponent(speed)
        );
        raindrop.add(RAINDROP_CATCHABLE_COMPONENT);

        return raindrop;
    }

}
