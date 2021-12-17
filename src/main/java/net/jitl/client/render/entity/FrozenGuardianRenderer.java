package net.jitl.client.render.entity;


import net.jitl.JITL;
import net.jitl.client.render.model.frozen.FrozenGuardianModel;
import net.jitl.common.entity.frozen.FrozenGuardianEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FrozenGuardianRenderer extends MobRenderer<FrozenGuardianEntity, FrozenGuardianModel<FrozenGuardianEntity>> {

    public FrozenGuardianRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FrozenGuardianModel(), 0.5F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FrozenGuardianEntity entity) {
        boolean isActivated = entity.isActivated();
        if (isActivated) {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_guardian_awake.png");
        } else {
            return new ResourceLocation(JITL.MODID, "textures/entity/frozen/frozen_guardian_asleep.png");
        }
    }
}
