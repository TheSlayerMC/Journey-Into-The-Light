package net.jitl.client.render;

import net.jitl.client.render.entity.*;
import net.jitl.client.render.entity.base.BossCrystalRenderer;
import net.jitl.client.render.entity.base.Entity2DRenderer;
import net.jitl.client.render.entity.euca.*;
import net.jitl.client.render.entity.frozen.*;
import net.jitl.client.render.entity.nether.MiniGhastRenderer;
import net.jitl.client.render.entity.nether.WitherspineRenderer;
import net.jitl.client.render.entity.overworld.*;
import net.jitl.client.render.model.overworld.BoomModel;
import net.jitl.client.render.model.overworld.BrownHongoModel;
import net.jitl.client.render.model.overworld.HongoModel;
import net.jitl.client.render.model.base.HoodedVillagerModel;
import net.jitl.client.render.model.block.ObeliskModel;
import net.jitl.client.render.model.euca.*;
import net.jitl.client.render.model.frozen.*;
import net.jitl.client.render.model.overworld.RockiteSmasherModel;
import net.jitl.client.render.model.vehicle.JBoatModel;
import net.jitl.client.render.tile.JChestTER;
import net.jitl.client.render.vehicle.JBoatRenderer;
import net.jitl.common.entity.vehicle.JBoat;
import net.jitl.core.JITL;
import net.jitl.core.init.JEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JEntityRenderRegistry {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        //Misc
        event.registerEntityRenderer(JEntities.ESSENCIA_BOLT_TYPE, EssenciaBoltRenderer::new);
        event.registerEntityRenderer(JEntities.EFFECT_CLOUD_TYPE, ParticleProjectileRenderer::new);
        //Mobs

        event.registerEntityRenderer(JEntities.HONGO_TYPE, HongoRenderer::new);
        event.registerEntityRenderer(JEntities.BROWN_HONGO_TYPE, BrownHongoRenderer::new);
        event.registerEntityRenderer(JEntities.WITHERSHROOM_TYPE, WithershroomRenderer::new);
        event.registerEntityRenderer(JEntities.HONGLOW_TYPE, HonglowRenderer::new);


        event.registerEntityRenderer(JEntities.FROZEN_TROLL_TYPE, FrozenTrollRenderer::new);
        event.registerEntityRenderer(JEntities.SHATTERER_TYPE, ShattererRenderer::new);
        event.registerEntityRenderer(JEntities.SHIVERING_RAM_TYPE, ShiveringRamRenderer::new);
        event.registerEntityRenderer(JEntities.PHANTASM_TYPE, PhantasmRenderer::new);

        event.registerEntityRenderer(JEntities.GLUMP_TYPE, GlumpRenderer::new);
        event.registerEntityRenderer(JEntities.ILLAGER_MECH_TYPE, IllagerMechRenderer::new);
        event.registerEntityRenderer(JEntities.BOSS_CRYSTAL_TYPE, BossCrystalRenderer::new);
        event.registerEntityRenderer(JEntities.WITHERSPINE_TYPE, WitherspineRenderer::new);
        event.registerEntityRenderer(JEntities.FLORO_TYPE, FloroRenderer::new);
        event.registerEntityRenderer(JEntities.TOWER_GUARDIAN_TYPE, TowerGuardianRenderer::new);
        event.registerEntityRenderer(JEntities.SOUL_WATCHER_TYPE, SoulWatcherRenderer::new);
        event.registerEntityRenderer(JEntities.ROCKITE_SMASHER_TYPE, RockiteSmasherRenderer::new);

        event.registerEntityRenderer(JEntities.SHIMMERER_TYPE, ShimmererRenderer::new);
        event.registerEntityRenderer(JEntities.EUCA_CHARGER_TYPE, EucaChargerRenderer::new);
        event.registerEntityRenderer(JEntities.GOLD_BOT_TYPE, GoldBotRenderer::new);
        event.registerEntityRenderer(JEntities.GOLDER_TYPE, GolderRenderer::new);
        event.registerEntityRenderer(JEntities.DYNASTER_TYPE, DynasterRenderer::new);

        event.registerEntityRenderer(JEntities.MINI_GHAST_TYPE, MiniGhastRenderer::new);


        //NPC
        event.registerEntityRenderer(JEntities.MAGE_TYPE, MageRenderer::new);
        event.registerEntityRenderer(JEntities.ESKIMO_TYPE, EskimoRenderer::new);
        event.registerEntityRenderer(JEntities.FROZEN_GUARDIAN_TYPE, FrozenGuardianRenderer::new);

        //Projectiles
        event.registerEntityRenderer(JEntities.FLORO_MUD_TYPE, ThrownItemRenderer::new);
        event.registerEntityRenderer(JEntities.CONJURING_PROJECTILE_TYPE, manager -> new Entity2DRenderer<>(manager, JITL.rl("textures/entity/projectile/conjuring.png"))
                .fullbright(true)
                .projectile(true)
                .scale(0.5F));
        event.registerEntityRenderer(JEntities.ESSENCIA_PROJECTILE_TYPE, manager -> new Entity2DRenderer<>(manager, JITL.rl("textures/entity/projectile/essencia.png"))
                .fullbright(true)
                .projectile(true)
                .scale(0.5F));
        event.registerEntityRenderer(JEntities.KNIFE_TYPE, manager -> new KnifeRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.PIERCER_TYPE, manager -> new PiercerRenderer(manager, Minecraft.getInstance().getItemRenderer()));
        event.registerEntityRenderer(JEntities.CALCIA_MINE_TYPE, ParticleProjectileRenderer::new);
        event.registerEntityRenderer(JEntities.CALCIA_BURST_TYPE, ParticleProjectileRenderer::new);

        //PETS
        event.registerEntityRenderer(JEntities.MINI_BOOM_TYPE, MiniBoomRenderer::new);
        event.registerEntityRenderer(JEntities.CAPYBARA_TYPE, CapybaraRenderer::new);
        event.registerEntityRenderer(JEntities.EUCA_HOPPER_TYPE, EucaHopperRenderer::new);

        //VEHICLES
        event.registerEntityRenderer(JEntities.JBOAT_TYPE, JBoatRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //Blocks
        event.registerLayerDefinition(JModelLayers.OBELISK_BOTTOM_MODEL_LAYER, ObeliskModel::createBottomBodyLayer);
        event.registerLayerDefinition(JModelLayers.OBELISK_TOP_MODEL_LAYER, ObeliskModel::createTopBodyLayer);

        event.registerLayerDefinition(JModelLayers.JCHEST, JChestTER::createSingleBodyLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_RIGHT, JChestTER::createDoubleBodyRightLayer);
        event.registerLayerDefinition(JModelLayers.JDOUBLE_CHEST_LEFT, JChestTER::createDoubleBodyLeftLayer);

        //Mobs
        event.registerLayerDefinition(JModelLayers.HONGO_MODEL_LAYER, HongoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.BROWN_HONGO_MODEL_LAYER, BrownHongoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.WITHERSHROOM_MODEL_LAYER, HongoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.HONGLOW_MODEL_LAYER, HongoModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.ROCKITE_SMASHER_LAYER, RockiteSmasherModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.FROZEN_TROLL_MODEL_LAYER, FrozenTrollModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.FROZEN_TROLL_HELD_ITEM_LAYER, FrozenTrollModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.SHATTERER_MODEL_LAYER, ShattererModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.SHIVERING_RAM_LAYER, ShiveringRamModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.SHIVERING_RAM_WOOL_LAYER, ShiveringRamWoolModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.PHANTASM_MODEL_LAYER, PhantasmModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.SHIMMERER_MODEL_LAYER, ShimmererModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.EUCA_CHARGER_MODEL_LAYER, EucaChargerModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.GOLD_BOT_MODEL_LAYER, GoldBotModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.GOLDER_MODEL_LAYER, GolderModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.DYNASTER_MODEL_LAYER, DynasterModel::createBodyLayer);

        event.registerLayerDefinition(JModelLayers.MINI_GHAST_LAYER, GhastModel::createBodyLayer);

        //NPC
        event.registerLayerDefinition(JModelLayers.MAGE_MODEL_LAYER, HoodedVillagerModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.ESKIMO_MODEL_LAYER, HoodedVillagerModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.FROZEN_GUARDIAN_MODEL_LAYER, FrozenGuardianModel::createBodyLayer);

        //PETS
        event.registerLayerDefinition(JModelLayers.MINI_BOOM_LAYER, BoomModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.MINI_BOOM_CHARGED_LAYER, BoomModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.CAPYBARA_MODEL_LAYER, CapybaraModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.CAPYBARA_SADDLE_LAYER, CapybaraModel::createBodyLayer);
        event.registerLayerDefinition(JModelLayers.EUCA_HOPPER_MODEL_LAYER, EucaHopperModel::createBodyLayer);

        //VEHICLES
        for(JBoat.Type type : JBoat.Type.values()) {
            event.registerLayerDefinition(JModelLayers.createBoatModelName(type), JBoatModel::createBodyModel);
        }
    }
}
