package ru.timeconqueror.timecore.client.render.animation;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.client.render.TimeEntityModel;
import ru.timeconqueror.timecore.api.client.render.TimeModel;

import java.util.List;

public class Animation {
	private boolean loop;
	private String name;
	/**
	 * animation length in ms
	 */
	private int length;

	@Nullable
	private List<BoneOption> options;

	public Animation(boolean loop, String name, int length, @Nullable List<BoneOption> options) {
		this.loop = loop;
		this.name = name;
		this.length = length;
		this.options = options;
	}

	public void apply(TimeEntityModel model, int existingTime) {
		TimeModel baseModel = model.getBaseModel();
		if (options != null) {
			if (existingTime <= length) {
				for (BoneOption option : options) {
					option.apply(this, baseModel, existingTime);
				}
			}
		}
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public boolean isLooped() {
		return loop;
	}
}
