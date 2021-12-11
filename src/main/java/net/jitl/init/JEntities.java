package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.frozen.EskimoEntity;
import net.jitl.common.entity.nether.SoulWatcherEntity;
import net.jitl.common.entity.nether.WitherspineEntity;
import net.jitl.common.entity.overworld.*;
import net.jitl.common.entity.pet.MiniBoomEntity;
import net.jitl.common.entity.projectile.*;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import ru.timeconqueror.timecore.api.registry.EntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static net.minecraft.entity.EntityType.Builder;

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
            Builder.of(FloroEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1F, 2F))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FloroEntity::canSpawn)
            .attributes(() -> FloroEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<EskimoEntity> ESKIMO_TYPE = REGISTER.registerMob("eskimo",
            Builder.of(EskimoEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2F))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())
            .spawnEgg(FROZEN_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<MageEntity> MAGE_TYPE = REGISTER.registerMob("mage",
                    Builder.of(MageEntity::new, EntityClassification.MONSTER)
                            .setTrackingRange(80)
                            .setShouldReceiveVelocityUpdates(true)
                            .sized(0.9F, 2.2F))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())
            .spawnEgg(TRADER_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<HongoEntity> HONGO_TYPE = REGISTER.registerMob("hongo",
            Builder.of(HongoEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2F))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<MiniBoomEntity> MINI_BOOM_TYPE = REGISTER.registerMob("miniboom",
            Builder.of(MiniBoomEntity::new, EntityClassification.CREATURE)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2F))
            .attributes(() -> MiniBoomEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<WithershroomEntity> WITHERSHROOM_TYPE = REGISTER.registerMob("withershroom",
            Builder.of(WithershroomEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2F))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WithershroomEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<HonglowEntity> HONGLOW_TYPE = REGISTER.registerMob("honglow",
            Builder.of(HonglowEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2F))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HonglowEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())//FIXME is Hongo attributes for Honglow not an error?
            .spawnEgg(OVERWORLD_COLOR, NEUTRAL_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<WitherspineEntity> WITHERSPINE_TYPE = REGISTER.registerMob("witherspine",
            Builder.of(WitherspineEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 3.65F))//TODO add spawn settings
            .attributes(() -> WitherspineEntity.createAttributes().build())
            .spawnEgg(NETHER_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<TowerGuardianEntity> TOWER_GUARDIAN_TYPE = REGISTER.registerMob("tower_guardian",
            Builder.of(TowerGuardianEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(2F, 4.2F))//TODO add spawn settings
            .attributes(() -> TowerGuardianEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, BOSS_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<GlumpEntity> GLUMP_TYPE = REGISTER.registerMob("glump",
            Builder.of(GlumpEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(2F, 0.8F))//TODO add spawn settings
            .attributes(() -> GlumpEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, PASSIVE_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<IllagerMechEntity> ILLAGER_MECH_TYPE = REGISTER.registerMob("illager_mech",
            Builder.of(IllagerMechEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.25F, 1.95F))
            .attributes(() -> IllagerMechEntity.createAttributes().build())
            .spawnEgg(OVERWORLD_COLOR, HOSTILE_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<SoulWatcherEntity> SOUL_WATCHER_TYPE = REGISTER.registerMob("soul_watcher",
            Builder.of(SoulWatcherEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(2.5F, 2.5F))
            .attributes(() -> SoulWatcherEntity.createAttributes().build())
            .spawnEgg(NETHER_COLOR, BOSS_COLOR, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<EssenciaBoltEntity> ESSENCIA_BOLT_TYPE = REGISTER.register("essencia_bolt",
            Builder.of(EssenciaBoltEntity::new, EntityClassification.MISC)
                    .noSave()
                    .sized(0.0F, 0.0F)
                    .clientTrackingRange(16)
                    .updateInterval(Integer.MAX_VALUE))
            .retrieve();

    public static final EntityType<FloroMudEntity> FLORO_MUD_TYPE = REGISTER.register("floro_mud",
            Builder.<FloroMudEntity>of(FloroMudEntity::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<ConjuringProjectileEntity> CONJURING_PROJECTILE_TYPE = REGISTER.register("conjuring_projectile",
            Builder.<ConjuringProjectileEntity>of(ConjuringProjectileEntity::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<EssenciaProjectileEntity> ESSENCIA_PROJECTILE_TYPE = REGISTER.register("essencia_projectile",
            Builder.<EssenciaProjectileEntity>of(EssenciaProjectileEntity::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<KnifeEntity> KNIFE_TYPE = REGISTER.register("knife",
            Builder.<KnifeEntity>of(KnifeEntity::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<PiercerEntity> PIERCER_TYPE = REGISTER.register("piercer",
            Builder.<PiercerEntity>of(PiercerEntity::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<JEffectCloudEntity> EFFECT_CLOUD_TYPE = REGISTER.register("effect_cloud",
            Builder.<JEffectCloudEntity>of(JEffectCloudEntity::new, EntityClassification.MISC)
                    .fireImmune()
                    .clientTrackingRange(10)
                    .sized(0.0F, 0.0F))
            .retrieve();

    public static final EntityType<CalciaMineEntity> CALCIA_MINE_TYPE = REGISTER.register("calcia_mine",
            Builder.<CalciaMineEntity>of(CalciaMineEntity::new, EntityClassification.MISC)
                    .fireImmune()
                    .clientTrackingRange(10)
                    .sized(0.0F, 0.0F))
            .retrieve();

    public static final EntityType<CalciaBurstEntity> CALCIA_BURST_TYPE = REGISTER.register("calcia_burst",
            Builder.<CalciaBurstEntity>of(CalciaBurstEntity::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.4F, 0.4F))
            .retrieve();
}


