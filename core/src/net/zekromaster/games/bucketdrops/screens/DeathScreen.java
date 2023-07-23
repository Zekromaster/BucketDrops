package net.zekromaster.games.bucketdrops.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton @BucketDropsSystem
public class DeathScreen implements Screen {

    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private Music music;
    private final ScreenHandler screenHandler;

    @Inject
    public DeathScreen(
        ScreenHandler screenHandler
    ) {
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 480);
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.screenHandler = screenHandler;
    }

    @Override
    public void show() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu.ogg"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "You died!", 100, 150);
        font.draw(batch, "Click anywhere to replay", 100, 100);
        batch.end();

        if (Gdx.input.isTouched()) {
            this.screenHandler.switchScreen(MainMenuScreen.class);
        }


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

    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void dispose() {
        music.dispose();
    }
}
