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
		// ��Ϣ�����ϲ�
		addObject(info);

		mGameState = GameState.GAME_START;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// ��ͣ
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		super.draw(gc);
	}

	@Override
	public void update() {
		// ��ͣ
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		// ����
		if (snake.getHp() <= 0) {
			snake.setVisible(false);
			snakeBody.setVisible(false);
			food.setVisible(false);
			
			info.setHp(0);
			return;
		}

		// ������ʾ�����ͻ���
		info.setHp(snake.getHp());
		info.setScore(snake.score());

		// ���ø��²���
		super.update();

		// �����ۣ�ײ�߿����ˣ�����һ��������ԭ
		if (snake.getX() > FFContants.WIDTH || snake.getX() < 0 //
				|| snake.getY() > FFContants.HEIGHT || snake.getY() < 0) {
			snake.death();
			snakeBody.setVisible(false);
			return;
		}

		// ײ���Լ���������
		if (snakeBody.isCollisionWith(snake)) {
			snake.death();
			snakeBody.setVisible(false);
			return;
		}

		// �Ե���~��~
		if (snake.isCollisionWith(food)) {
			snake.addScore();
			snake.addLength();
			food.setVisible(false);
		}

	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		// ��ͣ
		if (event.getCode() == KeyCode.P) {
			if (mGameState == GameState.GAME_PAUSE) {
				mGameState = GameState.GAME_CONTINUE;
			} else {
				mGameState = GameState.GAME_PAUSE;
			}
		}

		// ���¿�ʼ
		if (event.getCode() == KeyCode.S) {
			info.init();
			snake.init();
			snakeBody.init();
			food.init();
			mGameState = GameState.GAME_START;
		}
	}

}
