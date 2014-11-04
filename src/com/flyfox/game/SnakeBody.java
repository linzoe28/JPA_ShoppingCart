package com.flyfox.game;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;

import com.flyfox.game.core.WObject;

public class SnakeBody extends WObject {

	Snake snake;
	int length; // ����
	LinkedList<Point> list = new LinkedList<Point>();

	public SnakeBody(Snake snake) {
		this.snake = snake;
		this.length = snake.length;
		// ���԰�
		this.xProperty.bindBidirectional(snake.xProperty());
		this.yProperty.bindBidirectional(snake.yProperty());
		this.widthProperty.bindBidirectional(snake.widthProperty());
		this.heightProperty.bindBidirectional(snake.heightProperty());
		// ��ʼ��λ���б�
		init();
	}

	private void init() {
		list.clear();
		for (int i = 0; i < snake.getLength(); i++) {
			Point point = new Point();
			point.setX(getX() - getWidth() * i);
			point.setY(getY());
			list.add(point);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (snake.getLength() <= 1) {
			return;
		}

		// ԭ���ƶ�һ�Σ���ô��һ����λ�þ͵���ǰһ����λ�ã�Ҳ���Ǽ����µ�first��ɾ���ɵ�last
		Point firstPoi = list.getFirst();
		// λ���Ѿ��ﵽһ����λ�ٽ��д���
		if (firstPoi.getX() + getWidth() <= getX() || firstPoi.getX() - getWidth() >= getX() //
				|| firstPoi.getY() + getHeight() <= getY() || firstPoi.getY() - getHeight() >= getY()) {
			Point poi = new Point();
			poi.setX(getX());
			poi.setY(getY());
			// ��ӵ�һ��ͷ
			list.addFirst(poi);
			// ����Ե��˾Ͳ��Ƴ��ˣ�û�Ե���ɾ�����һ��
			if (this.length < snake.length) {
				this.length = this.length + 1;
			} else {
				// �Ƴ����һ��
				list.removeLast();
			}
		}

		gc.setFill(snake.getSnakeColor());
		// �����������ɫ
		// gc.setFill(Color.BLUE);
		for (Point point : list) {
			// Ϊ��������~��һ����Ҳ��������
			gc.fillRect(point.getX(), point.getY(), getWidth(), getHeight());
		}
	}

	@Override
	public void update() {
		// �������ʾ�ˣ�����չʾ
		if (!isVisible()) {
			init();
			setVisible(true);
			return;
		}
	}

	@Override
	public boolean isCollisionWith(WObject baseObject) {
		for (Point point : list) {
			if (list.getFirst().equals(point))
				continue;

			if (isCollisionWith(point.getX(), point.getY(), baseObject))
				return true;
		}
		return false;
	}

	private boolean isCollisionWith(double x, double y, WObject baseObject) {
		if (x + getWidth() > baseObject.getX() && x < baseObject.getX() + baseObject.getWidth()
				&& y + getHeight() > baseObject.getY() && y < baseObject.getY() + baseObject.getHeight()) {
			return true;
		}
		return false;
	}

}
