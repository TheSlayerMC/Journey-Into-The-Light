package net.jitl.client.render;

import net.jitl.init.JEntityTypes;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class JEntityRenderRegistry {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(JEntityTypes.ESSENCIA_BOLT_TYPE, LightningBoltRenderer::new);
    }
}
