package net.jitl.client.render.entity;

import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ParticleProjectileRenderer extends EntityRenderer {
   public ParticleProjectileRenderer(EntityRenderDispatcher manager) {
      super(manager);
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getTextureLocation(Entity entityIn) {
      return TextureAtlas.LOCATION_BLOCKS;
   }
}
