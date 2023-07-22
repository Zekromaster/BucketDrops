package net.zekromaster.games.gdxpong;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.github.classgraph.ClassGraph;
import net.zekromaster.games.gdxpong.entitysystems.GdxPongSystem;
import net.zekromaster.games.gdxpong.gamestate.GameState;
import net.zekromaster.games.gdxpong.gamestate.Player;
import net.zekromaster.games.gdxpong.gamestate.ScoreComponent;

public class GuiceHandler extends AbstractModule {

    @Override
    public void configure() {
        bind(Engine.class)
            .in(Singleton.class);

        Multibinder<EntitySystem> entitySystemBinder = Multibinder.newSetBinder(binder(), EntitySystem.class);

        new ClassGraph().enableClassInfo().enableAnnotationInfo()
            .acceptPackages("net.zekromaster.games.gdxpong")
            .scan()
            .getSubclasses(EntitySystem.class)
            .filter(classInfo -> classInfo.hasAnnotation(GdxPongSystem.class))
            .loadClasses(EntitySystem.class)
            .forEach(
                loadedClass -> entitySystemBinder.addBinding().to((loadedClass))
            );
    }

    @Provides
    @GameState
    @Singleton
    public Entity gameState() {
        var gameStateEntity = new Entity();
        gameStateEntity.add(new ScoreComponent(0));
        return gameStateEntity;
    }

    @Provides
    @Player
    @Singleton
    public Entity player() {
        return new Entity();
    }

}
