package net.jitl.client.render.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.overlay.internal.EssenceOverlay;
import net.jitl.client.render.overlay.internal.PortalOverlay;
import net.jitl.client.util.RenderUtils;
import net.jitl.core.JITL;
import net.jitl.core.init.JEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Overlays {
    protected static final ResourceLocation FROSTBURN_LOCATION = JITL.rl("textures/gui/overlay/frostburn.png");

    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(Overlays::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        OverlayRegistry.registerOverlayBottom("Frostburn", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
            gui.setupOverlayRenderState(true, false);
            frostburn(gui, mStack, partialTicks, screenWidth, screenHeight);
        });

        OverlayRegistry.registerOverlayTop("Portal", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
            gui.setupOverlayRenderState(true, false);
            portal(gui, mStack, partialTicks, screenWidth, screenHeight);
        });

        OverlayRegistry.registerOverlayTop("Essence", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
            gui.setupOverlayRenderState(true, false);
            essence(gui, mStack, partialTicks, screenWidth, screenHeight);
        });
    }

    public static void frostburn(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            if (player.hasEffect(JEffects.FROSTBURN.get())) {
                RenderUtils.renderTextureOverlay(FROSTBURN_LOCATION, 0.5F);
            }
        }
    }

    public static void portal(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
        PortalOverlay.render(partialTicks);
    }

    public static void essence(ForgeIngameGui gui, PoseStack matrixStack, float partialTicks, int width, int height) {
        EssenceOverlay.render(matrixStack, height, width);
    }
}