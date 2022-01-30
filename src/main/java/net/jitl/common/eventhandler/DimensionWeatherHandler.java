package net.jitl.common.eventhandler;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.FogDensity;
import net.jitl.core.JITL;
import net.jitl.core.init.world.Dimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = JITL.MODID)
public class DimensionWeatherHandler {

    @SubscribeEvent
    public static void handleDimensionWeather(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level;
        if (level.dimension() == Dimensions.FROZEN_LANDS) {
            JPlayer jPlayer = JPlayer.from(player);
            if (jPlayer != null) {
                FogDensity fogDensity = jPlayer.fogDensity;
                if (fogDensity.isDensityEnabled()) {
                    level.setRainLevel(0.0F);
                } else {
                    level.setRainLevel(5.0F);
                }
                jPlayer.detectAndSendChanges();
            }
        }
    }
}
