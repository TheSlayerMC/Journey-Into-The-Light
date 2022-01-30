package net.jitl.client.eventhandler;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JItems;
import net.jitl.core.init.world.Dimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawSelectionEvent;
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

    private static void onBlockHighlight(DrawSelectionEvent.HighlightBlock event) {
        BlockPos blockPos = event.getTarget().getBlockPos();
        BlockState state = ClientProxy.world().getBlockState(blockPos);
        if (state.getBlock() == JBlocks.GUARDIAN_TOWER_BRAIN && !JItems.TEST_BUG.isInMainHand(ClientProxy.player())) {
            event.setCanceled(true);
        }
    }

    public static void onFogDensityEvent(EntityViewRenderEvent.RenderFogEvent event) {
        float farPlaneDistance = event.getFarPlaneDistance();
        Player player = ClientProxy.player();
        if (player != null) {
            JPlayer cap = JPlayer.from(player);
            if (player.level.dimension() == Dimensions.FROZEN_LANDS) {
                float density = 0.15F;
                float curioMult = 2.33F;
                float capMult = 8.66F;
                float curioCapMult = (curioMult + 2.0F) + capMult;

                if (CuriosApi.getCuriosHelper().findEquippedCurio(JItems.EYE_OF_THE_BLIZZARD, player).isPresent()) {
                    if (cap != null) {
                        if (!cap.frozenBlizzard.isBlizzardDisabled()) {
                            density *= curioMult;
                        } else {
                            density *= curioCapMult;
                        }
                    } else {
                        density *= curioMult;
                    }
                } else if (cap != null) {
                    if (cap.frozenBlizzard.isBlizzardDisabled()) {
                        density *= capMult;
                    }
                }
                RenderSystem.setShaderFogStart(density);
                RenderSystem.setShaderFogEnd(density * farPlaneDistance);
            }
        }
    }
}