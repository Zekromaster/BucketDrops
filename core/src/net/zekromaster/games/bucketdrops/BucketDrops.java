package net.zekromaster.games.bucketdrops;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import net.zekromaster.games.bucketdrops.components.*;
import net.zekromaster.games.bucketdrops.gamestate.BucketInput;
import net.zekromaster.games.bucketdrops.gamestate.Player;
import java.util.Set;

public class BucketDrops extends ApplicationAdapter {
	private final Engine engine;
	private final Set<EntitySystem> systems;
	private final Entity player;
	private Music music;

	@Inject
	public BucketDrops(
		Engine engine,
		Set<EntitySystem> systems,
		@Player Entity player
	) {
		this.engine = engine;
		this.systems = systems;
		this.player = player;
	}

	@Override
	public void create () {
		systems.forEach(engine::addSystem);

		player.add(new PositionComponent(
            328,
			20,
			64,
			64
		));
		player.add(new HorizontalMoverComponent(
			200
		));
		player.add(new RenderableComponent(
			new Texture(Gdx.files.internal("bucket.png"))
		));
		player.add(
			new InputComponent(BucketInput.NONE)
		);
		engine.addEntity(player);

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.play();
	}

	@Override
	public void render () {
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		engine.getEntitiesFor(
			Family.all(RenderableComponent.class).get()
		).forEach(
			entity -> {
				var renderableComponent = RenderableComponent.MAPPER.get(entity);
				var texture = renderableComponent.texture();
				texture.dispose();
			}
		);

		engine.getEntitiesFor(
			Family.all(HitComponent.class).get()
		).forEach(
			entity -> {
				var hitComponent = HitComponent.MAPPER.get(entity);
				var sound = hitComponent.sound();
				sound.dispose();
			}
		);

		music.dispose();
	}

}
