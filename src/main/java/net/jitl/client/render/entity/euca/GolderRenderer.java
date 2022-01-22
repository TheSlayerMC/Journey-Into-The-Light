package net.jitl.client.render.entity.euca;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.model.euca.GolderModel;
import net.jitl.common.entity.euca.GolderEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GolderRenderer extends MobRenderer<GolderEntity, GolderModel<GolderEntity>> {

    public GolderRenderer(EntityRendererProvider.Context context) {
        super(context, new GolderModel<>(context.bakeLayer(JModelLayers.GOLDER_MODEL_LAYER)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GolderEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/euca/golder.png");
    }

    @Override
    protected void scale(GolderEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 1.5F;
        matrixStackIn.scale(f, f, f);
    }

}
