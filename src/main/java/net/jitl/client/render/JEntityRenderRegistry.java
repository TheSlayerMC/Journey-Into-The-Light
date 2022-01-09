package net.jitl.client.render;

import net.jitl.JITL;
import net.jitl.client.render.entity.*;
import net.jitl.client.render.entity.base.Entity2DRenderer;
import net.jitl.client.render.model.BoomModel;
import net.jitl.client.render.model.HongoModel;
import net.jitl.client.render.model.MageModel;
import net.jitl.client.render.model.block.ObeliskModel;
import net.jitl.client.render.model.frozen.*;
import net.jitl.common.entity.base.BossCrystalEntity;
import net.jitl.common.entity.nether.SoulWatcherEntity;
import net.jitl.common.entity.nether.WitherspineEntity;
import net.jitl.common.entity.overworld.FloroEntity;
import net.jitl.common.entity.overworld.GlumpEntity;
import net.jitl.common.entity.overworld.IllagerMechEntity;
import net.jitl.common.entity.overworld.TowerGuardianEntity;
import net.jitl.init.JEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.api.client.render.model.TimeModelLoader;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class JEntityRenderRegistry {

    public static TimeEntityModel<FloroEntity> floroModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/floro.json"));
    public static TimeEntityModel<WitherspineEntity> witherspineModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/witherspine.json"));
    public static TimeEntityModel<TowerGuardianEntity> towerGuardianModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/tower_guardian.json"));
    public static TimeEntityModel<GlumpEntity> glumpModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/glump.json"));
    public static TimeEntityModel<IllagerMechEntity> illagerMechModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/illager_mech.json"));
    public static TimeEntityModel<SoulWatcherEntity> soulWatcherModel = TimeModelLoader.loadJsonEntityModel(new ResourceLocation(JITL.MODID, "models/entity/soul_watcher.json"));
    public static TimeEntityModel<BossCrystalEntity> bossCrystalModel = TimeModelLoader.loadJsonEntityModel(
            new ResourceLocation(JITL.MODID, "models/entity/boss_crystal.json"), JRenderTypes::transparentCutout);

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        //Misc
        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE, EssenciaBoltRenderer::new);
        event.registerEntityRenderer(JEntities.EFFECT_CLOUD_TYPE, ParticleProjectileRenderer::new);
        event.registerEntityRenderer(JEntities.BOSS_CRYSTAL_TYPE, BossCrystalRenderer::new);

        //Mobs
        event.registerEntityRenderer(JEntities.FLORO_TYPE, FloroRenderer::new);
        event.registerEntityRenderer(JEntities.HONGO_TYPE, HongoRenderer::new);
        event.registerEntityRenderer(JEntities.WITHERSHROOM_TYPE, WithershroomRenderer::new);
        event.registerEntityRenderer(JEntities.HONGLOW_TYPE, HonglowRenderer::new);
        event.registerEntityRenderer(JEntities.WITHERSPINE_TYPE, WitherspineRenderer::new);
        event.registerEntityRenderer(JEntities.TOWER_GUARDIAN_TYPE, TowerGuardianRenderer::new);
        event.registerEntityRenderer(JEntities.SOUL_WATCHER_TYPE, SoulWatcherRenderer::new);
        event.registerEntityRenderer(JEntities.GLUMP_TYPE, GlumpRenderer::new);
        event.registerEntityRenderer(JEntities.ILLAGER_MECH_TYPE, IllagerMechRenderer::new);
        event.registerEntityRenderer(JEntities.FROZEN_TROLL_TYPE, FrozenTrollRenderer::new);
        event.registerEntityRenderer(JEntities.SHATTERER_TYPE, ShattererRenderer::new);
        event.registerEntityRenderer(JEntities.SHIVERING_RAM_TYPE, ShiveringRamRenderer::new);
        event.registerEntityRenderer(JEntities.PHANTASM_TYPE, PhantasmRenderer::new);

        //NPC
        event.registerEntityRenderer(JEntities.MAGE_TYPE, MageRenderer::new);
        event.registerEntityRenderer(JEntities.ESKIMO_TYPE, EskimoRenderer::new);
        event.registerEntityRenderer(JEntities.FROZEN_GUARDIAN_TYPE, FrozenGuardianRenderer::new);

        //Projectiles
        event.registerEntityRenderer(JEntities.FLORO_MUD_TYPE, ThrownItemRenderer::new);
        event.registerEntityRenderer(JEntities.CONJURING_PROJECTILE_TYPE, manager -> new Entity2DRenderer<>(manager, JITL.rl("textures/entity/projectile/conjuring.png"), 0.5F, true));
        event.registerEntityRenderer(JEntities.ESSENCIA_PROJECTILE_TYPE, manager -> new Entity2DRenderer<>(manager, JITL.rl("textures/entity/projectile/essencia.png"), 0.5F, true));
        event.registerEntityRenderer(JEntities.KNIFE_TYPE, manager -> new KnifeRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.PIERCER_TYPE, manager -> new PiercerRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.CALCIA_MINE_TYPE, ParticleProjectileRenderer::new);
        event.registerEntityRenderer(JEntities.CALCIA_BURST_TYPE, ParticleProjectileRenderer::new);

        //PETS
        event.registerEntityRenderer(JEntities.MINI_BOOM_TYPE, MiniBoomRenderer::new);
        event.registerEntityRenderer(JEntities.CAPYBARA_TYPE, CapybaraRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //Blocks
        event.registerLayerDefinition(JModelLayers.OBELISK_MODEL_LAYER, ObeliskModel::createBodyLayer);

        //Mobs
        //event.registerLayerDefinition(JEntities.FLORO_TYPE, FloroRenderer::new);
        event.registerLayerDefinition(JModelLayers.HONGO_MODEL_LAYER, HongoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.WITHERSHROOM_MODEL_LAYER, HongoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.HONGLOW_MODEL_LAYER, HongoModel::createBodyLayer);
        //event.registerLayerDefinition(JEntities.WITHERSPINE_TYPE, WitherspineRenderer::new);
        //event.registerLayerDefinition(JEntities.TOWER_GUARDIAN_TYPE, TowerGuardianRenderer::new);
        //event.registerLayerDefinition(JEntities.SOUL_WATCHER_TYPE, SoulWatcherRenderer::new);
        //event.registerLayerDefinition(JEntities.GLUMP_TYPE, GlumpRenderer::new);
        //event.registerLayerDefinition(JEntities.ILLAGER_MECH_TYPE, IllagerMechRenderer::new);
        event.registerLayerDefinition(JModelLayers.FROZEN_TROLL_MODEL_LAYER, FrozenTrollModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.SHATTERER_MODEL_LAYER, ShattererModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.SHIVERING_RAM_LAYER, ShiveringRamModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.PHANTASM_MODEL_LAYER, PhantasmModel::createBodyLayer);

        //NPC
        event.registerLayerDefinition(JModelLayers.MAGE_MODEL_LAYER, MageModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.ESKIMO_MODEL_LAYER, EskimoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.FROZEN_GUARDIAN_MODEL_LAYER, FrozenGuardianModel::createBodyLayer);

        //PETS
        event.registerLayerDefinition(JModelLayers.MINI_BOOM_LAYER, BoomModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.CAPYBARA_MODEL_LAYER, CapybaraModel::createBodyLayer);
    }
}
