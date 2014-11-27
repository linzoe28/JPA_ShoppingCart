package com.flyfox.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.flyfox.game.core.FFContants;
import com.flyfox.game.core.FFScreen;

public class GameScreen extends FFScreen {

	Information info = new Information();
	Snake snake = new Snake();
	SnakeBody snakeBody = new SnakeBody(snake);
	Food food = new Food();

	public GameScreen(double width, double height) {
		super(width, height);
		addObject(snakeBody);
		addObject(snake);
		addObject(food);
		// 信息在最上层
		addObject(info);

		mGameState = GameState.GAME_START;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// 暂停
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		super.draw(gc);
	}

	@Override
	public void update() {
		// 暂停
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		// 死了
		if (snake.getHp() <= 0) {
			snake.setVisible(false);
			snakeBody.setVisible(false);
			food.setVisible(false);
			
			info.setHp(0);
			return;
		}

		// 设置显示生命和积分
		info.setHp(snake.getHp());
		info.setScore(snake.score());

		// 调用更新操作
		super.update();

		// 不长眼，撞边框上了，减少一条命，复原
		if (snake.getX() > FFContants.WIDTH || snake.getX() < 0 //
				|| snake.getY() > FFContants.HEIGHT || snake.getY() < 0) {
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

	@Override
	public void onKeyReleased(KeyEvent event) {
		// 暂停
		if (event.getCode() == KeyCode.P) {
			if (mGameState == GameState.GAME_PAUSE) {
				mGameState = GameState.GAME_CONTINUE;
			} else {
				mGameState = GameState.GAME_PAUSE;
			}
		}

		// 重新开始
		if (event.getCode() == KeyCode.S) {
			info.init();
			snake.init();
			snakeBody.init();
			food.init();
			mGameState = GameState.GAME_START;
		}
	}

}
