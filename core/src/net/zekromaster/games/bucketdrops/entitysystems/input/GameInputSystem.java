package net.zekromaster.games.bucketdrops.entitysystems.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.components.InputComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.enums.BucketInput;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton @BucketDropsSystem
public class GameInputSystem extends IteratingSystem {

    @Inject
    public GameInputSystem() {
        super(Family.all(InputComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        if (PositionComponent.MAPPER.has(entity) && Gdx.input.isTouched()) {
            final var position = PositionComponent.MAPPER.get(entity);
            entity.add(
                new InputComponent(
                    this.processMouse(Gdx.input.getX(), position)
                )
            );
            return;
        }

        entity.add(
            new InputComponent(
                this.processKeyboard()
            )
        );
    }

    private BucketInput processMouse(
        int clickX,
        PositionComponent entityPosition
    ) {
        final var center = entityPosition.x() + (entityPosition.width() / 2);
        if (clickX > center + 4) {
            return BucketInput.RIGHT;
        }
        if (clickX < center - 4) {
            return BucketInput.LEFT;
        }
        return BucketInput.NONE;
    }

    private BucketInput processKeyboard() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            return BucketInput.LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            return BucketInput.RIGHT;
        }
        return BucketInput.NONE;
    }

}
