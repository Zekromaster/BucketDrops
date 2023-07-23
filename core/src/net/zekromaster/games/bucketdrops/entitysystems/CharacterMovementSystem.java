package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.enums.Direction;
import net.zekromaster.games.bucketdrops.components.HorizontalMoverComponent;
import net.zekromaster.games.bucketdrops.components.InputComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton @BucketDropsSystem
public class CharacterMovementSystem extends IteratingSystem {

    @Inject
    public CharacterMovementSystem() {
        super(
            Family.all(InputComponent.class, HorizontalMoverComponent.class, PositionComponent.class).get(),
            1
        );
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        final var position = PositionComponent.MAPPER.get(entity);
        final var horizontalMovement = HorizontalMoverComponent.MAPPER.get(entity);
        final var input = InputComponent.MAPPER.get(entity);

        Optional<Direction> moveDirection = Direction.fromInput(input.pendingBucketInput());

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

                entity.add(newLocation);
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
}
