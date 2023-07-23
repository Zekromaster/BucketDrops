package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import net.zekromaster.games.bucketdrops.gamestate.BucketInput;

public record InputComponent(
    BucketInput pendingBucketInput
) implements Component {

    public static ComponentMapper<InputComponent> MAPPER = ComponentMapper.getFor(InputComponent.class);

}
