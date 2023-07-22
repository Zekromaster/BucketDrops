package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.components.FallingComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton @BucketDropsSystem
public class RaindropFallSystem extends IteratingSystem {

    private final Engine engine;

    @Inject
    public RaindropFallSystem(
        Engine engine
    ) {
        super(
            Family.all(FallingComponent.class, PositionComponent.class).get()
        );
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final var position = PositionComponent.MAPPER.get(entity);
        final var speed = FallingComponent.MAPPER.get(entity).fallSpeed();

        if (position.y() < 0) {
            engine.removeEntity(entity);
            return;
        }

        entity.add(
            position.withY(position.y() - speed*deltaTime)
        );

    }
}
