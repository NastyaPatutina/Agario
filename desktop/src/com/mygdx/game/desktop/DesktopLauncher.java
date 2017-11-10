package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Agario;
import java.util.ArrayList;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Agario";
                ArrayList<String> str = new ArrayList <String>();
                config.width = 272;
                config.height = 408;
                new LwjglApplication(new Agario(), config);
	}
}
