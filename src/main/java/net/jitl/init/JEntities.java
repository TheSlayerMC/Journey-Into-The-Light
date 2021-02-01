package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.nether.WitherspineEntity;
import net.jitl.common.entity.overworld.FloroEntity;
import net.jitl.common.entity.overworld.HonglowEntity;
import net.jitl.common.entity.overworld.HongoEntity;
import net.jitl.common.entity.overworld.TowerGuardianEntity;
import net.jitl.common.entity.projectile.*;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.jitl.common.entity.projectile.base.PiercerEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import ru.timeconqueror.timecore.api.registry.EntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static net.minecraft.entity.EntityType.Builder;

public class JEntities {
    @AutoRegistrable
    private static final EntityRegister REGISTER = new EntityRegister(JITL.MODID);

    public static final EntityType<FloroEntity> FLORO_TYPE = REGISTER.registerMob("floro",
            Builder.of(FloroEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1, 2))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FloroEntity::canSpawn)
            .attributes(() -> FloroEntity.createAttributes().build())
            .spawnEgg(0xFF00FF00, 0xFF000000, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<HongoEntity> HONGO_TYPE = REGISTER.registerMob("hongo",
            Builder.of(HongoEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())
            .spawnEgg(0xFF00FF00, 0xFF000000, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<HonglowEntity> HONGLOW_TYPE = REGISTER.registerMob("honglow",
            Builder.of(HonglowEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 2))
            .spawnSettings(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HonglowEntity::canSpawn)
            .attributes(() -> HongoEntity.createAttributes().build())//FIXME is Hongo attributes for Honglow not an error?
            .spawnEgg(0xFF00FF00, 0xFF000000, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<WitherspineEntity> WITHERSPINE_TYPE = REGISTER.registerMob("witherspine",
            Builder.of(WitherspineEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 3))//TODO add spawn settings
            .attributes(() -> WitherspineEntity.createAttributes().build())
            .spawnEgg(0xFF00FF00, 0xFF000000, JTabs.SPAWNERS)
            .retrieve();

    public static final EntityType<TowerGuardianEntity> TOWER_GUARDIAN_TYPE = REGISTER.registerMob("tower_guardian",
            Builder.of(TowerGuardianEntity::new, EntityClassification.MONSTER)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5F, 3))//TODO add spawn settings
            .attributes(() -> TowerGuardianEntity.createAttributes().build())
            .spawnEgg(0xFF00FF00, 0xFF000000, JTabs.SPAWNERS)
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

    public static final EntityType<EntityMoltenKnife> MOLTEN_KNIFE_TYPE = REGISTER.register("molten_knife",
            Builder.<EntityMoltenKnife>of(EntityMoltenKnife::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(0.5F, 0.5F))
            .retrieve();

    public static final EntityType<PiercerEntity> EUCA_PIERCER_TYPE = REGISTER.register("euca_piercer",
            Builder.<PiercerEntity>of(EucaPiercerEntity::new, EntityClassification.MISC)
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
}


