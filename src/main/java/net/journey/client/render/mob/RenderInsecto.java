package net.journey.client.render.mob;

import net.journey.client.render.RenderModMob;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class RenderInsecto extends RenderModMob {

    public RenderInsecto(ModelBase model) {
        super(model, null);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        Random r = new Random();
        String[] tex = {"insecto_0", "insecto_1", "insecto_2", "insecto_3"};
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