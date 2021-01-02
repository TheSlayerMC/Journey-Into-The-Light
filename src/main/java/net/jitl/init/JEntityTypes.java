package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.FloroEntity;
import net.jitl.common.entity.projectile.FloroMudProjectileEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JEntityTypes {

	public static final EntityType<FloroEntity> FLORO_TYPE = EntityType.Builder.of(FloroEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(80)
			.setShouldReceiveVelocityUpdates(true)
			.sized(1, 2)
			.build(JITL.MODID + ":floro");

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

	@SubscribeEvent
	public static void register(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(FLORO_TYPE.setRegistryName(JITL.MODID + ":floro"));
		event.getRegistry().register(ESSENCIA_BOLT_TYPE.setRegistryName(JITL.MODID + ":essencia_bolt"));
		event.getRegistry().register(FLORO_MUD_PROJECTILE_TYPE.setRegistryName(JITL.MODID + ":floro_mud_projectile"));
		GlobalEntityTypeAttributes.put(FLORO_TYPE, FloroEntity.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new SpawnEggItem(FLORO_TYPE, 0xFF00FF00, 0xFF000000, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName(JITL.MODID, "spawn_floro")
		);
	}
}
