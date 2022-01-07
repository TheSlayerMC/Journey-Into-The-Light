package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.JITL;
import net.jitl.client.render.model.frozen.CapybaraModel;
import net.jitl.common.entity.frozen.CapybaraEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CapybaraRenderer extends MobRenderer<CapybaraEntity, CapybaraModel<CapybaraEntity>> {

    public CapybaraRenderer(EntityRendererProvider.Context context) {
        super(context, new CapybaraModel(), 0.5F);
        this.addLayer(new SaddleLayer<>(this, new CapybaraModel<>(), JITL.rl("textures/entity/frozen/capybara_saddle.png")));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CapybaraEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/capybara.png");
    }

    @Override
    protected void scale(CapybaraEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 1.5F;
        matrixStackIn.scale(f, f, f);
    }
}
