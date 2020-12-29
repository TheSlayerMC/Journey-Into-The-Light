package net.jitl.client.render;

import net.jitl.client.render.entity.EssenciaBoltRenderer;
import net.jitl.init.JEntityTypes;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class JEntityRenderRegistry {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(JEntityTypes.ESSENCIA_BOLT_TYPE, EssenciaBoltRenderer::new);
    }
}
