package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public record RenderableComponent(
    String texture
) implements Component {
    public static ComponentMapper<RenderableComponent> MAPPER = ComponentMapper.getFor(RenderableComponent.class);

}
