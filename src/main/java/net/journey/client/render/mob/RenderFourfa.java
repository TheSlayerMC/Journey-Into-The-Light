package net.journey.client.render.mob;

import net.journey.client.render.Textures;
import net.journey.client.render.base.RenderBoss;
import net.journey.client.render.mob.layers.LayerFourfaHeldItem;
import net.journey.client.render.model.mob.boss.ModelFourfa;
import net.journey.entity.mob.boss.EntityFourfa;
import net.journey.util.EnumHexColorHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class RenderFourfa extends RenderBoss {

    public RenderFourfa() {
		super(new ModelFourfa(), 0, 2.0F, Textures.blank, "fourfa", EnumHexColorHelper.GREEN, EnumHexColorHelper.WHITE);
		this.addLayer(new LayerFourfaHeldItem(this));
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        EntityFourfa e = (EntityFourfa) entity;
        String type = "";
        switch (e.getStage()) {
            case 0:
                type = "darkness";
                break;
            case 1:
                type = "flame";
                break;
            case 2:
                type = "slowness";
                break;
            case 3:
                type = "poison";
                break;
        }
        return new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/fourfa_" + type + ".png");
    }
}