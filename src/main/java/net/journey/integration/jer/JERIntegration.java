package net.journey.integration.jer;

import jeresources.api.*;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import jeresources.util.LogHelper;
import net.journey.api.entity.IJERCompatible;
import net.journey.init.EntityRegistry;
import net.journey.init.JourneyLootTables;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.Config;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.List;

public class JERIntegration {
	@JERPlugin
	public static IJERAPI jerAPI;

	@SuppressWarnings("unchecked")
	public static void onInit(FMLInitializationEvent event) {
		World fakeWorld = jerAPI.getWorld();

		initDungeonLoot();
		initOreGen();

		IMobRegistry mobRegistry = jerAPI.getMobRegistry();
		EntityRegistry.getMobEntries().forEach((entityEntry) -> {
			if (EntityLivingBase.class.isAssignableFrom(entityEntry.getEntityClass()) && IJERCompatible.class.isAssignableFrom(entityEntry.getEntityClass())) {

				EntityLivingBase mob = construct(fakeWorld, ((Class<? extends EntityLivingBase>) entityEntry.getEntityClass()));

				if (mob != null) {
					IJERCompatible compatibleMob = (IJERCompatible) mob;

					List<LootDrop> drops = compatibleMob.getJERDrops();
					if (!drops.isEmpty()) {
						mobRegistry.register(mob, compatibleMob.getJERDrops().toArray(new LootDrop[0]));
					} else if (compatibleMob.getJERLootLocation() != null) {
						mobRegistry.register(mob, compatibleMob.getJERLootLocation());
					}
				}
			}
		});
	}

	//TODO: finish JER dungeon loot
	private static void initDungeonLoot() {
		IDungeonRegistry dungeonRegistry = jerAPI.getDungeonRegistry();
		dungeonRegistry.registerChest("Rockite Dungeon", JourneyLootTables.ROCKITE_CHEST_LOOT);

		//TODO: maybe create custom registry for loot pouches and loot boxes?
		dungeonRegistry.registerChest("Basic Loot Pouch", JourneyLootTables.LOOT_BASIC);
		dungeonRegistry.registerChest("Gold Loot Pouch", JourneyLootTables.LOOT_GOLD);
		dungeonRegistry.registerChest("Diamond Loot Pouch", JourneyLootTables.LOOT_DIAMOND);
	}

	//TODO: finish JER ore gen
	private static void initOreGen() {
		IWorldGenRegistry worldGenRegistry = jerAPI.getWorldGenRegistry();
		worldGenRegistry.register(new ItemStack(JourneyBlocks.sapphireOre), new DistributionSquare(0, Config.sapphireOreGenMaxY, Config.sapphireOreTrys), new LootDrop(new ItemStack(JourneyItems.sapphire)));
	}

	@Nullable
	private static <T extends EntityLivingBase> T construct(World world, Class<T> entityClass) {
		Constructor<T> constructor = getConstructor(entityClass);
		if (constructor != null) {
			try {
				return constructor.newInstance(world);
			} catch (ReflectiveOperationException | RuntimeException e) {
				LogHelper.warn("Could not create entity " + entityClass, e);
				return null;
			}
		}
		return null;
	}

	@Nullable
	private static <T extends EntityLivingBase> Constructor<T> getConstructor(Class<T> entityClass) {
		try {
			return entityClass.getConstructor(World.class);
		} catch (NoSuchMethodException e) {
			LogHelper.warn("Could not find constructor for entity " + entityClass);
			return null;
		}
	}
}
