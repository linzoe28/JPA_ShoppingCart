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
	AtomicInteger score = new AtomicInteger(0); // ����
	Color snakeColor; // ��ɫ
	KeyCode keyCode; // ����
	int speed; // �ٶ�
	int hp; // ����ֵ
	int length; // ����

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
		// ������
		if (hp <= 0) {
			setVisible(false);
			return;
		}

		// ����һ��������ԭ
		if (getX() > WSystem.WIDTH || getX() < 0 //
				|| getY() > WSystem.HEIGHT || getY() < 0) {
			setX(0);
			setY(0);
			keyCode = KeyCode.RIGHT;
			this.length = DEFAULT_LENGTH;
			reduceHp();
			return;
		}

		// �ƶ������ǲ��ܵ�ͷ
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
		// �����������ô�������߲����ͷ
		if ((tmpCode == KeyCode.UP && keyCode == KeyCode.DOWN) //
				|| (tmpCode == KeyCode.DOWN && keyCode == KeyCode.UP) //
				|| (tmpCode == KeyCode.RIGHT && keyCode == KeyCode.LEFT) //
				|| (tmpCode == KeyCode.LEFT && keyCode == KeyCode.RIGHT) //
		) {
			return;
		}

		// �����ܱ�İ���
		if (tmpCode == KeyCode.UP || tmpCode == KeyCode.DOWN || tmpCode == KeyCode.RIGHT || tmpCode == KeyCode.LEFT) {
			keyCode = tmpCode;
		}
	}

	/**
	 * ��򵥵ļƷ���
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
