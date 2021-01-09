package net.jitl.client.render;

import net.jitl.JITL;
import net.jitl.client.render.entity.EssenciaBoltRenderer;
import net.jitl.client.render.entity.FloroRenderer;
import net.jitl.client.render.entity.base.Entity2DRenderer;
import net.jitl.client.render.entity.base.HongoRenderer;
import net.jitl.common.entity.FloroEntity;
import net.jitl.init.JEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import ru.timeconqueror.timecore.api.client.render.model.TimeModelLoader;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class JEntityRenderRegistry {

    public static TimeEntityModel<FloroEntity> floroModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/floro.json"));


    public static void registerEntityRenders() {
        //Misc
        RenderingRegistry.registerEntityRenderingHandler(JEntities.ESSENCIA_BOLT_TYPE, EssenciaBoltRenderer::new);

        //Mobs
        RenderingRegistry.registerEntityRenderingHandler(JEntities.FLORO_TYPE, FloroRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(JEntities.HONGO_TYPE, HongoRenderer::new);

        //Projectiles
        RenderingRegistry.registerEntityRenderingHandler(JEntities.FLORO_MUD_PROJECTILE_TYPE, manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(JEntities.CONJURING_PROJECTILE_TYPE, manager -> new Entity2DRenderer<>(manager, JITL.rl("textures/entity/projectile/conjuring.png"), 0.5F, true));
        RenderingRegistry.registerEntityRenderingHandler(JEntities.ESSENCIA_PROJECTILE_TYPE, manager -> new Entity2DRenderer<>(manager, JITL.rl("textures/entity/projectile/essencia.png"), 0.5F, true));
    }
}
