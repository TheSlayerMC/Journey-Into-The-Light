package net.jitl.init;

import net.jitl.JITL;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JAnimations {
	public static Animation floroWalk;
	public static Animation floroShoot;
	public static Animation floroReveal;
	public static Animation floroHidden;
    public static Animation floroHide;
    public static Animation floroIdle;

    public static Animation witherspineWalk;
    public static Animation witherspineIdle;

    public static Animation towerGuardianWalk;
    public static Animation towerGuardianSmash;

    public static Animation illagerMechWalk;
    public static Animation illagerMechThrow;

    @SubscribeEvent
    public static void registerAnimations(FMLCommonSetupEvent event) {
        floroWalk = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.walk.json"));
        floroShoot = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.shoot.json"));
        floroReveal = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.showing.json"));
        floroHidden = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.hidden.json"));
        floroIdle = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/floro.idle.json"));
        floroHide = AnimationAPI.register(AnimationAPI.reverse(floroReveal));

        witherspineWalk = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/witherspine.walk.json"));
        witherspineIdle = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/witherspine.idle.json"));

        towerGuardianWalk = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/tower_guardian.walk.json"));
        towerGuardianSmash = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/tower_guardian.smash.json"));

        illagerMechWalk = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/illager_mech.walk.json"));
        illagerMechThrow = AnimationAPI.loadAndRegisterAnimation(JITL.rl("animations/illager_mech.throw.json"));
    }
}