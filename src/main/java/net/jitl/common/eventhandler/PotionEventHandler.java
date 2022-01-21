package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.jitl.core.init.JEffects;
import net.jitl.core.init.JSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class PotionEventHandler {

    @SubscribeEvent
    public static void onPotionAdded(PotionEvent.PotionAddedEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (event.getPotionEffect().getEffect() == JEffects.FROSTBURN.get()) {
            if (entity instanceof Player) {
                playFrostburnApplySound();
            }
        }
    }

    @SubscribeEvent
    public static void onPotionRemoved(PotionEvent.PotionRemoveEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (event.getPotion().equals(JEffects.FROSTBURN.get())) {
            if (entity instanceof Player) {
                playFrostburnExpireSound();
            }
        }
    }

    @SubscribeEvent
    public static void onPotionExpired(PotionEvent.PotionExpiryEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (Objects.requireNonNull(event.getPotionEffect()).getEffect().equals(JEffects.FROSTBURN.get())) {
            if (entity instanceof Player) {
                playFrostburnExpireSound();
            }
        }
    }

    private static void playFrostburnApplySound() {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(JSounds.CRYSTAL_APPLE_FREEZE.get(), 1.0F, 1.0F));
    }

    private static void playFrostburnExpireSound() {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forLocalAmbience(JSounds.CRYSTAL_APPLE_UNFREEZE.get(), 1.0F, 1.0F));
    }
}
