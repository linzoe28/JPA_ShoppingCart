package com.flyfox.game;

import com.flyfox.game.core.WScreen;

public class GameScreen extends WScreen {

	Snake snake = new Snake();
	Food food = new Food();

	public GameScreen(double width, double height) {
		super(width, height);
		addObject(snake);
		addObject(food);
	}

	@Override
	public void update() {
		super.update();

		if (snake.isCollisionWith(food)) {
			food.setVisible(false);
		}
	}
}
