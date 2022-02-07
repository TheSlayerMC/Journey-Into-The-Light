package net.jitl.client.eventhandler;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.jitl.core.config.enums.HealthBarRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class RenderLivingEventHandler {

    @SubscribeEvent()
    public static void onRenderLiving(RenderLivingEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        JClientConfig config = JConfigs.CLIENT;
        HealthBarRendering healthBarRendering = config.GUI_CATEGORY.getHealthBarRendering();
        boolean neverRender = healthBarRendering == HealthBarRendering.NEVER;
        boolean onlyRenderInDebug = healthBarRendering == HealthBarRendering.IN_DEBUG_MODE;

        if (!neverRender) {
            PoseStack poseStack = event.getPoseStack();
            LivingEntity livingEntity = event.getEntity();
            EntityRenderDispatcher entityRenderDispatcher = event.getRenderer().entityRenderDispatcher;

            double height = event.getEntity().getBoundingBox().getYsize();

            String healthString = "Health: " + livingEntity.getHealth() + "/" + livingEntity.getMaxHealth();

            if (onlyRenderInDebug) {
                if (minecraft.options.renderDebug) {
                    renderHealth(minecraft, poseStack, livingEntity, healthString, 10, height, entityRenderDispatcher);
                }
            } else {
                renderHealth(minecraft, poseStack, livingEntity, healthString, 10, height, entityRenderDispatcher);
            }
        }
    }

    private static void renderHealth(Minecraft minecraft, PoseStack poseStack, LivingEntity e, String s, int distance, double height, EntityRenderDispatcher entityRenderDispatcher) {
        if (minecraft.player != null) {
            double d3 = e.distanceToSqr(minecraft.player);
            if (d3 <= distance * distance) {
                Font fontrenderer = minecraft.font;
                poseStack.pushPose();

                poseStack.translate(0, 0.5F + height, 0);
                poseStack.mulPose(entityRenderDispatcher.cameraOrientation());
                poseStack.scale(-0.025F, -0.025F, -0.025F);

                int fontX = -40;
                fontrenderer.drawShadow(poseStack, s, fontX, 0, 553648127);
                fontrenderer.drawShadow(poseStack, s, fontX, 0, -1);

                poseStack.popPose();
            }
        }
    }
}
