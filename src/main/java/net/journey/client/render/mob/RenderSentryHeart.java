package net.journey.client.render.mob;

import net.journey.client.render.Textures;
import net.journey.client.render.base.RenderBoss;
import net.journey.client.render.model.mob.boss.ModelSentryHeart;
import net.journey.entity.mob.boss.EntitySentryHeart;
import net.journey.util.EnumHexColorHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class RenderSentryHeart extends RenderBoss {

    public RenderSentryHeart() {
        super(new ModelSentryHeart(), 0, 2.0F, Textures.blank, "sentryheart", EnumHexColorHelper.DARK_RED, EnumHexColorHelper.RED);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        EntitySentryHeart guardian = (EntitySentryHeart) entity;
        String type = "";
        switch (guardian.getStage()) {
            case 0:
                type = "sleep";
                break;
            case 1:
                type = "alert";
                break;
        }
        return new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/sentry_heart_" + type + ".png");
    }
}