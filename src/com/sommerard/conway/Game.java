package com.sommerard.conway;

import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Game extends BasicGame {
	private boolean grid[][];
	private int timer;
	private int turn;
	private int width;
	private int height;
	private String fontPath;
	private UnicodeFont uFont;

	public Game(String title) {
		super(title);
		this.grid = new boolean[64][48];
	}

	public void render(GameContainer container, Graphics g) {
		g.setBackground(new Color(78, 78, 78));
		g.setColor(new Color(56, 56, 56));

		for (int i = 0; i <= this.width; i += 10)
			g.drawLine(i, 0, i, this.height);
		for (int i = 0; i <= this.height; i += 10)
			g.drawLine(0, i, this.width, i);

		g.setColor(Color.black);

		for (int y = 0; y < 48; y++)
			for (int x = 0; x < 64; x++) {
				if (this.grid[x][y] == true)
					g.fillRect((x * 10) + 1, (y * 10) + 1, 9, 9);
			}

		g.setColor(Color.white);
		uFont.drawString(10, 0, Integer.toString(this.turn));
	}

	public void init(GameContainer container) throws SlickException {
		this.initGrid(container);
		this.timer = 0;
		this.turn = 0;
		this.width = container.getWidth();
		this.height = container.getHeight();
		fontPath = "res/font.ttf";
		uFont = new UnicodeFont(fontPath, 11, false, false);
		uFont.addAsciiGlyphs();
		uFont.getEffects().add(new ColorEffect());
		uFont.loadGlyphs();
	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		if (this.timer < 15) {
			this.timer++;
		} else {
			this.updateGrid();
			turn++;
			this.timer = 0;
		}
	}

	private void initGrid(GameContainer container) {
		Random rand = new Random();
		for (int y = 0; y < 48; y++)
			for (int x = 0; x < 64; x++)
				this.grid[x][y] = rand.nextBoolean();
	}

	private void updateGrid() {
		boolean[][] tmpGrid = new boolean[64][48];
		for (int y = 0; y < 48; y++) {
			for (int x = 0; x < 64; x++) {
				tmpGrid[x][y] = caseState(x, y);
			}
		}
		this.grid = tmpGrid;
	}

	private boolean caseState(int x, int y) {
		boolean state = this.grid[x][y];
		int count = 0;
		if (x == 0) {
			if (this.grid[x + 1][y])
				count++;

			if (y != 47) {
				if (this.grid[x][y + 1])
					count++;
				if (this.grid[x + 1][y + 1])
					count++;
			}

			if (y != 0) {
				if (this.grid[x][y - 1])
					count++;
				if (this.grid[x + 1][y - 1])
					count++;
			}
		} else if (x == 63) {
			if (this.grid[x - 1][y])
				count++;

			if (y != 47) {
				if (this.grid[x - 1][y + 1])
					count++;
				if (this.grid[x][y + 1])
					count++;
			}

			if (y != 0) {
				if (this.grid[x - 1][y - 1])
					count++;
				if (this.grid[x][y - 1])
					count++;
			}
		} else if (y == 0) {
			if (this.grid[x - 1][y])
				count++;
			if (this.grid[x - 1][y + 1])
				count++;
			if (this.grid[x][y + 1])
				count++;
			if (this.grid[x + 1][y + 1])
				count++;
			if (this.grid[x + 1][y])
				count++;
		} else if (y == 47) {
			if (this.grid[x - 1][y])
				count++;
			if (this.grid[x - 1][y - 1])
				count++;
			if (this.grid[x][y - 1])
				count++;
			if (this.grid[x + 1][y - 1])
				count++;
			if (this.grid[x + 1][y])
				count++;
		} else {
			if (this.grid[x - 1][y - 1])
				count++;
			if (this.grid[x][y - 1])
				count++;
			if (this.grid[x + 1][y - 1])
				count++;
			if (this.grid[x - 1][y])
				count++;
			if (this.grid[x + 1][y])
				count++;
			if (this.grid[x - 1][y + 1])
				count++;
			if (this.grid[x][y + 1])
				count++;
			if (this.grid[x + 1][y + 1])
				count++;
		}

		if (state && count < 2)
			state = false;
		else if (state && count >= 2 && count <= 3)
			state = true;
		else if (state && count > 3)
			state = false;
		else if (!state && count == 3)
			state = true;

		return state;
	}

}
