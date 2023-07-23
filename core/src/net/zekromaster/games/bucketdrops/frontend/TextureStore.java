package net.zekromaster.games.bucketdrops.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TextureStore extends BasicStore<Texture> {

    @Inject
    public TextureStore() {
        super();
    }

    @Override
    protected Texture defaultGenerator(String name) {
        return new Texture(Gdx.files.internal(name));
    }

    @Override
    protected void disposeOfOne(Texture object) {
        object.dispose();
    }

}
