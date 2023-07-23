package net.zekromaster.games.bucketdrops.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundStore extends BasicStore<Sound> {

    public SoundStore() {
        super();
    }

    @Override
    protected Sound defaultGenerator(String name) {
        return Gdx.audio.newSound(Gdx.files.internal("sounds/" + name));
    }

    @Override
    protected void disposeOfOne(Sound object) {
        object.dispose();
    }


}
