package com.flyfox.game.core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * 基础事件类
 * 
 * @author flyfox
 * @date 2014年11月4日
 */
public interface FFEvent {

	default public void onKeyPressed(KeyEvent event) {
	}

	default public void onKeyReleased(KeyEvent event) {
	}

	default public void onMouseMoved(MouseEvent event) {
	}
}
