package net.zekromaster.games.gdxpong.gamestate;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import lombok.With;

@With
public record ScoreComponent (
    int score
) implements Component {

    public static ComponentMapper<ScoreComponent> MAPPER = ComponentMapper.getFor(ScoreComponent.class);


}
