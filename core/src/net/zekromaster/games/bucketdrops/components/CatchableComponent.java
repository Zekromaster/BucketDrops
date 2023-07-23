package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public record CatchableComponent(
    String catchingSound
) implements Component {

    public static ComponentMapper<CatchableComponent> MAPPER = ComponentMapper.getFor(CatchableComponent.class);

}
