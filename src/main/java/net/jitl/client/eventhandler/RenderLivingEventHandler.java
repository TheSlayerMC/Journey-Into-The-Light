package net.jitl.client.eventhandler;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.util.RenderUtils;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.jitl.core.config.enums.HealthBarRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.api.util.client.DrawHelper;

import java.text.DecimalFormat;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class RenderLivingEventHandler {
    private static final ResourceLocation HEALTH_BAR = JITL.tl("gui/health_bar.png").fullLocation();

    @SubscribeEvent()
    public static void onRenderLiving(RenderLivingEvent.Pre event) {
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

            Vec3 vector3d = new Vec3(livingEntity.getX(), livingEntity.getY(0.5), livingEntity.getZ());
            Vec3 vector3d1 = new Vec3(minecraft.player.getX(), minecraft.player.getY(0.8), minecraft.player.getZ());
            boolean canSee = livingEntity.level.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, livingEntity)).getType() == HitResult.Type.MISS;

            if (canSee) {
                if (onlyRenderInDebug) {
                    if (minecraft.options.renderDebug) {
                        renderHealth(minecraft, poseStack, livingEntity, height, entityRenderDispatcher);
                    }
                } else {
                    renderHealth(minecraft, poseStack, livingEntity, height, entityRenderDispatcher);
                }
            }
        }
    }

    private static void renderHealth(Minecraft minecraft, PoseStack poseStack, LivingEntity e, double height, EntityRenderDispatcher entityRenderDispatcher) {
        if (minecraft.player != null) {
            int distance = 10;
            double d3 = e.distanceToSqr(minecraft.player);
            if (d3 <= distance * distance) {
                Font fontrenderer = minecraft.font;
                poseStack.pushPose();

                float scale = -0.025F;
                poseStack.translate(0, 0.5F + height, 0);
                poseStack.mulPose(entityRenderDispatcher.cameraOrientation());
                poseStack.scale(scale, scale, scale);

                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(770, 771, 1, 0);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, HEALTH_BAR);

                float health = e.getHealth();
                float maxHealth = e.getMaxHealth();

                int red = 16711680;
                int yellow = 16776960;
                int green = 65280;

                int color = 0;
                if (health <= maxHealth / 3)
                    color = red;
                else if (health <= maxHealth / 2 && health > maxHealth / 3)
                    color = yellow;
                else if (health <= maxHealth && health > maxHealth / 2)
                    color = green;

                float r = 0, g = 0, b = 0;

                if (color == red) {
                    r = DrawHelper.getRed(red) / 255F;
                    g = DrawHelper.getGreen(red) / 255F;
                    b = DrawHelper.getBlue(red) / 255F;

                } else if (color == yellow) {
                    r = DrawHelper.getRed(yellow) / 255F;
                    g = DrawHelper.getGreen(yellow) / 255F;
                    b = DrawHelper.getBlue(yellow) / 255F;

                } else if (color == green) {
                    r = DrawHelper.getRed(green) / 255F;
                    g = DrawHelper.getGreen(green) / 255F;
                    b = DrawHelper.getBlue(green) / 255F;
                }

                RenderSystem.setShaderColor(r, g, b, 1.0F);

                int barX = -40;
                RenderUtils.blit(poseStack, barX, 0, 0, 5, 81, 5, 81, 10);

                int i = (int) ((health / maxHealth) * 81);
                RenderUtils.blit(poseStack, barX, 0, 0, 0, i, 5, 81, 10);

                DecimalFormat df = new DecimalFormat("##########.#");
                String s = "Health: " + df.format(health) + "/" + df.format(maxHealth);

                //TODO: disable depth test for font and bar
                int fontX = -fontrenderer.width(s) / 2;
                int fontY = -10;
                fontrenderer.draw(poseStack, s, fontX + 1, fontY, 0);
                fontrenderer.draw(poseStack, s, fontX - 1, fontY, 0);
                fontrenderer.draw(poseStack, s, fontX, fontY + 1, 0);
                fontrenderer.draw(poseStack, s, fontX, fontY - 1, 0);
                fontrenderer.draw(poseStack, s, fontX, fontY, color);

                RenderSystem.enableDepthTest();
                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();

                poseStack.popPose();
            }
        }
    }
}
