package net.zekromaster.games.bucketdrops.entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.raindrops.RaindropFactory;
import net.zekromaster.games.bucketdrops.raindrops.RaindropType;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton @BucketDropsSystem
public class RaindropSpawnSystem extends IntervalSystem {

    private final RaindropFactory raindropFactory;
    private final Engine engine;

    @Inject
    public RaindropSpawnSystem(
        Engine engine,
        RaindropFactory raindropFactory
    ) {
        super(1);
        this.engine = engine;
        this.raindropFactory = raindropFactory;
    }

    @Override
    protected void updateInterval() {
        this.spawnRaindrop();
    }

    private void spawnRaindrop() {
        var raindrop = raindropFactory.createRaindrop(
            RaindropType.random(),
            MathUtils.random(0, 656),
            480
        );
        engine.addEntity(raindrop);
    }
}
