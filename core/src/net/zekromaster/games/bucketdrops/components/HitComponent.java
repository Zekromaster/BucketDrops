package net.zekromaster.games.bucketdrops.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.audio.Sound;
import net.zekromaster.games.bucketdrops.raindrops.HitEffect;

public record HitComponent(
    HitEffect effect,
    int value,
    Sound sound
) implements Component {

    public static ComponentMapper<HitComponent> MAPPER = ComponentMapper.getFor(HitComponent.class);

}