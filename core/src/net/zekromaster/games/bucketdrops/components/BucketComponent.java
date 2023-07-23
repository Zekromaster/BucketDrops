package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import lombok.With;
import net.zekromaster.games.bucketdrops.enums.BucketColor;

@With
public record BucketComponent(
    int score,
    int health,
    BucketColor color
) implements Component {

    public static ComponentMapper<BucketComponent> MAPPER = ComponentMapper.getFor(BucketComponent.class);

    public static BucketComponent init(BucketColor color) {
        return new BucketComponent(0, 3, color);
    }

}
