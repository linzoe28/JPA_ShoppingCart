package com.flyfox.game.core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * �����¼��࣬����JDK8Ĭ��ʱ������
 * 
 * @author flyfox
 * @date 2014��11��4��
 */
public interface FFEvent {

	/**
	 * ��ʼ���¼�
	 * 
	 */
	default public void init() {
	}

	/**
	 * Ĭ�ϰ��������¼�
	 * 
	 * @param event
	 */
	default public void onKeyPressed(KeyEvent event) {
	}

	/**
	 * Ĭ�ϰ����ͷ��¼�
	 * 
	 * @param event
	 */
	default public void onKeyReleased(KeyEvent event) {
	}

	/**
	 * Ĭ������ƶ��¼�
	 * 
	 * @param event
	 */
	default public void onMouseMoved(MouseEvent event) {
	}
}
