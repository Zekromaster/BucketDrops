package net.zekromaster.games.bucketdrops.entitysystems.physics;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.components.*;
import net.zekromaster.games.bucketdrops.frontend.SoundStore;
import net.zekromaster.games.bucketdrops.annotations.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton @BucketDropsSystem
public class CatchingSystem extends IteratingSystem {

    private final Engine engine;
    private final Entity player;
    private final SoundStore soundStore;

    @Inject
    public CatchingSystem(
        Engine engine,
        SoundStore soundStore,
        @Player Entity player
    )  {
        super(Family.all(CatchableComponent.class, PositionComponent.class).get());
        this.engine = engine;
        this.player = player;
        this.soundStore = soundStore;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        final var playerLocation = PositionComponent.MAPPER.get(player);
        final var playerState = BucketComponent.MAPPER.get(player);
        final var fallingObjectLocation = PositionComponent.MAPPER.get(entity);

        if (fallingObjectLocation.overlaps(playerLocation)) {
            final var catchedObjectLocation = CatchableComponent.MAPPER.get(entity);

            soundStore.get(catchedObjectLocation.catchingSound()).play();

            if (RaindropComponent.MAPPER.has(entity)) {
                final var raindrop = RaindropComponent.MAPPER.get(entity);
                if (raindrop.color().equals(playerState.color())) {
                    player.add(
                        playerState.withScore(playerState.score() + 5)
                    );
                } else {
                    player.add(
                        playerState.withHealth(playerState.health() - 1)
                    );
                }
            }
            engine.removeEntity(entity);
        }
    }
}
