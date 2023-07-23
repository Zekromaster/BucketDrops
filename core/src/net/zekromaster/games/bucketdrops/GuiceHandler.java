package net.zekromaster.games.bucketdrops;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.github.classgraph.ClassGraph;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.annotations.Player;

public class GuiceHandler extends AbstractModule {

    @Override
    public void configure() {
        bind(Engine.class)
            .in(Singleton.class);

        Multibinder<EntitySystem> entitySystemBinder = Multibinder.newSetBinder(binder(), EntitySystem.class);

        new ClassGraph().enableClassInfo().enableAnnotationInfo()
            .acceptPackages("net.zekromaster.games.bucketdrops")
            .scan()
            .getSubclasses(EntitySystem.class)
            .filter(classInfo -> classInfo.hasAnnotation(BucketDropsSystem.class))
            .loadClasses(EntitySystem.class)
            .forEach(
                loadedClass -> entitySystemBinder.addBinding().to((Key.get(loadedClass)))
            );
    }

    @Provides
    @Player
    @Singleton
    public Entity player() {
        return new Entity();
    }

}
