package com.flyfox.game;

import com.flyfox.game.core.WScreen;

public class GameScreen extends WScreen {

	Snake snake = new Snake();
	SnakeBody snakeBody = new SnakeBody(snake);
	Food food = new Food();

	public GameScreen(double width, double height) {
		super(width, height);
		addObject(snake);
		addObject(snakeBody);
		addObject(food);
	}

	@Override
	public void update() {
		super.update();

		// �Ե���~��~
		if (snake.isCollisionWith(food)) {
			snake.addScore();
			snake.addLength();
			food.setVisible(false);
		}

		// ����
		if (snake.getHp() <= 0) {
			
		}
	}
	
}
