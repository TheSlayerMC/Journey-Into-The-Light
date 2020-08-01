package net.journey.init;

import net.journey.JITL;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;

public class JAnimations {
	public static final Animation FLORO_WALK = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.walk.json"));
	public static final Animation FLORO_SHOOT = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.shoot.json"));
	public static final Animation FLORO_REVEAL = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.showing.json"));
	public static final Animation FLORO_HIDDEN = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.hidden.json"));
	public static final Animation FLORO_HIDE = AnimationAPI.register(AnimationAPI.reverse(FLORO_REVEAL));

	public static final Animation LAVASNAKE_TAIL = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/lavasnake.tail.json"));
	public static final Animation LAVASNAKE_SHOOT = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/lavasnake.shoot.json"));
}
