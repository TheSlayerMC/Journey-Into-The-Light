package net.jitl.common.entity;

import net.jitl.JITL;
import net.jitl.common.entity.frozen.*;
import net.jitl.common.entity.nether.*;
import net.jitl.common.entity.overworld.*;
import net.jitl.common.entity.pet.*;
import net.jitl.init.JEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JAttributeCreation {

    @SubscribeEvent
    public static void onAttributeCreation(EntityAttributeCreationEvent event) {
        //Mobs
        event.put(JEntities.FLORO_TYPE, FloroEntity.createAttributes().build());
        event.put(JEntities.HONGO_TYPE, HongoEntity.createAttributes().build());
        event.put(JEntities.WITHERSHROOM_TYPE, WithershroomEntity.createAttributes().build());
        event.put(JEntities.HONGLOW_TYPE, HonglowEntity.createAttributes().build());
        event.put(JEntities.WITHERSPINE_TYPE, WitherspineEntity.createAttributes().build());
        event.put(JEntities.TOWER_GUARDIAN_TYPE, TowerGuardianEntity.createAttributes().build());
        event.put(JEntities.SOUL_WATCHER_TYPE, SoulWatcherEntity.createAttributes().build());
        event.put(JEntities.GLUMP_TYPE, GlumpEntity.createAttributes().build());
        event.put(JEntities.ILLAGER_MECH_TYPE, IllagerMechEntity.createAttributes().build());
        event.put(JEntities.FROZEN_TROLL_TYPE, FrozenTrollEntity.createAttributes().build());
        event.put(JEntities.SHATTERER_TYPE, ShattererEntity.createAttributes().build());
        event.put(JEntities.SHIVERING_RAM_TYPE, ShiveringRamEntity.createAttributes().build());
        event.put(JEntities.PHANTASM_TYPE, PhantasmEntity.createAttributes().build());

        //NPC
        event.put(JEntities.MAGE_TYPE, MageEntity.createAttributes().build());
        event.put(JEntities.ESKIMO_TYPE, EskimoEntity.createAttributes().build());
        event.put(JEntities.FROZEN_GUARDIAN_TYPE, FrozenGuardianEntity.createAttributes().build());
        
        //Pets
        event.put(JEntities.MINI_BOOM_TYPE, MiniBoomEntity.createAttributes().build());
        event.put(JEntities.CAPYBARA_TYPE, CapybaraEntity.createAttributes().build());
    }
}
