package com.cyanon.dandd;

import java.io.IOException;

public class DungeonsAndDragons {

	public static void main(String[] args) throws IOException {
		GameLoop game = new GameLoop();
		game.gamePlaying = true;
		game.start();
	}
}
