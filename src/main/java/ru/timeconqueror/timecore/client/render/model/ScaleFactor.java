package ru.timeconqueror.timecore.client.render.model;

import org.lwjgl.util.vector.Vector3f;

public class ScaleFactor {
	private final Vector3f scaleFactor = new Vector3f(1, 1, 1);
	private final Vector3f animationScaleFactor = new Vector3f(1, 1, 1);
	/**
	 * Will be not set or overridden by animations, so you can increase and decrease it manually.
	 * Will be reset to default at the end of {@link TimeModelRenderer#render(float)} method.
	 */
	private final Vector3f customScaleFactor = new Vector3f(1, 1, 1);

	public void setCustomScaleFactor(float x, float y, float z) {
		customScaleFactor.set(x, y, z);
		updateScale();
	}

	public void setAnimationScaleFactor(float x, float y, float z) {
		animationScaleFactor.set(x, y, z);
		updateScale();
	}

	public Vector3f getScale() {
		return scaleFactor;
	}

	public Vector3f getAnimationScaleFactor() {
		return animationScaleFactor;
	}

	public Vector3f getCustomScaleFactor() {
		return customScaleFactor;
	}

	public void reset() {
		customScaleFactor.set(1, 1, 1);
		animationScaleFactor.set(1, 1, 1);
		scaleFactor.set(1, 1, 1);
	}

	private void updateScale() {
		scaleFactor.set(animationScaleFactor.x * customScaleFactor.x, animationScaleFactor.y * customScaleFactor.y, animationScaleFactor.z * customScaleFactor.z);
	}
}
