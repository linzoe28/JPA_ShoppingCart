package com.flyfox.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import com.flyfox.game.core.WObject;

public class Snake extends WObject {

	private KeyCode keyCode = KeyCode.RIGHT;
	private int speed = 2;

	public Snake() {
		setX(0);
		setY(0);
		setWidth(10);
		setHeight(10);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (keyCode == KeyCode.UP && keyCode != KeyCode.DOWN) {
			moveY(-speed);
		} else if (keyCode == KeyCode.DOWN && keyCode != KeyCode.UP) {
			moveY(speed);
		} else if (keyCode == KeyCode.LEFT && keyCode != KeyCode.RIGHT) {
			moveX(-speed);
		} else if (keyCode == KeyCode.RIGHT && keyCode != KeyCode.LEFT) {
			moveX(speed);
		}
	}

	public void onKeyPressed(KeyEvent event) {
		KeyCode tmpCode = event.getCode();
		// 如果反方向那么不处理，蛇不会掉头
		if ((tmpCode == KeyCode.UP && keyCode == KeyCode.DOWN) //
				|| (tmpCode == KeyCode.DOWN && keyCode == KeyCode.UP) //
				|| (tmpCode == KeyCode.RIGHT && keyCode == KeyCode.LEFT) //
				|| (tmpCode == KeyCode.LEFT && keyCode == KeyCode.RIGHT) //
		) {
			return;
		}

		// 不接受别的按键
		if (tmpCode == KeyCode.UP || tmpCode == KeyCode.DOWN || tmpCode == KeyCode.RIGHT || tmpCode == KeyCode.LEFT) {
			keyCode = tmpCode;
		}
	}
}
