package net.journey.client.render.mob;

import net.journey.client.render.Textures;
import net.journey.client.render.base.RenderBoss;
import net.journey.client.render.model.mob.boss.ModelGuardianOfDestruction;
import net.journey.entity.mob.boss.EntityGuardianOfDestruction;
import net.journey.util.EnumHexColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class RenderGuardianOfDestruction extends RenderBoss {

    public RenderGuardianOfDestruction() {
		super(new ModelGuardianOfDestruction(), 0, 2.0F, Textures.blank, "guardianofdestruction", EnumHexColor.ORANGE, EnumHexColor.RED);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        EntityGuardianOfDestruction guardian = (EntityGuardianOfDestruction) entity;
        String type = "";
        switch (guardian.getStage()) {
            case 0:
                type = "sleep";
                break;
            case 1:
                type = "alert";
                break;
            case 2:
                type = "lowhealth";
                break;
        }
        return new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/guardian_destruction_" + type + ".png");
    }
}