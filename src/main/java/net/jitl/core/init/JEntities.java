package net.jitl.core.init;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.base.BossCrystalEntity;
import net.jitl.common.entity.euca.*;
import net.jitl.common.entity.frozen.*;
import net.jitl.common.entity.nether.SoulWatcherEntity;
import net.jitl.common.entity.nether.WitherspineEntity;
import net.jitl.common.entity.overworld.*;
import net.jitl.common.entity.pet.MiniBoomEntity;
import net.jitl.common.entity.projectile.*;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.common.entity.vehicle.JBoat;
import net.jitl.core.JITL;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import ru.timeconqueror.timecore.api.registry.EntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JEntities {
    //dimension colors
    private static final int OVERWORLD_COLOR = 0x32f53f;
    private static final int NETHER_COLOR = 0x881a2b;
    private static final int END_COLOR = 0x000000/*0x931aa3*/;
    private static final int FROZEN_COLOR = 0x3ea4ff;
    private static final int BOILING_COLOR = 0xeb8026;
    private static final int EUCA_COLOR = 0xffff0b;
    private static final int DEPTHS_COLOR = 0x0705a7;
    private static final int CORBA_COLOR = 0x106903;
    private static final int TERRANIA_COLOR = 0x91046d;
    private static final int CLOUDIA_COLOR = 0xfa45cd;
    private static final int SENTERIAN_COLOR = 0x2e2d2c;

    //mob type colors
    private static final int PASSIVE_COLOR = 0x00ff00;
    private static final int NEUTRAL_COLOR = 0x555555;
    private static final int HOSTILE_COLOR = 0xff0000;
    private static final int TRADER_COLOR = 0x7d007d;
    private static final int BOSS_COLOR = 0xffff7d;

    @AutoRegistrable
    private static final EntityRegister REGISTER = new EntityRegister(JITL.MODID);
    public static final EntityType<FloroEntity> FLORO_TYPE = REGISTER.registerMob("floro",
                    Builder.of(FloroEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1F, 2F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FloroEntity::canSpawn)
            .spawnEgg(OVERWORLD_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .attributes(() -> FloroEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<EskimoEntity> ESKIMO_TYPE = REGISTER.registerMob("eskimo",
                    Builder.of(EskimoEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.6F, 1.95F))
            .spawnEgg(FROZEN_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> EskimoEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<MageEntity> MAGE_TYPE = REGISTER.registerMob("mage",
                    Builder.of(MageEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.6F, 1.95F))
            .spawnEgg(TRADER_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> MageEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<FrozenTrollEntity> FROZEN_TROLL_TYPE = REGISTER.registerMob("frozen_troll",
                    Builder.of(FrozenTrollEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.9F, 1.35F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FrozenTrollEntity::canSpawn)
            .spawnEgg(FROZEN_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .attributes(() -> FrozenTrollEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<PhantasmEntity> PHANTASM_TYPE = REGISTER.registerMob("phantasm",
                    Builder.of(PhantasmEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PhantasmEntity::canSpawn)
            .spawnEgg(FROZEN_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .attributes(() -> PhantasmEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<ShiveringRamEntity> SHIVERING_RAM_TYPE = REGISTER.registerMob("shivering_ram",
                    Builder.of(ShiveringRamEntity::new, MobCategory.CREATURE)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.2F, 1.25F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShiveringRamEntity::canSpawn)
            .spawnEgg(FROZEN_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> ShiveringRamEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<CapybaraEntity> CAPYBARA_TYPE = REGISTER.registerMob("capybara",
                    Builder.of(CapybaraEntity::new, MobCategory.CREATURE)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.85F, 1.65F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CapybaraEntity::canSpawn)
            .spawnEgg(FROZEN_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> CapybaraEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<FrozenGuardianEntity> FROZEN_GUARDIAN_TYPE = REGISTER.registerMob("frozen_guardian",
                    Builder.of(FrozenGuardianEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.9F, 2.2F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FrozenGuardianEntity::canSpawn)
            .spawnEgg(FROZEN_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> FrozenGuardianEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<ShattererEntity> SHATTERER_TYPE = REGISTER.registerMob("shatterer",
                    Builder.of(ShattererEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.9F, 2.2F))
            .spawnEgg(FROZEN_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> ShattererEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<HongoEntity> HONGO_TYPE = REGISTER.registerMob("hongo",
                    Builder.of(HongoEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.5F, 2F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn)
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> HongoEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<MiniBoomEntity> MINI_BOOM_TYPE = REGISTER.registerMob("miniboom",
                    Builder.of(MiniBoomEntity::new, MobCategory.CREATURE)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.5F, 2F))
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> MiniBoomEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<WithershroomEntity> WITHERSHROOM_TYPE = REGISTER.registerMob("withershroom",
                    Builder.of(WithershroomEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.5F, 2F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WithershroomEntity::canSpawn)
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> WithershroomEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<HonglowEntity> HONGLOW_TYPE = REGISTER.registerMob("honglow",
                    Builder.of(HonglowEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.5F, 2F))
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HonglowEntity::canSpawn)
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> HonglowEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<EucaHopperEntity> EUCA_HOPPER_TYPE = REGISTER.registerMob("euca_hopper",
                    Builder.of(EucaHopperEntity::new, MobCategory.CREATURE)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.7F, 0.7F))
            .spawnEgg(EUCA_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EucaHopperEntity::canSpawn)
            .attributes(() -> EucaHopperEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<EucaChargerEntity> EUCA_CHARGER_TYPE = REGISTER.registerMob("euca_charger",
                    Builder.of(EucaChargerEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.7F, 1.0F))
            .spawnEgg(EUCA_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EucaChargerEntity::canSpawn)
            .attributes(() -> EucaChargerEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<GoldBotEntity> GOLD_BOT_TYPE = REGISTER.registerMob("gold_bot",
                    Builder.of(GoldBotEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.7F, 1.0F))
            .spawnEgg(EUCA_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .attributes(() -> GoldBotEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<GolderEntity> GOLDER_TYPE = REGISTER.registerMob("golder",
                    Builder.of(GolderEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.7F, 1.0F))
            .spawnEgg(EUCA_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GolderEntity::canSpawn)
            .attributes(() -> GolderEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<DynasterEntity> DYNASTER_TYPE = REGISTER.registerMob("dynaster",
                    Builder.of(DynasterEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.7F, 1.0F))
            .spawnEgg(EUCA_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DynasterEntity::canSpawn)
            .attributes(() -> DynasterEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<ShimmererEntity> SHIMMERER_TYPE = REGISTER.registerMob("shimmerer",
                    Builder.of(ShimmererEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.0F, 1.0F))
            .spawnEgg(EUCA_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .spawnSettings(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShimmererEntity::canSpawn)
            .attributes(() -> ShimmererEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<WitherspineEntity> WITHERSPINE_TYPE = REGISTER.registerMob("witherspine",
                    Builder.of(WitherspineEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.5F, 3.65F))//TODO add spawn settings
            .spawnEgg(NETHER_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .attributes(() -> WitherspineEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<TowerGuardianEntity> TOWER_GUARDIAN_TYPE = REGISTER.registerMob("tower_guardian",
                    Builder.of(TowerGuardianEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(2F, 4.2F))//TODO add spawn settings
            .spawnEgg(OVERWORLD_COLOR, BOSS_COLOR, JTabs.SPAWNERS)
            .attributes(() -> TowerGuardianEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<GlumpEntity> GLUMP_TYPE = REGISTER.registerMob("glump",
                    Builder.of(GlumpEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(2F, 0.8F))//TODO add spawn settings
            .spawnEgg(OVERWORLD_COLOR, PASSIVE_COLOR, JTabs.SPAWNERS)
            .attributes(() -> GlumpEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<IllagerMechEntity> ILLAGER_MECH_TYPE = REGISTER.registerMob("illager_mech",
                    Builder.of(IllagerMechEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.25F, 1.95F))
            .spawnEgg(OVERWORLD_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .attributes(() -> IllagerMechEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<SoulWatcherEntity> SOUL_WATCHER_TYPE = REGISTER.registerMob("soul_watcher",
                    Builder.of(SoulWatcherEntity::new, MobCategory.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(2.5F, 2.5F))
            .spawnEgg(NETHER_COLOR, BOSS_COLOR, JTabs.SPAWNERS)
            .attributes(() -> SoulWatcherEntity.createAttributes().build())
            .retrieve();

    public static final EntityType<EssenciaBoltEntity> ESSENCIA_BOLT_TYPE = REGISTER.register("essencia_bolt",
                    Builder.of(EssenciaBoltEntity::new, MobCategory.MISC)
                            .noSave()
                            .sized(0.0F, 0.0F)
                            .clientTrackingRange(16)
                            .updateInterval(Integer.MAX_VALUE))
            .retrieve();

    public static final EntityType<FloroMudEntity> FLORO_MUD_TYPE = REGISTER.register("floro_mud",
                    Builder.<FloroMudEntity>of(FloroMudEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<ConjuringProjectileEntity> CONJURING_PROJECTILE_TYPE = REGISTER.register("conjuring_projectile",
                    Builder.<ConjuringProjectileEntity>of(ConjuringProjectileEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<EssenciaProjectileEntity> ESSENCIA_PROJECTILE_TYPE = REGISTER.register("essencia_projectile",
                    Builder.<EssenciaProjectileEntity>of(EssenciaProjectileEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<JBoat> JBOAT_TYPE = REGISTER.register("jboat",
                    Builder.<JBoat>of(JBoat::new, MobCategory.MISC)
                            .setTrackingRange(10)
                            .sized(1.375F, 0.5625F))
            .retrieve();

    public static final EntityType<KnifeEntity> KNIFE_TYPE = REGISTER.register("knife",
                    Builder.<KnifeEntity>of(KnifeEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<PiercerEntity> PIERCER_TYPE = REGISTER.register("piercer",
                    Builder.<PiercerEntity>of(PiercerEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<JEffectCloudEntity> EFFECT_CLOUD_TYPE = REGISTER.register("effect_cloud",
                    Builder.<JEffectCloudEntity>of(JEffectCloudEntity::new, MobCategory.MISC)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .sized(0.0F, 0.0F))
            .retrieve();

    public static final EntityType<CalciaMineEntity> CALCIA_MINE_TYPE = REGISTER.register("calcia_mine",
                    Builder.<CalciaMineEntity>of(CalciaMineEntity::new, MobCategory.MISC)
                            .fireImmune()
                            .clientTrackingRange(10)
                            .sized(0.0F, 0.0F))
            .retrieve();

    public static final EntityType<CalciaBurstEntity> CALCIA_BURST_TYPE = REGISTER.register("calcia_burst",
                    Builder.<CalciaBurstEntity>of(CalciaBurstEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.4F, 0.4F))
            .retrieve();

    public static final EntityType<BossCrystalEntity> BOSS_CRYSTAL_TYPE = REGISTER.register("boss_crystal",
                    Builder.<BossCrystalEntity>of(BossCrystalEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(1.0F, 3.0F))
            .retrieve();
}


