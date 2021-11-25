package net.jitl.client.eventhandler;

import net.jitl.init.JBlocks;
import net.jitl.init.JDimensions;
import net.jitl.init.JItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import ru.timeconqueror.timecore.api.util.client.ClientProxy;

public class ClientEventHandler {
    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        forgeBus.addListener(ClientEventHandler::onKeyEvent);
        forgeBus.addListener(ClientEventHandler::onBlockHighlight);
        forgeBus.addListener(ClientEventHandler::onFogDensityEvent);
    }

    private static void onKeyEvent(InputEvent.KeyInputEvent event) {
        KeybindEventHandler.onKeyPressed(event);
    }

    private static void onBlockHighlight(DrawHighlightEvent.HighlightBlock event) {
        BlockPos blockPos = event.getTarget().getBlockPos();
        BlockState state = ClientProxy.world().getBlockState(blockPos);
        if (state.getBlock() == JBlocks.GUARDIAN_TOWER_BRAIN && !JItems.TEST_BUG.isInMainHand(ClientProxy.player())) {
            event.setCanceled(true);
        }
    }

    public static void onFogDensityEvent(EntityViewRenderEvent.FogDensity event) {
        PlayerEntity player = Minecraft.getInstance().player;

        if(player != null && player.level.dimension() == JDimensions.FROZEN_WORLD){
            if(!(player.inventory.getArmor(3).getItem() == JItems.SNOW_GOGGLES)) {
                event.setDensity(0.15F);
            } else {
                event.setDensity(0.005F);
            }
            event.setCanceled(true);
        }
    }
}
