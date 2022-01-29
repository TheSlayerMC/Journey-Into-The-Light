package net.jitl.client.render.overlay.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.core.JITL;
import net.jitl.core.util.IEssenceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class EssenceOverlay {
    private static float transparency;
    private static float burnoutTransparency;

    public static void render(PoseStack matrixStack, int height, int width) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null && !player.isCreative() && !player.isSpectator()) {
            JPlayer cap = JPlayer.from(player);
            if (cap != null) {
                float currentEssence = cap.essence.getCurrentEssence();
                float maxEssence = Essence.getMaxEssence(player);
                float cooldown = cap.essence.getBurnout();

                boolean isEssenceUsed = currentEssence < maxEssence;
                if ((instanceOfEssenceItem(player.getMainHandItem().getItem()) || isEssenceUsed) && transparency <= 1.0) {
                    transparency += .02;
                } else if (transparency > 0) {
                    transparency -= .02;
                }

                boolean cooldownActive = cooldown > 1.0F;

                if (cooldownActive && burnoutTransparency < 1) {
                    burnoutTransparency += .02;
                } else if (burnoutTransparency > 0) {
                    burnoutTransparency -= .02;
                }

                if (!minecraft.options.hideGui && transparency > 0) {
                    int l = height - 32 + 3;
                    int w = width / 2 - 91;

                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderTexture(0, JITL.tl("gui/essence.png").fullLocation());
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, transparency);

                    RenderUtils.blit(matrixStack, w, l, 0, 5, 81, 5, 81, 15);

                    if (cooldownActive) {
                        float sin = (float) Math.sin((float) player.tickCount / 5F) / 2F + 0.5F; //sin function ranging from 0 to 1
                        float cooldownFade = Math.min(cooldown, 10) / 10; //when the cooldown starts getting close to zero, it fades out

                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sin * cooldownFade);
                        RenderUtils.blit(matrixStack, w, l, 0, 0, 81, 5, 81, 15);
                    } else {
                        int i = (int) ((currentEssence / maxEssence) * 81);
                        RenderUtils.blit(matrixStack, w, l, 0, 0, i, 5, 81, 15);
                    }


                    if (burnoutTransparency > 0) {
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, burnoutTransparency);
                        RenderUtils.blit(matrixStack, w, l, 0, 10, 81, 5, 81, 15);
                    }
                }
            }
        }
    }

    /**
     * Method used to group all Essence-related items under one umbrella.
     * Allows Essence transparency rendering to be compatible with (hopefully) all Essence-related items without referencing many individual classes/items
     */
    public static boolean instanceOfEssenceItem(Item isEssence) {
        return isEssence instanceof IEssenceItem;
    }
}
