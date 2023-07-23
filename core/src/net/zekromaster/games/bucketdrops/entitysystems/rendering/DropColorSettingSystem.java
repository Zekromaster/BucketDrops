package net.zekromaster.games.bucketdrops.entitysystems.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.components.RaindropComponent;
import net.zekromaster.games.bucketdrops.components.RenderableComponent;
import net.zekromaster.games.bucketdrops.components.UntexturedTagComponent;
import net.zekromaster.games.bucketdrops.enums.BucketColor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedHashMap;
import java.util.Map;

@Singleton
@BucketDropsSystem
public class DropColorSettingSystem extends IteratingSystem {

    private final Map<BucketColor, RenderableComponent> renderableComponentCache;

    @Inject
    public DropColorSettingSystem() {
        super(
            Family.all(UntexturedTagComponent.class, RaindropComponent.class).get(),
            25
        );
        this.renderableComponentCache = new LinkedHashMap<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final var color = RaindropComponent.MAPPER.get(entity).color();

        entity.add(
            this.renderableComponentCache.computeIfAbsent(
                color,
                bucketColor -> new RenderableComponent(bucketColor.dropTexture())
            )
        );
        entity.remove(UntexturedTagComponent.class);
    }
}
