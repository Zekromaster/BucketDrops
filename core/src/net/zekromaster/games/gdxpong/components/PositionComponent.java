package net.zekromaster.games.gdxpong.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Rectangle;
import lombok.With;

@With
public record PositionComponent(
    float x,
    float y,
    float width,
    float height
) implements Component {
    public static ComponentMapper<PositionComponent> MAPPER = ComponentMapper.getFor(PositionComponent.class);

    private Rectangle asRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public boolean overlaps(PositionComponent c) {
        return this.asRectangle().overlaps(c.asRectangle());
    }
}
