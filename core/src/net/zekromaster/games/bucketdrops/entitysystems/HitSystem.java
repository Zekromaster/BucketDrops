package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.components.BucketComponent;
import net.zekromaster.games.bucketdrops.components.HitComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.frontend.SoundStore;
import net.zekromaster.games.bucketdrops.annotations.Player;
import net.zekromaster.games.bucketdrops.raindrops.HitEffect;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

@Singleton @BucketDropsSystem
public class HitSystem extends IteratingSystem {

    private final Engine engine;
    private final Entity player;
    private final SoundStore soundStore;

    @Inject
    public HitSystem(
        Engine engine,
        SoundStore soundStore,
        @Player Entity player
    )  {
        super(Family.all(HitComponent.class, PositionComponent.class).get());
        this.engine = engine;
        this.player = player;
        this.soundStore = soundStore;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        final var playerLocation = PositionComponent.MAPPER.get(player);
        final var playerState = BucketComponent.MAPPER.get(player);
        final var raindropLocation = PositionComponent.MAPPER.get(entity);
        final var raindropEffect = HitComponent.MAPPER.get(entity);

        if (raindropLocation.overlaps(playerLocation)) {
            if (Objects.requireNonNull(raindropEffect.effect()) == HitEffect.GAIN_POINTS) {
                player.add(
                    playerState.withScore(
                        Math.max(0, playerState.score() + raindropEffect.value())
                    )
                );
            }
            soundStore.get(raindropEffect.sound()).play();
            engine.removeEntity(entity);
        }
    }
}
