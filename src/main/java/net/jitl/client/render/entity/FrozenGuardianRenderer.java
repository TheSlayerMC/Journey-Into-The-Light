package net.jitl.client.render.entity;


import net.jitl.JITL;
import net.jitl.client.render.model.frozen.FrozenTrollModel;
import net.jitl.common.entity.frozen.FrozenGuardianEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FrozenGuardianRenderer extends MobRenderer<FrozenGuardianEntity, FrozenTrollModel<FrozenGuardianEntity>> {

    public FrozenGuardianRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FrozenTrollModel(), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FrozenGuardianEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_troll.png");
    }
}
