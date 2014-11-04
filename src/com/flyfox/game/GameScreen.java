package com.flyfox.game;

import com.flyfox.game.core.FFContants;
import com.flyfox.game.core.FFScreen;

public class GameScreen extends FFScreen {

	Snake snake = new Snake();
	SnakeBody snakeBody = new SnakeBody(snake);
	Food food = new Food();

	public GameScreen(double width, double height) {
		super(width, height);
		addObject(snakeBody);
		addObject(snake);
		addObject(food);
	}

	@Override
	public void update() {
		// ����
		if (snake.getHp() <= 0) {
			snake.setVisible(false);
			snakeBody.setVisible(false);
			food.setVisible(false);
			return;
		}

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

}
