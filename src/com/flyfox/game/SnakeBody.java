package com.flyfox.game;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;

import com.flyfox.game.core.WObject;

public class SnakeBody extends WObject {

	Snake snake;
	int length; // 方向
	LinkedList<Point> list = new LinkedList<Point>();

	public SnakeBody(Snake snake) {
		this.snake = snake;
		this.length = snake.length;
		// 属性绑定
		this.xProperty.bindBidirectional(snake.xProperty());
		this.yProperty.bindBidirectional(snake.yProperty());
		this.widthProperty.bindBidirectional(snake.widthProperty());
		this.heightProperty.bindBidirectional(snake.heightProperty());
		// 初始化位置列表
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

		Point poi = new Point();
		poi.setX(getX());
		poi.setY(getY());
		// 添加第一个
		list.addFirst(poi);
		if (this.length != snake.length) {
			this.length = snake.length;
		} else {
			// 移除最后一个
			list.removeLast();
		}

		gc.setFill(Color.BLUE);
		for (Point point : list) {
			if (list.getFirst().equals(point))
				continue;
			gc.fillRect(point.getX(), point.getY(), getWidth(), getHeight());
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCollisionWith(WObject baseObject) {
		return false;
	}

}
