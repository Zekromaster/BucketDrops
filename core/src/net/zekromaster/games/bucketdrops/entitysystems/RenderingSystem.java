package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import javax.inject.Inject;
import javax.inject.Singleton;

import net.zekromaster.games.bucketdrops.components.PositionComponent;
import net.zekromaster.games.bucketdrops.components.RenderableComponent;
import net.zekromaster.games.bucketdrops.gamestate.GameState;
import net.zekromaster.games.bucketdrops.gamestate.ScoreComponent;

@Singleton @BucketDropsSystem
public class RenderingSystem extends IteratingSystem {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private final Entity gameState;

    @Inject
    public RenderingSystem(
        @GameState Entity gameState
    ) {
        super(Family.all(PositionComponent.class, RenderableComponent.class).get());
        this.gameState = gameState;
    }

    @Override
    public void update(float deltaTime) {
        var score = ScoreComponent.MAPPER.get(gameState).score();

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

        batch.draw(texture, position.x(), position.y());
    }
}
