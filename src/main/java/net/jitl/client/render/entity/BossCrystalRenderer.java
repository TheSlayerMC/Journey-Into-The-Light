package net.jitl.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.common.entity.base.BossCrystalEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.renderer.AnimatedEntityRenderer;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class BossCrystalRenderer extends AnimatedEntityRenderer<BossCrystalEntity, TimeEntityModel<BossCrystalEntity>> {
    public BossCrystalRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, JEntityRenderRegistry.bossCrystalModel);
    }

    @Override
    public void render(BossCrystalEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, @NotNull IRenderTypeBuffer buffer, int packedLight) {
        Minecraft minecraft = Minecraft.getInstance();
        long worldTime = minecraft.level.getGameTime();
        TextureManager textureManager = minecraft.textureManager;

        float angle = worldTime % 360;
        float yScale = 4.5F;

        float brightness = 4.5F; //why this work as expected? :/ it should be in 0 to 1 range, how...
        float alpha = 0.7F;

        //starts
        matrixStack.pushPose();

        //colors texture
        RenderSystem.color4f(brightness, brightness, brightness, alpha);

        //offsets model by camera's position
        RenderSystem.translated(0, 2, 0);

        //binds texture
        textureManager.bind(entity.getTexture());

        //rotates model
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(angle + partialTicks));

        //Json Model is adapted for LivingRenderer, where this operation is called to mirror the model by X and Y axis, so we need to reproduce it here
        matrixStack.scale(-1.0F, -1.0F, 1.0F);

        matrixStack.scale(1, yScale, 1);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableAlphaTest();

        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();

        //ends
        matrixStack.popPose();

        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(BossCrystalEntity entityIn) {
        return entityIn.getTexture();
    }
}
