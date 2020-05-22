package ru.timeconqueror.timecore.client.render.animation;

import org.lwjgl.util.vector.Vector3f;

public class KeyFrame {
	/**
	 * in ms
	 */
	private int startTime;
	private Vector3f vec;

	public KeyFrame(int startTime, Vector3f vec) {
		this.startTime = startTime;
		this.vec = vec;
	}

	public int getStartTime() {
		return startTime;
	}

	public Vector3f getVec() {
		return vec;
	}

	public static KeyFrame createIdleKeyFrame(int startTime, float modelIdleX, float modelIdleY, float modelIdleZ) {
		return new KeyFrame(startTime, new Vector3f(modelIdleX, modelIdleY, modelIdleZ));
	}
}
