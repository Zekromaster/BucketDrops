package net.zekromaster.games.bucketdrops;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Game;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.zekromaster.games.bucketdrops.annotations.Player;
import net.zekromaster.games.bucketdrops.screens.MainMenuScreen;
import net.zekromaster.games.bucketdrops.screens.ScreenHandler;

import java.util.Set;

@Singleton
public class BucketDrops extends Game {

	private final ScreenHandler screenHandler;
	private final Engine engine;
	private final Set<EntitySystem> systems;
	private final Entity player;

	@Inject
	public BucketDrops(
		ScreenHandler screenHandler,
		Engine engine,
		Set<EntitySystem> systems,
		@Player Entity player
	) {
		this.screenHandler = screenHandler;
		this.engine = engine;
		this.systems = systems;
		this.player = player;
	}

	@Override
	public void create () {
		systems.forEach(
			system -> system.setProcessing(false)
		);
		systems.forEach(engine::addSystem);
		engine.addEntity(player);


		this.screenHandler.setGame(this);
		this.screenHandler.switchScreen(MainMenuScreen.class);
	}

}
