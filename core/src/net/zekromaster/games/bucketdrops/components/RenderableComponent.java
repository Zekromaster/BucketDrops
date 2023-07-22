package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;

public record RenderableComponent(
    Texture texture
) implements Component {
    public static ComponentMapper<RenderableComponent> MAPPER = ComponentMapper.getFor(RenderableComponent.class);

}
