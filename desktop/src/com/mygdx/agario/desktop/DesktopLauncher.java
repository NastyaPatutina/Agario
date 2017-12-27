package com.mygdx.agario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.agario.Agario;

import java.util.ArrayList;

public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Agario";
            ArrayList<String> str = new ArrayList <String>();
            config.width = 1000;
            config.height = 1000;

            new LwjglApplication(new Agario(), config);
	}
}
