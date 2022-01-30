package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.jitl.core.init.world.Dimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class DimensionWeatherHandler {

    @SubscribeEvent
    public static void handleDimensionWeather(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            for (Player player : event.player.level.players()) {
                Level level = player.level;
                if (level.dimension() == Dimensions.FROZEN_LANDS) {
                    player.level.setRainLevel(5.0F);
                }
            }
        }
    }
}
