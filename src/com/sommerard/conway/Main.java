package com.sommerard.conway;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	public static void main(String[] args) {
		Game game = new Game("Game of life");
		try {
			AppGameContainer app = new AppGameContainer(game);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
