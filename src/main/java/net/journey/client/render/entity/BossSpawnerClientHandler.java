package net.journey.client.render.entity;

import net.journey.entity.functional.EntityBossSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import ru.timeconqueror.timecore.api.util.MathUtils;

import static net.journey.entity.functional.EntityBossSpawner.State;

public class BossSpawnerClientHandler {
	private static final int MAX_HEIGHT = 5;
	private static final int MAX_ROTATION_SPEED = 25;
	private static final double HEIGHT_OF_RENDERED_ENTITY = 1.3; // in blocks

	private final EntityBossSpawner entity;
	private float prevRotationAngle;
	private float rotationAngle;
	private float prevTranslationY;
	private float translationY;
	private double scale;

	public BossSpawnerClientHandler(EntityBossSpawner entity) {
		this.entity = entity;
	}

	public void init() {
		Entity entityToSpawn = entity.getEntityToSpawn();

		if (entityToSpawn != null) {
			AxisAlignedBB boundingBox = entityToSpawn.getEntityBoundingBox();

			scale = HEIGHT_OF_RENDERED_ENTITY / (boundingBox.maxY - boundingBox.minY);
		}
	}

	public void onTick() {
		int current = entity.getTicksFromActivating();
		int total = entity.getTotalTicksBeforeSpawn();

		recalculateRotation(current, total);
		recalculateTranslation(current, total);
	}

	private void recalculateRotation(int currentTime, int total) {
		prevRotationAngle = rotationAngle;

		float risingEndTime = State.RISING.getEndTime(total);
		float speedFactor = MathUtils.calcPercentage(currentTime, 0, risingEndTime);
		float speed = MathUtils.interpolate(speedFactor, 0, MAX_ROTATION_SPEED);

		float baseAngle = 5;
		rotationAngle += 10 + (baseAngle * speed);
	}

	private void recalculateTranslation(int currentTime, int total) {
		prevTranslationY = translationY;

		State currentState = State.getCurrentState(currentTime, total);

		if (currentState == State.STAYING) {
			translationY = 0F;
		} else if (currentState == State.RISING) {
			float startTime = currentState.getStartTime(total);
			float endTime = currentState.getEndTime(total);

			float factor = MathUtils.calcPercentage(currentTime, startTime, endTime);
			translationY = MathUtils.interpolate(factor, 0, MAX_HEIGHT);
		} else if (currentState == State.FALLING) {
			float startTime = currentState.getStartTime(total);
			float endTime = currentState.getEndTime(total);

			float factor = MathUtils.calcPercentage(currentTime, startTime, endTime);
			translationY = MathUtils.interpolate(factor, MAX_HEIGHT, 0);
		}
	}

	public float getTranslationY(float partialTicks) {
		return MathUtils.interpolate(partialTicks, prevTranslationY, translationY);
	}

	public float getRotationAngle(float partialTicks) {
		return MathUtils.interpolate(partialTicks, prevRotationAngle, rotationAngle);
	}

	public State getCurrentState() {
		return State.getCurrentState(entity.getTicksFromActivating(), entity.getTotalTicksBeforeSpawn());
	}

	public double getScale() {
		return scale;
	}

	public double getRayOffsetY() {
		return HEIGHT_OF_RENDERED_ENTITY / 2 + (2F / 16 /* 1 / 16 of block */);
	}
}
