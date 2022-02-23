package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.jitl.core.init.JEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class LivingSpawnEventHandler {

    @SubscribeEvent
    public static void setupSpawns(BiomeLoadingEvent event) {
        MobSpawnSettingsBuilder spawnSettingsBuilder = event.getSpawns();
        spawnSettingsBuilder
                .addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings.SpawnerData(
                                JEntities.HONGLOW_TYPE,
                                55, 1, 2));

        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            spawnSettingsBuilder
                    .addSpawn(
                            MobCategory.MONSTER,
                            new MobSpawnSettings.SpawnerData(
                                    JEntities.FLORO_TYPE,
                                    60, 1, 2));
        }
        if (event.getCategory() == Biome.BiomeCategory.MUSHROOM || Objects.equals(event.getName(), new ResourceLocation("dark_forest"))) {
            spawnSettingsBuilder
                    .addSpawn(
                            MobCategory.MONSTER,
                            new MobSpawnSettings.SpawnerData(
                                    JEntities.HONGO_TYPE,
                                    70, 2, 4))
                    .addSpawn(
                            MobCategory.MONSTER,
                            new MobSpawnSettings.SpawnerData(
                                    JEntities.BROWN_HONGO_TYPE,
                                    60, 2, 4));
        }
    }
}
