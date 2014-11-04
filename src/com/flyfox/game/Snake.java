package com.flyfox.game;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import com.flyfox.game.core.WObject;
import com.flyfox.game.core.WSystem;

public class Snake extends WObject {

	public static final int DEFAULT_LENGTH = 4;
	AtomicInteger score = new AtomicInteger(0); // 分数
	Color snakeColor; // 颜色
	KeyCode keyCode; // 方向
	KeyCode tmpKeyCode; // 临时方向，保证最小单元后赋值给实际方向变量
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
		setWidth(WSystem.MIN_X);
		setHeight(WSystem.MIN_Y);
		keyCode = KeyCode.RIGHT;
		snakeColor = Color.WHITE;
		speed = 1;
		hp = 3;
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

		// 移动，但是不能调头
		if (keyCode == KeyCode.UP) {
			moveY(-speed);
		} else if (keyCode == KeyCode.DOWN) {
			moveY(speed);
		} else if (keyCode == KeyCode.LEFT) {
			moveX(-speed);
		} else if (keyCode == KeyCode.RIGHT) {
			moveX(speed);
		}

		// 判断是否需要掉头,保证最小单元
		if (tmpKeyCode == KeyCode.UP || tmpKeyCode == KeyCode.DOWN) {
			if (getX() == 0 || getX() % WSystem.MIN_X == 0) {
				keyCode = tmpKeyCode;
			}
		} else if (tmpKeyCode == KeyCode.LEFT || tmpKeyCode == KeyCode.RIGHT) {
			if (getY() == 0 || getY() % WSystem.MIN_Y == 0) {
				keyCode = tmpKeyCode;
			}
		}
	}

	public void death() {
		setX(0);
		setY(0);
		keyCode = KeyCode.RIGHT;
		this.length = DEFAULT_LENGTH;
		reduceHp();
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

		// 不接受别的按键，先存入临时变量，待达到最小单位后再赋值给实际方向变量
		if (tmpCode == KeyCode.UP || tmpCode == KeyCode.DOWN || tmpCode == KeyCode.RIGHT || tmpCode == KeyCode.LEFT) {
			tmpKeyCode = tmpCode;
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
