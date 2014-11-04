package com.flyfox.game;

import java.security.SecureRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.flyfox.game.core.FFObject;
import com.flyfox.game.core.FFContants;

public class Food extends FFObject {

	private SecureRandom random = new SecureRandom();

	public Food() {
		createRandomFood();
		setWidth(FFContants.MIN_X);
		setHeight(FFContants.MIN_Y);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (!isVisible()) {
			createRandomFood();
			setVisible(true);
		}
	}

	private void createRandomFood() {
		// ��֤������С��Ԫ�ϣ������λ
		int x = random.nextInt(FFContants.WIDTH / FFContants.MIN_X) * FFContants.MIN_X;
		int y = random.nextInt(FFContants.HEIGHT / FFContants.MIN_Y) * FFContants.MIN_Y;
		setX(x);
		setY(y);
	}

}
