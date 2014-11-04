package com.flyfox.game.core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * �����¼���
 * 
 * @author flyfox
 * @date 2014��11��4��
 */
public interface FFEvent {

	default public void onKeyPressed(KeyEvent event) {
	}

	default public void onKeyReleased(KeyEvent event) {
	}

	default public void onMouseMoved(MouseEvent event) {
	}
}
