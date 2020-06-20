package net.journey.client.render.entity;

import net.journey.entity.functional.EntityBossSpawner;
import ru.timeconqueror.timecore.api.util.MathUtils;

import static net.journey.entity.functional.EntityBossSpawner.State;

public class BossSpawnerClientHandler {
	private static final int MAX_HEIGHT = 5;
	private static final int MAX_SPEED = 25;

	private final EntityBossSpawner entity;
	private float prevRotationAngle;
	private float rotationAngle;
	private float prevTranslationY;
	private float translationY;

	public BossSpawnerClientHandler(EntityBossSpawner entity) {
		this.entity = entity;
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

	public void onTick() {
		prevRotationAngle = rotationAngle;
		int current = entity.getTicksFromActivating();
		int total = entity.getTotalTicksBeforeSpawn();

		float risingEndTime = State.RISING.getEndTime(total);
		float speedFactor = MathUtils.calcPercentage(current, 0, risingEndTime);
		float speed = MathUtils.interpolate(speedFactor, 0, MAX_SPEED);

		float baseAngle = 5;
		rotationAngle += 10 + (baseAngle * speed);


		State currentState = State.getCurrentState(current, total);
		prevTranslationY = translationY;
		if (currentState == State.STAYING) {
			translationY = 0F;
		} else if (currentState == State.RISING) {
			float startTime = currentState.getStartTime(total);
			float endTime = currentState.getEndTime(total);

			float factor = MathUtils.calcPercentage(current, startTime, endTime);
			translationY = MathUtils.interpolate(factor, 0, MAX_HEIGHT);
		} else if (currentState == State.FALLING) {
			float startTime = currentState.getStartTime(total);
			float endTime = currentState.getEndTime(total);

			float factor = MathUtils.calcPercentage(current, startTime, endTime);
			translationY = MathUtils.interpolate(factor, MAX_HEIGHT, 0);
		}
	}
}
