package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.nether.WitherspineEntity;
import net.jitl.common.entity.overworld.FloroEntity;
import net.jitl.common.entity.overworld.HonglowEntity;
import net.jitl.common.entity.overworld.HongoEntity;
import net.jitl.common.entity.projectile.ConjuringProjectileEntity;
import net.jitl.common.entity.projectile.EssenciaProjectileEntity;
import net.jitl.common.entity.projectile.FloroMudProjectileEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JEntities {

	public static final EntityType<FloroEntity> FLORO_TYPE = EntityType.Builder.of(FloroEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(1, 2)
			.build(JITL.MODID + ":floro");

	public static final EntityType<HongoEntity> HONGO_TYPE = EntityType.Builder.of(HongoEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(1.5F, 2)
			.build(JITL.MODID + ":hongo");

	public static final EntityType<HonglowEntity> HONGLOW_TYPE = EntityType.Builder.of(HonglowEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(1.5F, 2)
			.build(JITL.MODID + ":honglow");

	public static final EntityType<WitherspineEntity> WITHERSPINE_TYPE = EntityType.Builder.of(WitherspineEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(1.5F, 3)
			.build(JITL.MODID + ":witherspine");

	public static final EntityType<EssenciaBoltEntity> ESSENCIA_BOLT_TYPE = EntityType.Builder.of(EssenciaBoltEntity::new, EntityClassification.AMBIENT)
			.noSave()
			.sized(0.0F, 0.0F)
			.clientTrackingRange(16)
			.updateInterval(Integer.MAX_VALUE)
			.build(JITL.MODID + ":essencia_bolt");

	public static final EntityType<FloroMudProjectileEntity> FLORO_MUD_PROJECTILE_TYPE = EntityType.Builder.<FloroMudProjectileEntity>of(FloroMudProjectileEntity::new, EntityClassification.MISC)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(0.5F, 0.5F)
			.build(JITL.MODID + ":floro_mud_projectile");

	public static final EntityType<ConjuringProjectileEntity> CONJURING_PROJECTILE_TYPE = EntityType.Builder.<ConjuringProjectileEntity>of(ConjuringProjectileEntity::new, EntityClassification.MISC)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(0.5F, 0.5F)
			.build(JITL.MODID + ":conjuring_projectile");

	public static final EntityType<EssenciaProjectileEntity> ESSENCIA_PROJECTILE_TYPE = EntityType.Builder.<EssenciaProjectileEntity>of(EssenciaProjectileEntity::new, EntityClassification.MISC)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(0.5F, 0.5F)
			.build(JITL.MODID + ":essencia_projectile");

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(FLORO_TYPE.setRegistryName(JITL.MODID + ":floro"));
		event.getRegistry().register(HONGO_TYPE.setRegistryName(JITL.MODID + ":hongo"));
		event.getRegistry().register(HONGLOW_TYPE.setRegistryName(JITL.MODID + ":honglow"));
		event.getRegistry().register(WITHERSPINE_TYPE.setRegistryName(JITL.MODID + ":witherspine"));
		event.getRegistry().register(ESSENCIA_BOLT_TYPE.setRegistryName(JITL.MODID + ":essencia_bolt"));
		event.getRegistry().register(FLORO_MUD_PROJECTILE_TYPE.setRegistryName(JITL.MODID + ":floro_mud_projectile"));
		event.getRegistry().register(CONJURING_PROJECTILE_TYPE.setRegistryName(JITL.MODID + ":conjuring_projectile"));
		event.getRegistry().register(ESSENCIA_PROJECTILE_TYPE.setRegistryName(JITL.MODID + ":essencia_projectile"));

		GlobalEntityTypeAttributes.put(FLORO_TYPE, FloroEntity.createAttributes().build());
		GlobalEntityTypeAttributes.put(HONGO_TYPE, HongoEntity.createAttributes().build());
		GlobalEntityTypeAttributes.put(HONGLOW_TYPE, HongoEntity.createAttributes().build());
		GlobalEntityTypeAttributes.put(WITHERSPINE_TYPE, WitherspineEntity.createAttributes().build());
	}

	public static void registerSpawnPlacements() {
		EntitySpawnPlacementRegistry.register(JEntities.FLORO_TYPE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FloroEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(JEntities.HONGO_TYPE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(JEntities.HONGLOW_TYPE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HonglowEntity::canSpawn);
		EntitySpawnPlacementRegistry.register(JEntities.HONGLOW_TYPE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HongoEntity::canSpawn);
	}

	//TODO: create streamlined registry system that registers a spawn egg alongside each entity
	@SubscribeEvent
	public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new SpawnEggItem(FLORO_TYPE, 0xFF00FF00, 0xFF000000, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName(JITL.MODID, "spawn_floro"),
				new SpawnEggItem(HONGO_TYPE, 0xFF00FF00, 0xFF000000, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName(JITL.MODID, "spawn_hongo"),
				new SpawnEggItem(HONGLOW_TYPE, 0xFF00FF00, 0xFF000000, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName(JITL.MODID, "spawn_honglow"),
				new SpawnEggItem(WITHERSPINE_TYPE, 0xFF00FF00, 0xFF000000, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName(JITL.MODID, "spawn_witherspine")
		);
	}
}
