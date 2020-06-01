package net.journey.integration;

import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.JERPlugin;
import jeresources.api.drop.LootDrop;
import jeresources.util.LogHelper;
import net.journey.api.entity.IJERCompatible;
import net.journey.init.EntityRegistry;
import net.minecraft.entity.EntityLivingBase;
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
