package net.zekromaster.games.bucketdrops;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.*;
import com.google.inject.multibindings.Multibinder;
import io.github.classgraph.ClassGraph;
import net.zekromaster.games.bucketdrops.annotations.BucketDropsSystem;
import net.zekromaster.games.bucketdrops.annotations.Player;
import net.zekromaster.games.bucketdrops.frontend.Store;
import net.zekromaster.games.bucketdrops.frontend.SoundStore;
import net.zekromaster.games.bucketdrops.frontend.TextureStore;

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

        bind(new TypeLiteral<Store<Texture>>(){}).to(TextureStore.class).in(Singleton.class);
        bind(new TypeLiteral<Store<Sound>>(){}).to(SoundStore.class).in(Singleton.class);
    }

    @Provides
    @Player
    @Singleton
    public Entity player() {
        return new Entity();
    }


}
