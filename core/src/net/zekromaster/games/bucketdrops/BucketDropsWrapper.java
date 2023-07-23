package net.zekromaster.games.bucketdrops;

import com.badlogic.gdx.Game;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class BucketDropsWrapper extends Game {

    private final Injector injector;
    private BucketDrops game;

    public BucketDropsWrapper() {
        injector = Guice.createInjector(
            new GuiceHandler()
        );
    }

    @Override
    public void create() {
        game = injector.getInstance(BucketDrops.class);
        game.create();
    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
