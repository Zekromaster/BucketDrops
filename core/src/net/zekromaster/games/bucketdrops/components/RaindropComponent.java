package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import net.zekromaster.games.bucketdrops.enums.BucketColor;

public record RaindropComponent(
    BucketColor color
) implements Component {

    public static ComponentMapper<RaindropComponent> MAPPER = ComponentMapper.getFor(RaindropComponent.class);

}
