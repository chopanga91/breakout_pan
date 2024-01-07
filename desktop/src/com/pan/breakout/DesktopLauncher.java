package com.pan.breakout;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.pan.breakout.BreakoutGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Breakout");
		//config.setWindowedMode(1280, 720);
		config.setWindowedMode(432, 243);

		new Lwjgl3Application(new BreakoutGame(), config);
	}
}
