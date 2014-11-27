package com.flyfox.game;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.flyfox.game.core.FFApplication;
import com.flyfox.game.core.FFContants;

public class MainSnake extends FFApplication {

	@Override
	protected void before() {
		setWindowSize(FFContants.WIDTH, FFContants.HEIGHT);
	}

	@Override
	protected void after() {
		GameScreen gameScreen = new GameScreen(FFContants.WIDTH, FFContants.HEIGHT);
		getRoot().getChildren().add(gameScreen);
		gameScreen.start();
		gameScreen.initEvents();

		getScene().setFill(Color.BLACK);
	}

	@Override
	protected void showStage(Stage stage) {
		super.showStage(stage);
		stage.setTitle("JavaFX游戏开发 贪吃蛇");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
