package net.zekromaster.games.bucketdrops.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import net.zekromaster.games.bucketdrops.annotations.Player;
import net.zekromaster.games.bucketdrops.components.*;
import net.zekromaster.games.bucketdrops.enums.BucketColor;
import net.zekromaster.games.bucketdrops.enums.BucketInput;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameScreen implements Screen {

    private final Engine engine;
    private final Entity player;
    private Music music;

    @Inject
    public GameScreen(
        Engine engine,
        @Player Entity player
    ) {
        this.engine=engine;
        this.player=player;
    }

    public void show() {
        this.initPlayer();

        engine.getSystems().forEach(
            system -> system.setProcessing(true)
        );

        music = Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3"));
        music.setLooping(true);
        music.play();
    }

    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        // Blank
    }

    @Override
    public void pause() {
        // Blank
    }

    @Override
    public void resume() {
        // Blank
    }

    public void hide() {
        music.stop();
        this.initPlayer();
        engine.getSystems().forEach(
            system -> system.setProcessing(true)
        );
        engine.removeAllEntities();
        engine.addEntity(player);
    }

    public void dispose() {
        music.dispose();
    }

    private void initPlayer() {
        player.removeAll();
        player.add(new PositionComponent(
            328,
            20,
            64,
            54
        ));
        player.add(new HorizontalMoverComponent(
            200
        ));
        player.add(UntexturedTagComponent.INSTANCE);
        player.add(
            new InputComponent(BucketInput.NONE)
        );
        player.add(
            BucketComponent.init(BucketColor.RED)
        );
    }

}
