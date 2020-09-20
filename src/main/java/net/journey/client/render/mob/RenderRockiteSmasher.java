package net.journey.client.render.mob;

import net.journey.client.render.Textures;
import net.journey.client.render.base.RenderBoss;
import net.journey.client.render.model.mob.overworld.underground.ModelRockiteSmasher;
import net.journey.entity.mob.boss.EntityRockiteSmasher;
import net.journey.util.EnumHexColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class RenderRockiteSmasher extends RenderBoss {

    public RenderRockiteSmasher() {
        super(new ModelRockiteSmasher(), 0, 1.2F, Textures.blank, "rockite_smasher", EnumHexColor.RED, EnumHexColor.BLACK);
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

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/rockitesmasher.png");
    }
}