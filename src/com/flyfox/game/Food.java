package com.flyfox.game;

import java.security.SecureRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.flyfox.game.core.WObject;
import com.flyfox.game.core.WSystem;

public class Food extends WObject {

	private SecureRandom random = new SecureRandom();

	public Food() {
		createRandomFood();
		setWidth(10);
		setHeight(10);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (!isVisible()) {
			createRandomFood();
			setVisible(true);
		}
	}

	private void createRandomFood() {
		// 保证是在最小单元上，不会错位
		int x = random.nextInt(WSystem.WIDTH / WSystem.MIN_X) * WSystem.MIN_X;
		int y = random.nextInt(WSystem.HEIGHT / WSystem.MIN_Y) * WSystem.MIN_Y;
		setX(x);
		setY(y);
	}

}
