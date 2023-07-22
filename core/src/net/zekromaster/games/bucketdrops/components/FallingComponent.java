package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public record FallingComponent(
    int fallSpeed
) implements Component {

    public static ComponentMapper<FallingComponent> MAPPER = ComponentMapper.getFor(FallingComponent.class);


}
