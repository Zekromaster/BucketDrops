package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.components.HitComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.gamestate.GameState;
import net.zekromaster.games.bucketdrops.gamestate.Player;
import net.zekromaster.games.bucketdrops.gamestate.ScoreComponent;
import net.zekromaster.games.bucketdrops.raindrops.HitEffect;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

@Singleton @BucketDropsSystem
public class HitSystem extends IteratingSystem {

    private final Engine engine;
    private final Entity player;
    private final Entity gameState;

    @Inject
    public HitSystem(
        Engine engine,
        @Player Entity player,
        @GameState Entity gameState
    )  {
        super(Family.all(HitComponent.class, PositionComponent.class).get());
        this.engine = engine;
        this.player = player;
        this.gameState = gameState;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        final var playerLocation = PositionComponent.MAPPER.get(player);
        final var raindropLocation = PositionComponent.MAPPER.get(entity);
        final var raindropEffect = HitComponent.MAPPER.get(entity);

        final var score = ScoreComponent.MAPPER.get(gameState);

        if (raindropLocation.overlaps(playerLocation)) {
            if (Objects.requireNonNull(raindropEffect.effect()) == HitEffect.GAIN_POINTS) {
                gameState.add(
                    score.withScore(
                        Math.max(0, score.score() + raindropEffect.value())
                    )
                );
            }
            raindropEffect.sound().play();
            engine.removeEntity(entity);
        }
    }
}
