package net.zekromaster.games.bucketdrops.entitysystems.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.components.BucketComponent;
import net.zekromaster.games.bucketdrops.components.RenderableComponent;
import net.zekromaster.games.bucketdrops.components.UntexturedTagComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@BucketDropsSystem
public class BucketColorSettingSystem extends IteratingSystem {

    @Inject
    public BucketColorSettingSystem() {
        super(
            Family.all(UntexturedTagComponent.class, BucketComponent.class).get(),
            25
        );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final var texture = BucketComponent.MAPPER.get(entity).color().bucketTexture();

        entity.add(new RenderableComponent(texture));
        entity.remove(UntexturedTagComponent.class);
    }
}
