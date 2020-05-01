package net.journey.client.render.mob;

import net.journey.client.render.base.RenderModMob;
import net.journey.entity.mob.overworld.underground.EntityRockiteSmasher;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderRockiteSmasher extends RenderModMob {

    public RenderRockiteSmasher(ModelBase model, ResourceLocation tex) {
        super(model, tex);
    }

    protected void applyRotations(EntityRockiteSmasher entityLiving, float f3, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, f3, rotationYaw, partialTicks);

        if ((double) entityLiving.limbSwingAmount >= 0.01D) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }
}