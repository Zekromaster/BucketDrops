package net.zekromaster.games.bucketdrops.entitysystems.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import javax.inject.Inject;
import javax.inject.Singleton;

import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.components.BucketComponent;
import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.components.RenderableComponent;
import net.zekromaster.games.bucketdrops.frontend.TextureStore;
import net.zekromaster.games.bucketdrops.annotations.Player;

@Singleton @BucketDropsSystem
public class RenderingSystem extends IteratingSystem {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private final Entity player;
    private final TextureStore textureStore;

    @Inject
    public RenderingSystem(
        TextureStore textureStore,
        @Player Entity player
    ) {
        super(
            Family.all(PositionComponent.class, RenderableComponent.class).get(),
            30
        );
        this.textureStore = textureStore;
        this.player = player;
    }

    @Override
    public void update(float deltaTime) {
        var score = BucketComponent.MAPPER.get(player).score();

        ScreenUtils.clear(0, 0, 0.2f, 1);

        if (batch == null) {
            batch = new SpriteBatch();
        }
        if (camera == null) {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 720, 480);
        }
        if (font == null) {
            font = new BitmapFont();
            font.getData().setScale(2, 2);
        }

        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        super.update(deltaTime);
        font.draw(batch, String.format("Score: %d", score), 0, 480);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final var texture = RenderableComponent.MAPPER.get(entity).texture();
        final var position = PositionComponent.MAPPER.get(entity);

        batch.draw(textureStore.get(texture), position.x(), position.y());
    }
}
