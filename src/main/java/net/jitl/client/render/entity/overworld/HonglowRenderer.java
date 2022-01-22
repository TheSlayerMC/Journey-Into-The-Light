package net.jitl.client.render.entity.overworld;

import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.JRenderTypes;
import net.jitl.client.render.model.HongoModel;
import net.jitl.common.entity.overworld.HonglowEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class HonglowRenderer extends MobRenderer<HonglowEntity, HongoModel<HonglowEntity>> {

	public HonglowRenderer(EntityRendererProvider.Context context) {
		super(context, new HongoModel<>(context.bakeLayer(JModelLayers.HONGLOW_MODEL_LAYER)), 0.7F);
	}

	public @NotNull ResourceLocation getTextureLocation(@NotNull HonglowEntity entityIn) {
		return entityIn.getVariant().getTexture();
	}

	@Nullable
	@Override
	protected RenderType getRenderType(@NotNull HonglowEntity livingEntity, boolean boolean_, boolean boolean1_, boolean boolean2_) {
		return JRenderTypes.fullbrightCutout(getTextureLocation(livingEntity));
	}
}
