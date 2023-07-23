package net.zekromaster.games.bucketdrops.factories;

import com.badlogic.ashley.core.Entity;
import net.zekromaster.games.bucketdrops.components.*;
import net.zekromaster.games.bucketdrops.enums.BucketColor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RaindropFactory {

    @Inject
    public RaindropFactory() {
        // Default blank constructor
    }

    public Entity createRaindrop(
        int x,
        int y,
        int speed,
        BucketColor color
    ) {
        final var raindrop = new Entity();
        raindrop.add(
            new RaindropComponent(
                color
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
            new RenderableComponent(
                color.dropTexture()
            )
        );
        raindrop.add(
            new FallingComponent(speed)
        );
        raindrop.add(
            new CatchableComponent(
                "get.wav"
            )
        );

        return raindrop;
    }

}
