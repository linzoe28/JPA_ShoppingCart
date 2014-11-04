package com.flyfox.game;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import com.flyfox.game.core.WObject;
import com.flyfox.game.core.WSystem;

public class Snake extends WObject {

	public static final int DEFAULT_LENGTH = 10;
	AtomicInteger score = new AtomicInteger(0); // 分数
	Color snakeColor; // 颜色
	KeyCode keyCode; // 方向
	int speed; // 速度
	int hp; // 生命值
	int length; // 长度

	public Snake() {
		init();
	}

	private void init() {
		score.set(0);
		setX(0);
		setY(0);
		setWidth(10);
		setHeight(10);
		keyCode = KeyCode.RIGHT;
		snakeColor = Color.WHITE;
		speed = 2;
		hp = 10;
		length = DEFAULT_LENGTH;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(snakeColor);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		// 死逼了
		if (hp <= 0) {
			setVisible(false);
			return;
		}

		// 减少一条命，复原
		if (getX() > WSystem.WIDTH || getX() < 0 //
				|| getY() > WSystem.HEIGHT || getY() < 0) {
			setX(0);
			setY(0);
			keyCode = KeyCode.RIGHT;
			this.length = DEFAULT_LENGTH;
			reduceHp();
			return;
		}

		// 移动，但是不能调头
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

	/**
	 * 最简单的计分数
	 */
	public void addScore() {
		score.incrementAndGet();
	}

	public int getHp() {
		return hp;
	}

	public void reduceHp() {
		this.hp = this.hp - 1;
	}

	public int getLength() {
		return length;
	}

	public void addLength() {
		this.length = this.length + 1;
	}

	public Color getSnakeColor() {
		return snakeColor;
	}

}
