package com.flyfox.game;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import com.flyfox.game.core.FFObject;
import com.flyfox.game.core.FFContants;

public class Snake extends FFObject {

	public static final int DEFAULT_LENGTH = 8;
	AtomicInteger score = new AtomicInteger(0); // ����
	Color snakeColor; // ��ɫ
	KeyCode keyCode; // ����
	KeyCode tmpKeyCode; // ��ʱ���򣬱�֤��С��Ԫ��ֵ��ʵ�ʷ������
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
		setWidth(FFContants.MIN_X);
		setHeight(FFContants.MIN_Y);
		keyCode = KeyCode.RIGHT;
		tmpKeyCode =  KeyCode.RIGHT;
		snakeColor = Color.WHITE;
		speed = 2;
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
		// ������
		if (hp <= 0) {
			setVisible(false);
			return;
		}

		// �ƶ������ǲ��ܵ�ͷ
		if (keyCode == KeyCode.UP) {
			moveY(-speed);
		} else if (keyCode == KeyCode.DOWN) {
			moveY(speed);
		} else if (keyCode == KeyCode.LEFT) {
			moveX(-speed);
		} else if (keyCode == KeyCode.RIGHT) {
			moveX(speed);
		}

		// �ж��Ƿ���Ҫ��ͷ,��֤��С��Ԫ
		if (tmpKeyCode == KeyCode.UP || tmpKeyCode == KeyCode.DOWN) {
			if (getX() == 0 || getX() % FFContants.MIN_X == 0) {
				keyCode = tmpKeyCode;
			}
		} else if (tmpKeyCode == KeyCode.LEFT || tmpKeyCode == KeyCode.RIGHT) {
			if (getY() == 0 || getY() % FFContants.MIN_Y == 0) {
				keyCode = tmpKeyCode;
			}
		}
	}

	public void death() {
		setX(0);
		setY(0);
		keyCode = KeyCode.RIGHT;
		tmpKeyCode =  KeyCode.RIGHT;
		this.length = DEFAULT_LENGTH;
		reduceHp();
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

		// �����ܱ�İ������ȴ�����ʱ���������ﵽ��С��λ���ٸ�ֵ��ʵ�ʷ������
		if (tmpCode == KeyCode.UP || tmpCode == KeyCode.DOWN || tmpCode == KeyCode.RIGHT || tmpCode == KeyCode.LEFT) {
			tmpKeyCode = tmpCode;
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
