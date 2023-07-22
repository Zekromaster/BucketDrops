package net.zekromaster.games.bucketdrops;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.inject.Guice;

public class DesktopLauncher {

	public static void main (String[] arg) {
		final var injector = Guice.createInjector(
			new GuiceHandler()
		);

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("BucketDrops");
		config.setWindowedMode(720, 480);
		config.useVsync(true);
		new Lwjgl3Application(
			injector.getInstance(BucketDrops.class),
			config
		);
	}
}
