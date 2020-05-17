package ru.timeconqueror.timecore.api.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;

public abstract class TimeEntityRenderer<T extends EntityLiving & IAnimationProvider> extends RenderLiving<T> {
    public TimeEntityRenderer(RenderManager rendererManager, TimeEntityModel entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        entity.getAnimationManager().processAnimations(((TimeEntityModel) getMainModel()));
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
