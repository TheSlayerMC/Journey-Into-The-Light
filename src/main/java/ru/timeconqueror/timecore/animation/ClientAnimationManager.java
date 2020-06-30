package ru.timeconqueror.timecore.animation;

import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class ClientAnimationManager extends BaseAnimationManager {
	public ClientAnimationManager(@Nullable AnimationSetting walkingAnimationStarter) {
		super(walkingAnimationStarter);
	}

	@Override
	protected void applyAnimation(TimeEntityModel model, Layer layer, AnimationWatcher watcher, long currentTime) {
		Animation animation = watcher.getAnimation();
		animation.apply(model, layer, watcher.getExistingTime(currentTime));
	}

	@Override
	public boolean isGamePaused() {
		return Minecraft.getMinecraft().isGamePaused();
	}
}
