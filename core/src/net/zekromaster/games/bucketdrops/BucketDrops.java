package net.zekromaster.games.bucketdrops;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.google.inject.Inject;
import net.zekromaster.games.bucketdrops.components.*;
import net.zekromaster.games.bucketdrops.frontend.TextureStore;
import net.zekromaster.games.bucketdrops.gamestate.BucketInput;
import net.zekromaster.games.bucketdrops.gamestate.Player;
import java.util.Set;

public class BucketDrops extends ApplicationAdapter {
	private final Engine engine;
	private final Set<EntitySystem> systems;
	private final Entity player;
	private Music music;
	private final TextureStore textureStore;

	@Inject
	public BucketDrops(
		Engine engine,
		Set<EntitySystem> systems,
		TextureStore textureStore,
		@Player Entity player
	) {
		this.engine = engine;
		this.systems = systems;
		this.player = player;
		this.textureStore = textureStore;
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
			"bucket.png"
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
		textureStore.dispose();

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