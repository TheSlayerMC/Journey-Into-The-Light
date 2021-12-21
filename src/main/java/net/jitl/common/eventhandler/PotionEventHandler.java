package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.init.JEffects;
import net.jitl.init.JSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
            if (entity instanceof PlayerEntity) {
                Minecraft.getInstance().getSoundManager().play(SimpleSound.forLocalAmbience(JSounds.CRYSTAL_APPLE_FREEZE.get(), 1.0F, 1.0F));
            }
        }
    }

    @SubscribeEvent
    public static void onPotionRemoved(PotionEvent.PotionRemoveEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (event.getPotion().equals(JEffects.FROSTBURN.get())) {
            if (entity instanceof PlayerEntity) {
                Minecraft.getInstance().getSoundManager().play(SimpleSound.forLocalAmbience(JSounds.CRYSTAL_APPLE_FREEZE.get(), 1.0F, 1.55F));
            }
        }
    }

    @SubscribeEvent
    public static void onPotionExpired(PotionEvent.PotionExpiryEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (Objects.requireNonNull(event.getPotionEffect()).getEffect().equals(JEffects.FROSTBURN.get())) {
            if (entity instanceof PlayerEntity) {
                Minecraft.getInstance().getSoundManager().play(SimpleSound.forLocalAmbience(JSounds.CRYSTAL_APPLE_FREEZE.get(), 1.0F, 1.55F));
            }
        }
    }
}
