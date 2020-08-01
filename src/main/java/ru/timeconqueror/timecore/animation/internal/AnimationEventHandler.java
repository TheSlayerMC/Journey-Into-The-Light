package ru.timeconqueror.timecore.animation.internal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.timeconqueror.timecore.animation.ServerAnimationManager;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.common.network.S2CSyncEntityAnimationsMsg;
import ru.timeconqueror.timecore.common.network.TCNetworkHandler;

@Mod.EventBusSubscriber
public class AnimationEventHandler {

	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entityLiving = event.getEntityLiving();

		if (entityLiving instanceof AnimationProvider<?>) {
			if (entityLiving.isServerWorld()) {
				//needed for animation ticking on server side.
				((AnimationProvider<?>) entityLiving).getActionManager().getAnimationManager().applyAnimations(null);
			}

			ActionManager<?> actionManager = ((AnimationProvider<?>) entityLiving).getActionManager();
			actionManager.onTick();
		}
	}

	@SubscribeEvent
	public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
		Entity target = event.getTarget();
		if (target instanceof AnimationProvider<?>) {
			AnimationManager animationManager = ((AnimationProvider<?>) target).getActionManager().getAnimationManager();
			ServerAnimationManager<?> serverAnimationManager = (ServerAnimationManager<?>) animationManager;
			TCNetworkHandler.INSTANCE.sendTo(new S2CSyncEntityAnimationsMsg(serverAnimationManager, target), ((EntityPlayerMP) event.getEntityPlayer()));
		}
	}
}
