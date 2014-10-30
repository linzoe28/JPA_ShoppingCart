package com.flyfox.game.core;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Screen
 * 
 * @author wing 2012/8/25
 */
public abstract class WScreen extends Canvas implements FlyFoxEvent {
	protected enum GameState {
		GAME_MENU, GAME_START, GAME_CONTINUE, GAME_HELP, GAME_SET, GAME_EXIT, GAME_PAUSE
	};

	private List<WObject> mObjects = new ArrayList<WObject>();
	private Timeline timeline;
	private KeyFrame keyFrame;
	private int duration = 10;
	protected GameState mGameState = GameState.GAME_MENU;

	public WScreen(double width, double height) {
		super(width, height);
		initTimeLine();
	}

	public void initEvents() {
		getParent().getScene().setOnKeyPressed(event -> {
			onKeyPressed(event);
		});

		getParent().getScene().setOnKeyReleased(event -> {
			onKeyReleased(event);
		});

		getParent().getScene().setOnMouseMoved(event -> {
			onMouseMoved(event);
		});
	}

	public void onKeyPressed(KeyEvent event) {
		for (WObject wObject : mObjects) {
			wObject.onKeyPressed(event);
		}
	}

	public void onKeyReleased(KeyEvent event) {
		for (WObject wObject : mObjects) {
			wObject.onKeyReleased(event);
		}
	}

	public void onMouseMoved(MouseEvent event) {
		for (WObject wObject : mObjects) {
			wObject.onMouseMoved(event);
		}
	}

	/**
	 * add the object
	 * 
	 * @param baseObject
	 *            the object to be add
	 */
	public void addObject(WObject baseObject) {
		this.mObjects.add(baseObject);
	}

	/**
	 * remove the object
	 * 
	 * @param baseObject
	 *            the object to be remove
	 */
	public void removeObject(WObject baseObject) {
		this.mObjects.remove(baseObject);
	}

	/**
	 * remove the object with the index
	 * 
	 * @param index
	 *            the index of the object
	 */
	public void removeObjectAtIndex(int index) {
		this.mObjects.remove(index);
	}

	/**
	 * draw the objects
	 * 
	 * @param gc
	 */
	public void draw(GraphicsContext gc) {
		gc.setEffect(null);
		gc.clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < mObjects.size(); i++) {
			WObject wObject = mObjects.get(i);
			if (wObject.isVisible()) {
				wObject.draw(gc);
			}
		}
	}

	/**
	 * update all the objects
	 */
	public void update() {
		for (int i = 0; i < mObjects.size(); i++) {
			WObject wObject = mObjects.get(i);
			if (wObject.isUpdate()) {
				mObjects.get(i).update();
			}
		}
	}

	/**
	 * init the timeline
	 */
	private void initTimeLine() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

		keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				draw(getGraphicsContext2D());
				update();
			}
		});
		timeline.getKeyFrames().add(keyFrame);
	}

	/**
	 * start the update timeline
	 */
	public void start() {
		timeline.play();
	}

	/**
	 * pause the update timeline
	 */
	public void pause() {
		timeline.pause();
	}

	/**
	 * stop the update timeline
	 */
	public void stop() {
		timeline.stop();
	}

}