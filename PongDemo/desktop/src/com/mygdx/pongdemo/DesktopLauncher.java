package com.mygdx.pongdemo;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.pongdemo.PongDemo;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("PongDemo");
		config.setWindowedMode(1280, 720);
		config.useVsync(true);
		config.setForegroundFPS(60);
		while (true) {
			new Lwjgl3Application(new PongDemo(), config);
		}
	}
}
