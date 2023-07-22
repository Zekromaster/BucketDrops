package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public record HorizontalMoverComponent(
    int speed
) implements Component {
    public static ComponentMapper<HorizontalMoverComponent> MAPPER = ComponentMapper.getFor(HorizontalMoverComponent.class);
}
