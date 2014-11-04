package com.flyfox.game;

import com.flyfox.game.core.WScreen;
import com.flyfox.game.core.WSystem;

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
		// 死了
		if (snake.getHp() <= 0) {
			snake.setVisible(false);
			snakeBody.setVisible(false);
			food.setVisible(false);
			return;
		}

		// 调用更新操作
		super.update();

		// 不长眼，撞边框上了，减少一条命，复原
		if (snake.getX() > WSystem.WIDTH || snake.getX() < 0 //
				|| snake.getY() > WSystem.HEIGHT || snake.getY() < 0) {
			snake.death();
			snakeBody.setVisible(false);
			return;
		}

		// 撞到自己的身体了
		if (snakeBody.isCollisionWith(snake)) {
			snake.death();
			snakeBody.setVisible(false);
			return;
		}

		// 吃到了~！~
		if (snake.isCollisionWith(food)) {
			snake.addScore();
			snake.addLength();
			food.setVisible(false);
		}

	}

}
