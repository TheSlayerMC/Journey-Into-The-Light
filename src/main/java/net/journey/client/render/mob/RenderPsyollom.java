package net.journey.client.render.mob;

import net.journey.client.render.base.RenderSizeable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class RenderPsyollom extends RenderSizeable {

    public RenderPsyollom(ModelBase model) {
        super(model, 0.5F, 1.5F, null);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        Random r = new Random();
        String[] tex = {"psyollom_0", "psyollom_1", "psyollom_2", "psyollom_3"};
        String name = "";
        switch (r.nextInt(4)) {
            case 0:
                name = tex[0];
                break;
            case 1:
                name = tex[1];
                break;
            case 2:
                name = tex[2];
                break;
            case 3:
                name = tex[3];
                break;
        }
        return new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/" + name + ".png");
    }
}