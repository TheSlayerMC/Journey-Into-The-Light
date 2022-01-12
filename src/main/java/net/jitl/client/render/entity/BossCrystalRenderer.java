package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JEntityRenderRegistry;
import net.jitl.client.render.JRenderTypes;
import net.jitl.common.entity.base.BossCrystalEntity;
import net.jitl.init.client.JsonModels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.animation.renderer.AnimatedEntityRenderer;
import ru.timeconqueror.timecore.animation.renderer.ModelConfiguration;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.client.render.model.TimeModelPart;

import java.util.Objects;

public class BossCrystalRenderer extends AnimatedEntityRenderer<BossCrystalEntity, TimeEntityModel<BossCrystalEntity>> {
    public BossCrystalRenderer(EntityRendererProvider.Context context) {
        super(context, new TimeEntityModel<>(ModelConfiguration.builder(JsonModels.BOSS_CRYSTAL).withRenderType(JRenderTypes::transparentCutout).build()));
    }

    @Override
    protected void setupAnimations(BossCrystalEntity entity, PoseStack matrixStackIn, float partialTicks) {
        super.setupAnimations(entity, matrixStackIn, partialTicks);

        Minecraft minecraft = Minecraft.getInstance();
        long worldTime = minecraft.level.getGameTime();

        float angle = worldTime % 360;
        float yScale = 4.5F;

        TimeModelPart crystal = Objects.requireNonNull(model.getPart("crystal"));
        crystal.yRot = (angle + partialTicks) * ((float) Math.PI / 180F);
        crystal.setScaleFactor(1, yScale, 1);
    }

    @Override
    public ResourceLocation getTextureLocation(BossCrystalEntity entityIn) {
        return entityIn.getTexture();
    }
}
