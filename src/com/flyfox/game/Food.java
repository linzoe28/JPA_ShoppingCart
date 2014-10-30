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
		int x = random.nextInt(WSystem.WIDTH);
		int y = random.nextInt(WSystem.HEIGHT);
		setX(x);
		setY(y);
	}

}
