package net.jitl.client.render.overlay.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.jitl.core.util.IEssenceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class EssenceOverlay {
    private static float transparency;
    private static float burnoutTransparency;

    private static final ResourceLocation UNDER_CROSSHAIR_TEXTURE = JITL.tl("gui/essence_under_crosshair.png").fullLocation();
    private static final ResourceLocation OVER_EXP_TEXTURE = JITL.tl("gui/essence_over_exp.png").fullLocation();
    private static final ResourceLocation ABOVE_HUNGER_TEXTURE = JITL.tl("gui/essence_over_hunger.png").fullLocation();

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

                /*
                 * Handles the transparency of the Essence bar
                 */
                if ((instanceOfEssenceItem(player.getMainHandItem().getItem()) || isEssenceUsed) && transparency <= 1.0) {
                    transparency += .02;
                } else if (transparency > 0) {
                    transparency -= .02;
                }

                boolean cooldownActive = cooldown > 1.0F;

                /*
                 * Handles the transparency of the burnout overlay
                 */
                if (cooldownActive && burnoutTransparency < 1) {
                    burnoutTransparency += .02;
                } else if (burnoutTransparency > 0) {
                    burnoutTransparency -= .02;
                }

                /*
                 * Handles the configured position of the Essence bar
                 */
                JClientConfig.GuiCategory config = JConfigs.CLIENT.GUI_CATEGORY;
                EssencePosition essencePosition = config.getEssencePosition();
                int yPos = config.getEssenceYPos();
                int xPos = config.getEssenceXPos();

                if (!minecraft.options.hideGui && transparency > 0 && !player.isSpectator()) {
                    boolean belowCrosshair = essencePosition == EssencePosition.BELOW_CROSSHAIR;

                    /*
                     * We apply a separate algorithm if the bar is configured to be rendered under the crosshair
                     */
                    int crosshairY = height / 2;
                    int crosshairX = width / 2;

                    /*
                     * Default algorithm for all other position types
                     */
                    int y = belowCrosshair ? crosshairY : height - yPos;
                    int x = belowCrosshair ? crosshairX : width / 2 - xPos;

                    boolean aboveHunger = essencePosition == EssencePosition.ABOVE_HUNGER_BAR;
                    boolean isUnderwater = player.isEyeInFluid(FluidTags.WATER) || Math.min(player.getAirSupply(), player.getMaxAirSupply()) < player.getMaxAirSupply();
                    /*
                     * Checks if the bubble meter should render, and if the Essence position should be above the hunger bar.
                     * If true, moves the Essence bar up 10 pixels
                     */
                    if (isUnderwater && aboveHunger)
                        y -= 10;

                    LivingEntity livingentity = getPlayerVehicleWithHealth(player);
                    int vehicalMaxhearts = getVehicleMaxHearts(livingentity);

                    /*
                     * Checks if the player is riding a living vehicle (like a horse or a pig)
                     * If true, moves the Essence bar up according to the number of heart rows the vehicle has
                     */
                    if (vehicalMaxhearts > 0 && aboveHunger) {
                        int heartRows = (int) Math.ceil((double) vehicalMaxhearts / 10.0D) - 1;
                        y -= heartRows * 10;
                    }

                    matrixStack.pushPose();

                    /*
                     * Translates and scales the bar if it's rendered below the crosshair
                     */
                    if (belowCrosshair) {
                        float scale = 0.5F;
                        double widthTranslation = (width / 2F) - 42;
                        double heightTranslation = (height / 2F) + 42;
                        matrixStack.translate(widthTranslation, heightTranslation, 0);
                        matrixStack.scale(scale, scale, 0);
                        matrixStack.translate(-widthTranslation, -heightTranslation, 0);

                        RenderSystem.enableBlend();
                        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ZERO);
                    }

                    /*
                     * Adds a slight transparency modifier if the bar is rendered below the crosshair
                     */
                    float addedAlpha = belowCrosshair ? 0.5F : 0;

                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderTexture(0, getTextureBasedOnPosition(essencePosition));
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, transparency - addedAlpha);

                    /*
                     * Our texture is relative to the configured position.
                     * So, we have to adapt the UV, render height and texture height of the rectangle depending on the configured position.
                     */
                    boolean renderSmall = essencePosition == EssencePosition.OVER_EXPERIENCE_BAR || essencePosition == EssencePosition.BELOW_CROSSHAIR;
                    int barHeight = renderSmall ? 5 : 9;
                    int texHeight = renderSmall ? 15 : 27;
                    int backgroundVOffset = renderSmall ? 5 : 9;
                    int burnoutVOffset = renderSmall ? 10 : 18;

                    RenderUtils.blit(matrixStack, x, y, 0, backgroundVOffset, 81, barHeight, 81, texHeight);

                    if (cooldownActive) {
                        /*
                         * Sin function ranging from 0 to 1
                         */
                        float sin = (float) Math.sin((float) player.tickCount / 5F) / 2F + 0.5F;
                        /*
                         * When the cooldown starts getting close to zero, the bar fades out.
                         */
                        float cooldownFade = Math.min(cooldown, 10) / 10;
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (sin * cooldownFade) - addedAlpha);

                        RenderUtils.blit(matrixStack, x, y, 0, 0, 81, barHeight, 81, texHeight);
                    } else {
                        int i = (int) ((currentEssence / maxEssence) * 81);
                        RenderUtils.blit(matrixStack, x, y, 0, 0, i, barHeight, 81, texHeight);
                    }

                    if (burnoutTransparency > 0) {
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, burnoutTransparency - addedAlpha);
                        RenderUtils.blit(matrixStack, x, y, 0, burnoutVOffset, 81, barHeight, 81, texHeight);
                    }
                    matrixStack.popPose();
                }
            }
        }
    }

    /**
     * Method used to group all Essence-related items under one umbrella.
     * Allows Essence transparency rendering to be compatible with (hopefully) all Essence-related items without referencing many individual classes/items
     */
    private static boolean instanceOfEssenceItem(Item isEssence) {
        return isEssence instanceof IEssenceItem;
    }

    /**
     * Returns a texture based off of the configured position set by the player.
     */
    private static ResourceLocation getTextureBasedOnPosition(EssencePosition essencePosition) {
        if (essencePosition == EssencePosition.OVER_EXPERIENCE_BAR) {
            return OVER_EXP_TEXTURE;
        }
        if (essencePosition == EssencePosition.BELOW_CROSSHAIR) {
            return UNDER_CROSSHAIR_TEXTURE;
        } else {
            return ABOVE_HUNGER_TEXTURE;
        }
    }

    private static int getVehicleMaxHearts(LivingEntity mountEntity) {
        if (mountEntity != null && mountEntity.showVehicleHealth()) {
            float maxHealth = mountEntity.getMaxHealth();
            int i = (int) (maxHealth + 0.5F) / 2;
            if (i > 30) {
                i = 30;
            }

            return i;
        } else {
            return 0;
        }
    }

    private static LivingEntity getPlayerVehicleWithHealth(Player player) {
        if (player != null) {
            Entity entity = player.getVehicle();
            if (entity == null) {
                return null;
            }

            if (entity instanceof LivingEntity) {
                return (LivingEntity) entity;
            }
        }

        return null;
    }
}
