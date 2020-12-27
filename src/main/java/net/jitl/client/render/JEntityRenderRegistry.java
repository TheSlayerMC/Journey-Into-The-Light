package net.jitl.client.render;

import net.jitl.JITL;
import net.jitl.init.JEntityTypes;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class JEntityRenderRegistry {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(JEntityTypes.ESSENCIA_BOLT_TYPE, LightningBoltRenderer::new);
	}
}
