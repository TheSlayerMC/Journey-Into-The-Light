package net.journey.init;

import net.journey.JITL;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;

public class JAnimations {
	public static final Animation FLORO_WALK = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.walk.json"));
	public static final Animation FLORO_SHOOT = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.shoot.json"));
	public static final Animation FLORO_SHOWING = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.showing.json"));
}
