package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import javax.annotation.Nullable;

//FIXME biomes are broken. it's almost impossible to make an overworld biome with forge 1.18
public class JBiomes {

    @AutoRegistrable
    public static final SimpleForgeRegister<Biome> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.BIOMES, JITL.MODID);

    public static final RegistryObject<Biome> DEEP_FOREST = REGISTER.register("deep_forest", JBiomes::createDeepForest);

    private static Biome createDeepForest() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100, true);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);

        Music music = Musics.createGameMusic(JSounds.HAUNT_MUSKIE_2.get());
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.UNDERGROUND, 0.6F, 0.6F, spawnSettings, generationSettings, music);
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder generationSettings_) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings_);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings_);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings_);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings_);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings_);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings_);
    }

    protected static int calculateSkyColor(float temperature_) {
        float $$1 = temperature_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome biome(Biome.Precipitation precipitation_, Biome.BiomeCategory category_, float temperature_, float downfall_, MobSpawnSettings.Builder spawnSettings_, BiomeGenerationSettings.Builder generationSettings_, @Nullable Music music_) {
        return biome(precipitation_, category_, temperature_, downfall_, 4159204, 329011, spawnSettings_, generationSettings_, music_);
    }

    private static Biome biome(Biome.Precipitation precipitation_, Biome.BiomeCategory category_, float temperature_, float downfall_, int waterColor_, int waterFogColor_, MobSpawnSettings.Builder spawnSettings_, BiomeGenerationSettings.Builder generationSettings_, @Nullable Music music_) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation_).biomeCategory(category_).temperature(temperature_).downfall(downfall_).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor_).waterFogColor(waterFogColor_).fogColor(12638463).skyColor(calculateSkyColor(temperature_)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music_).build()).mobSpawnSettings(spawnSettings_.build()).generationSettings(generationSettings_.build()).build();
    }
}
