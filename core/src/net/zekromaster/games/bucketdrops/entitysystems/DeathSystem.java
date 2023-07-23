package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.annotations.Player;
import net.zekromaster.games.bucketdrops.components.BucketComponent;
import net.zekromaster.games.bucketdrops.screens.DeathScreen;
import net.zekromaster.games.bucketdrops.screens.ScreenHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@BucketDropsSystem
public class DeathSystem extends EntitySystem {

    private final ScreenHandler screenHandler;
    private final Entity player;

    @Inject
    public DeathSystem(
        ScreenHandler screenHandler,
        @Player Entity player
    ) {
        this.screenHandler = screenHandler;
        this.player = player;
    }

    @Override
    public void update(float deltaTime) {
        final var health = BucketComponent.MAPPER.get(player).health();
        if (health <= 0) {
            screenHandler.switchScreen(DeathScreen.class);
        }
    }
}
