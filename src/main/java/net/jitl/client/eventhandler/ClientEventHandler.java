package net.jitl.client.eventhandler;

import net.jitl.init.JBlocks;
import net.jitl.init.JDimensions;
import net.jitl.init.JItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.api.util.client.ClientProxy;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(Dist.CLIENT)
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
        PlayerEntity player = ClientProxy.player();

        if(player != null) {
            if (player.level.dimension() == JDimensions.FROZEN_WORLD) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(JItems.EYE_OF_THE_BLIZZARD, player).isPresent()) {
                    event.setDensity(0.05F);
                } else {
                    event.setDensity(0.15F);
                }
                event.setCanceled(true);
            }
        }
    }
}
