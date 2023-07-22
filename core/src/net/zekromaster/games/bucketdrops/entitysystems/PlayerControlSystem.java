package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.zekromaster.games.bucketdrops.Direction;
import net.zekromaster.games.bucketdrops.components.HorizontalMoverComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.gamestate.Player;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton @BucketDropsSystem
public class PlayerControlSystem extends EntitySystem {

    private final Entity player;

    @Inject
    public PlayerControlSystem(
        @Player Entity player
    ) {
        this.player = player;
    }

    @Override
    public void update(float deltaTime) {
        final var position = PositionComponent.MAPPER.get(player);
        final var horizontalMovement = HorizontalMoverComponent.MAPPER.get(player);

        Optional<Direction> moveDirection = Optional.empty();

        if (Gdx.input.isTouched()) {
            moveDirection = this.getSide(Gdx.input.getX(), position);
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveDirection = Optional.of(Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveDirection = Optional.of(Direction.RIGHT);
        }

        moveDirection.ifPresent(
            direction -> {
                final var newLocation = this.move(
                    position,
                    horizontalMovement,
                    direction,
                    0,
                    720,
                    deltaTime
                );

                player.add(newLocation);
            }
        );

    }

    private PositionComponent move(
        PositionComponent startingPosition,
        HorizontalMoverComponent horizontalMovement,
        Direction direction,
        int leftBoundary,
        int rightBoundary,
        float deltaTime
    ) {
        final var potentialX = startingPosition.x() + (direction.value() * horizontalMovement.speed() * deltaTime);
        if (potentialX < leftBoundary) {
            return startingPosition.withX(leftBoundary);
        }
        if (potentialX > rightBoundary - 64) {
            return startingPosition.withX((float) rightBoundary - 64);
        }
        return startingPosition.withX(potentialX);
    }

    private Optional<Direction> getSide(
        int clickLocation,
        PositionComponent position
    ) {
        final var center = position.x() + (position.width() / 2);
        if (clickLocation > center + 4) {
            return Optional.of(Direction.RIGHT);
        }
        if (clickLocation < center - 4) {
            return Optional.of(Direction.LEFT);
        }
        return Optional.empty();
    }
}
