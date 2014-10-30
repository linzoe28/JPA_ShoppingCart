package com.flyfox.game.core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface FlyFoxEvent {

	default public void onKeyPressed(KeyEvent event) {
	}

	default public void onKeyReleased(KeyEvent event) {
	}

	default public void onMouseMoved(MouseEvent event) {
	}
}
