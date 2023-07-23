package net.zekromaster.games.bucketdrops.screens;

import com.badlogic.gdx.Screen;
import com.google.inject.Injector;
import net.zekromaster.games.bucketdrops.BucketDrops;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScreenHandler {

    private BucketDrops game;

    private Injector injector;

    @Inject
    public ScreenHandler(
        Injector injector
    ) {
        this.injector = injector;
    }

    /**
     * ONLY CALL ONCE
     */
    public void setGame(BucketDrops game) {
        this.game = game;
    }

    public void switchScreen(Class<? extends Screen> screen) {
        final var currentScreen = this.game.getScreen();
        this.game.setScreen(injector.getInstance(screen));
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

}
