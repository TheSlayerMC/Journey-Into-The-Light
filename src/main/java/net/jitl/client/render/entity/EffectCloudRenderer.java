package net.jitl.client.render.entity;

import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EffectCloudRenderer extends EntityRenderer<JEffectCloudEntity> {
   public EffectCloudRenderer(EntityRendererManager manager) {
      super(manager);
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getTextureLocation(JEffectCloudEntity entityIn) {
      return AtlasTexture.LOCATION_BLOCKS;
   }
}
