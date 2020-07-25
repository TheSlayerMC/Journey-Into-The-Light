package net.journey.dimension.base;

import net.journey.dimension.boil.WorldProviderBoiling;
import net.journey.dimension.boil.biome.BiomeGenBoiling;
import net.journey.dimension.boil.biome.BiomeGenBoilingSands;
import net.journey.dimension.boil.biome.BiomeGenCharredFields;
import net.journey.dimension.boil.biome.BiomeGenScorchedWasteland;
import net.journey.dimension.cloudia.BiomeGenCloudia;
import net.journey.dimension.cloudia.WorldProviderCloudia;
import net.journey.dimension.corba.WorldProviderCorba;
import net.journey.dimension.corba.biomes.BiomeGenCorba;
import net.journey.dimension.corba.biomes.BiomeGenCorbaHills;
import net.journey.dimension.corba.biomes.BiomeGenCorbaPlains;
import net.journey.dimension.corba.biomes.BiomeGenCorbaSwamp;
import net.journey.dimension.depths.BiomeGenDepths;
import net.journey.dimension.depths.WorldProviderDepths;
import net.journey.dimension.euca.WorldProviderEuca;
import net.journey.dimension.euca.biomes.EucaGoldBiome;
import net.journey.dimension.euca.biomes.EucaGolditeGrainsBiome;
import net.journey.dimension.euca.biomes.EucaSilverBiome;
import net.journey.dimension.frozen.BiomeGenFrozenLands;
import net.journey.dimension.frozen.WorldProviderFrozenLands;
import net.journey.dimension.senterian.BiomeGenSenterian;
import net.journey.dimension.senterian.WorldProviderSenterian;
import net.journey.dimension.terrania.WorldProviderTerrania;
import net.journey.dimension.terrania.biome.BiomeGenEnchantedShroomForest;
import net.journey.dimension.terrania.biome.BiomeGenTerrania;
import net.journey.entity.mob.boiling.EntityBurningLight;
import net.journey.entity.mob.boiling.EntityFrightener;
import net.journey.entity.mob.boiling.EntityMagmaBlaze;
import net.journey.entity.mob.cloudia.*;
import net.journey.entity.mob.corba.EntityLeafBlower;
import net.journey.entity.mob.corba.EntitySurfaceSeer;
import net.journey.entity.mob.corba.EntityTreeGolem;
import net.journey.entity.mob.corba.EntityWoodCreature;
import net.journey.entity.mob.depths.*;
import net.journey.entity.mob.euca.EntityDynaster;
import net.journey.entity.mob.euca.EntityEucaCharger;
import net.journey.entity.mob.euca.EntityGolditeMage;
import net.journey.entity.mob.euca.EntityShimmerer;
import net.journey.entity.mob.frozen.*;
import net.journey.entity.mob.nether.*;
import net.journey.entity.mob.overworld.*;
import net.journey.entity.mob.overworld.cold.EntityBlizzard;
import net.journey.entity.mob.overworld.jungle.EntityJungleGolem;
import net.journey.entity.mob.overworld.jungle.EntityJungleSpider;
import net.journey.entity.mob.overworld.jungle.EntityJungleTurtle;
import net.journey.entity.mob.overworld.underground.*;
import net.journey.entity.mob.overworld.underground.npc.EntityRockiteGolem;
import net.journey.entity.mob.senterian.mob.EntitySentryBlock;
import net.journey.entity.mob.senterian.mob.EntitySentryLord;
import net.journey.entity.mob.senterian.mob.EntitySentryStalker;
import net.journey.entity.mob.senterian.mob.EntitySentryWalker;
import net.journey.entity.mob.terrania.mob.*;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.journey.util.handler.LogHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class DimensionHelper {

    public static final float[] boilHeight = new float[]{0.125F, 0.1F};

    @Deprecated //it's better to keep them inside biomes
    public static final float[] CORBA_PLAINS_HEIGHT = new float[]{0.0F, 0.0F};

    public static final Biome EUCA_GOLD_BIOME = new EucaGoldBiome(new Biome.BiomeProperties("Euca Gold Forest").setRainDisabled().setRainfall(0.0F), JourneyBlocks.eucaGrass.getDefaultState(), JourneyBlocks.eucaDirt.getDefaultState());
    public static final Biome EUCA_SILVER_BIOME = new EucaSilverBiome(new Biome.BiomeProperties("Euca Silver Forest").setRainDisabled().setRainfall(0.0F).setBaseHeight(0.125F).setHeightVariation(0.05F), JourneyBlocks.eucaSilverGrass.getDefaultState(), JourneyBlocks.eucaDirt.getDefaultState());
    public static final Biome EUCA_GOLDITE_GRAINS_BIOME = new EucaGolditeGrainsBiome(new Biome.BiomeProperties("Euca Goldite Grains").setRainDisabled().setRainfall(0.0F).setBaseHeight(0.125F).setHeightVariation(0.05F), JourneyBlocks.eucaGolditeGrass.getDefaultState(), JourneyBlocks.eucaGolditeDirt.getDefaultState());

    public static final Biome BOILING_BIOME = new BiomeGenBoiling();
    public static final Biome SCORCHED_WASTELAND_BIOME = new BiomeGenScorchedWasteland();
    public static final Biome CHARRED_FIELDS_BIOME = new BiomeGenCharredFields();
    public static final Biome BOILING_SANDS_BIOME = new BiomeGenBoilingSands();

    public static final Biome CLOUDIA_BIOME = new BiomeGenCloudia();

    public static final Biome CORBA_BIOME = new BiomeGenCorba();
    public static final Biome CORBA_PLAINS_BIOME = new BiomeGenCorbaPlains();
    public static final Biome CORBA_HILLS_BIOME = new BiomeGenCorbaHills();
    public static final Biome CORBA_SWAMP_BIOME = new BiomeGenCorbaSwamp();

    public static final Biome DEPTHS_BIOME = new BiomeGenDepths();
    public static final Biome FROZEN_BIOME = new BiomeGenFrozenLands();
    public static final Biome SENTERIAN_BIOME = new BiomeGenSenterian();

    public static final Biome TERRANIA_BIOME = new BiomeGenTerrania();
    public static final Biome ENCHANTED_SHROOM_FOREST_BIOME = new BiomeGenEnchantedShroomForest();

    public static final Biome[] BOILING_BIOMES = {BOILING_BIOME, CHARRED_FIELDS_BIOME, SCORCHED_WASTELAND_BIOME, BOILING_SANDS_BIOME};
    public static final Biome[] EUCA_BIOMES = {EUCA_SILVER_BIOME, EUCA_GOLD_BIOME, EUCA_GOLDITE_GRAINS_BIOME};
    public static final Biome[] CORBA_BIOMES = {CORBA_BIOME, CORBA_PLAINS_BIOME, CORBA_HILLS_BIOME, CORBA_SWAMP_BIOME};
    public static final Biome[] TERRANIA_BIOMES = {TERRANIA_BIOME, ENCHANTED_SHROOM_FOREST_BIOME};

    public static final DimensionType EUCA_DIM = DimensionType.register("euca", "_euca", Config.euca, WorldProviderEuca.class, Config.keepLoadingEuca);
    public static final DimensionType BOILING_DIM = DimensionType.register("boiling_point", "_boilingPoint", Config.boil, WorldProviderBoiling.class, Config.keepLoadingBoil);
    public static final DimensionType CLOUDIA_DIM = DimensionType.register("cloudia", "_cloudia", Config.cloudia, WorldProviderCloudia.class, Config.keepLoadingCloudia);
    public static final DimensionType CORBA_DIM = DimensionType.register("corba", "_corba", Config.corba, WorldProviderCorba.class, Config.keepLoadingCorba);
    public static final DimensionType DEPTHS_DIM = DimensionType.register("depths", "_depths", Config.depths, WorldProviderDepths.class, Config.keepLoadingDepths);
    public static final DimensionType FROZEN_DIM = DimensionType.register("frozen_lands", "_frozen", Config.frozen, WorldProviderFrozenLands.class, Config.keepLoadingFrozen);
    public static final DimensionType TERRANIA_DIM = DimensionType.register("terrania", "_terrania", Config.terrania, WorldProviderTerrania.class, Config.keepLoadingTerrania);
    public static final DimensionType SENTERIAN_DIM = DimensionType.register("senterian", "_senterian", Config.senterian, WorldProviderSenterian.class, Config.keepLoadingSenterian);

    public static void init() {

        LogHelper.info("Registering Dimensions...");
		/*if(Config.overrideNether = true) {
			DimensionManager.unregisterDimension(-1);
			DimensionManager.registerDimension(-1, DimensionType.register("Nether", "NETHER", -1, WorldProviderNetherJourney.class, true));
		}
		if(Config.overrideEnd = true) {
			DimensionManager.unregisterDimension(1);
			DimensionManager.registerDimension(1, DimensionType.register("End", "END", 1, WorldProviderEndJourney.class, true));
		} */
        addDimBiome(EUCA_GOLD_BIOME, "Euca", Type.MAGICAL, Type.MOUNTAIN);
        addDimBiome(EUCA_SILVER_BIOME, "Euca Silver", Type.MAGICAL, Type.MOUNTAIN);
        addDimBiome(EUCA_GOLDITE_GRAINS_BIOME, "Euca Goldite", Type.MAGICAL, Type.MOUNTAIN);
        addDimBiome(CHARRED_FIELDS_BIOME, "Charred Fields", Type.HOT);
        addDimBiome(SCORCHED_WASTELAND_BIOME, "Scorched Wasteland", Type.HOT);
        addDimBiome(BOILING_SANDS_BIOME, "Boiling Sands", Type.HOT);
        addDimBiome(BOILING_BIOME, "Boiling Point", Type.HOT);
        addDimBiome(CLOUDIA_BIOME, "Cloudia", Type.MAGICAL);
        addDimBiome(CORBA_BIOME, "Corba", Type.DRY, Type.PLAINS, Type.DEAD);
        addDimBiome(CORBA_PLAINS_BIOME, "Corba Plains", Type.DRY, Type.PLAINS, Type.DEAD);
        addDimBiome(CORBA_HILLS_BIOME, "Corba Hills", Type.DRY, Type.PLAINS, Type.DEAD);
        addDimBiome(CORBA_SWAMP_BIOME, "Corba Swamp", Type.DRY, Type.PLAINS, Type.DEAD);
        addDimBiome(DEPTHS_BIOME, "Depths", Type.MAGICAL, Type.SPOOKY);
        addDimBiome(FROZEN_BIOME, "Frozen Lands", Type.COLD);
        addDimBiome(TERRANIA_BIOME, "Terrania", Type.MAGICAL, Type.SPOOKY);
        addDimBiome(ENCHANTED_SHROOM_FOREST_BIOME, "EnchantedShroomForest", Type.MAGICAL, Type.SPOOKY);
        addDimBiome(SENTERIAN_BIOME, "Senterian Labyrinth", Type.MAGICAL, Type.SPOOKY);
        addDimension(Config.euca, EUCA_DIM);
        addDimension(Config.boil, BOILING_DIM);
        addDimension(Config.cloudia, CLOUDIA_DIM);
        addDimension(Config.corba, CORBA_DIM);
        addDimension(Config.depths, DEPTHS_DIM);
        addDimension(Config.frozen, FROZEN_DIM);
        addDimension(Config.terrania, TERRANIA_DIM);
        addDimension(Config.senterian, SENTERIAN_DIM);
    }

    private static void addDimension(int id, DimensionType type) {
        LogHelper.info("Registering dimension ID: " + id + ", With Name: " + type.getName());
        DimensionManager.registerDimension(id, type);
    }

    private static Biome addDimBiome(Biome biome, String name, Type... t) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, t);
        LogHelper.info("Biome Registered: " + name);
        return biome;
    }

    private static Biome addOverworldBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        LogHelper.info("Biome Registered");
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);
        LogHelper.info("Biome Added");
        return biome;
    }

    public static void addSpawns() {
        addEucaSpawns();
        addFrostFrozenSpawns();
        addColdFrozenSpawns();
        addBoilSpawns();
        addDepthsSpawns();
        addVanillaSpawns();
        addCaveSpawns();
        addCorbaSpawns();
        addCloudiaSpawns();
        addTerraniaSpawns();
        addSenterainSpawns();
        addNetherSpawns();
    }

    private static void addSenterainSpawns() {
        int amount = 50;
        EntityRegistry.addSpawn(EntitySentryBlock.class, amount, 10, 20, EnumCreatureType.MONSTER, SENTERIAN_BIOME);
        EntityRegistry.addSpawn(EntitySentryLord.class, amount, 10, 20, EnumCreatureType.MONSTER, SENTERIAN_BIOME);
        EntityRegistry.addSpawn(EntitySentryStalker.class, amount, 10, 20, EnumCreatureType.MONSTER, SENTERIAN_BIOME);
        EntityRegistry.addSpawn(EntitySentryWalker.class, amount, 10, 20, EnumCreatureType.MONSTER, SENTERIAN_BIOME);

    }

    private static void addCloudiaSpawns() {
        int amount = 50;
        EntityRegistry.addSpawn(EntityStarlightTransporter.class, amount, 10, 20, EnumCreatureType.MONSTER, CLOUDIA_BIOME);
        EntityRegistry.addSpawn(EntityStarlightGolem.class, amount, 10, 20, EnumCreatureType.MONSTER, CLOUDIA_BIOME);
        EntityRegistry.addSpawn(EntityStarlightWalker.class, amount, 10, 20, EnumCreatureType.MONSTER, CLOUDIA_BIOME);
        EntityRegistry.addSpawn(EntitySkyEel.class, amount, 10, 20, EnumCreatureType.MONSTER, CLOUDIA_BIOME);
        EntityRegistry.addSpawn(EntityCloudFlower.class, amount, 10, 20, EnumCreatureType.MONSTER, CLOUDIA_BIOME);
    }

    private static void addCorbaSpawns() {
        int amount = 20;
        EntityRegistry.addSpawn(EntityTreeGolem.class, amount, 1, 1, EnumCreatureType.MONSTER, CORBA_BIOMES);
        EntityRegistry.addSpawn(EntityLeafBlower.class, amount, 1, 1, EnumCreatureType.MONSTER, CORBA_BIOMES);
        EntityRegistry.addSpawn(EntitySurfaceSeer.class, amount, 1, 1, EnumCreatureType.MONSTER, CORBA_BIOMES);
        EntityRegistry.addSpawn(EntityWoodCreature.class, amount, 1, 1, EnumCreatureType.MONSTER, CORBA_BIOMES);
    }

    private static void addColdFrozenSpawns() {
        int amount = 25;
        EntityRegistry.addSpawn(EntityIceman.class, amount, 1, amount, EnumCreatureType.CREATURE, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityFrozenTroll.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityPermafraust.class, amount, 10, 20, EnumCreatureType.MONSTER, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityShiveringBushwalker.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityIceMage.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);

    }

    private static void addFrostFrozenSpawns() {
        int amount = 30;
        EntityRegistry.addSpawn(EntityIceman.class, amount, 1, amount, EnumCreatureType.CREATURE, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityPermafraust.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityShatterer.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityCrystalCluster.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);
        EntityRegistry.addSpawn(EntityIceMage.class, amount, 10, amount, EnumCreatureType.MONSTER, FROZEN_BIOME);
    }

    private static void addTerraniaSpawns() {
        int amount = 15;
        EntityRegistry.addSpawn(EntityTerraScatterer.class, 5, 1, 1, EnumCreatureType.MONSTER, TERRANIA_BIOMES);
        EntityRegistry.addSpawn(EntityTerralight.class, 5, 1, 1, EnumCreatureType.MONSTER, TERRANIA_BIOMES);
        EntityRegistry.addSpawn(EntityTerragrow.class, 5, 1, 1, EnumCreatureType.MONSTER, TERRANIA_BIOMES);
        EntityRegistry.addSpawn(EntityTerrashroom.class, 5, 1, 1, EnumCreatureType.MONSTER, TERRANIA_BIOMES);
        EntityRegistry.addSpawn(EntityTerraslug.class, 5, 1, 1, EnumCreatureType.MONSTER, TERRANIA_BIOMES);
    }

    private static void addEucaSpawns() {
        int amount = 10;
        EntityRegistry.addSpawn(EntityEucaCharger.class, amount, 1, 1, EnumCreatureType.MONSTER, EUCA_BIOMES);
        EntityRegistry.addSpawn(EntityDynaster.class, amount, 1, 1, EnumCreatureType.MONSTER, EUCA_BIOMES);
        EntityRegistry.addSpawn(EntityShimmerer.class, amount, 1, 1, EnumCreatureType.MONSTER, EUCA_BIOMES);
        EntityRegistry.addSpawn(EntityGolditeMage.class, amount, 1, 1, EnumCreatureType.MONSTER, EUCA_BIOMES);
    }

    private static void addBoilSpawns() {
        int amount = 15;
        EntityRegistry.addSpawn(EntityFrightener.class, amount, 1, 1, EnumCreatureType.MONSTER, BOILING_BIOMES);
        EntityRegistry.addSpawn(EntityBurningLight.class, amount, 1, 1, EnumCreatureType.MONSTER, BOILING_BIOMES);
        EntityRegistry.addSpawn(EntityMagmaBlaze.class, amount, 1, 1, EnumCreatureType.MONSTER, BOILING_BIOMES);
    }

    private static void addDepthsSpawns() {
        int amount = 20;
        EntityRegistry.addSpawn(EntityDarknessCrawler.class, amount, 10, 1, EnumCreatureType.MONSTER, DEPTHS_BIOME);
        EntityRegistry.addSpawn(EntityDepthsBeast.class, amount, 10, 1, EnumCreatureType.MONSTER, DEPTHS_BIOME);
        EntityRegistry.addSpawn(EntitySpikedBeast.class, amount, 10, 1, EnumCreatureType.MONSTER, DEPTHS_BIOME);
        EntityRegistry.addSpawn(EntityDepthsHunter.class, amount, 10, 1, EnumCreatureType.MONSTER, DEPTHS_BIOME);
        EntityRegistry.addSpawn(EntityDarkener.class, amount, 10, 1, EnumCreatureType.MONSTER, DEPTHS_BIOME);
        EntityRegistry.addSpawn(EntityLightener.class, 4, 10, 1, EnumCreatureType.MONSTER, DEPTHS_BIOME);
    }

    private static void addCaveSpawns() {
        int amount = 75;
        int amount2 = 10;
        for (Biome b : Biome.REGISTRY) {
            Biome biome = b;
            if (biome != null && biome != Biomes.HELL) {
                EntityRegistry.addSpawn(EntityCaveMage.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityCaveling.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityCavurn.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityStonewalker.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityHonglow.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityBlueHonglow.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityGreenHonglow.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
                EntityRegistry.addSpawn(EntityRockiteGolem.class, amount2, 3, 4, EnumCreatureType.MONSTER, biome);
            }
        }
    }

    private static void addNetherSpawns() {
        EntityRegistry.addSpawn(EntityLavasnake.class, 60, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityWitherspine.class, 50, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityReaper.class, 50, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityHellCow.class, 100, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityMiniGhast.class, 5, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityInfernoBlaze.class, 10, 1, 2, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityHellTurtle.class, 100, 1, 2, EnumCreatureType.MONSTER, Biomes.HELL);
    }

    private static void addVanillaSpawns() {
        int amount = 4;
        for (Biome b : Biome.REGISTRY) {
            Biome biome = b;
            if (b != Biomes.HELL && b != Biomes.SKY && b != EUCA_GOLD_BIOME && b != BOILING_BIOME && b != CLOUDIA_BIOME && b != CORBA_BIOME && b != DEPTHS_BIOME && b != CORBA_PLAINS_BIOME && b != FROZEN_BIOME && b != TERRANIA_BIOME && b != SENTERIAN_BIOME) {
                if (BiomeDictionary.hasType(b, BiomeDictionary.Type.SNOWY) || BiomeDictionary.hasType(b, BiomeDictionary.Type.COLD)) {
                    EntityRegistry.addSpawn(EntityBlizzard.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityIceMage.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                }

                if (BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.LUSH) || BiomeDictionary.hasType(b, BiomeDictionary.Type.PLAINS)) {
                    EntityRegistry.addSpawn(EntityTurducken.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityFloro.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityBigHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityMediumHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                }

                if (BiomeDictionary.hasType(b, BiomeDictionary.Type.MUSHROOM)) {
                    EntityRegistry.addSpawn(EntityBigHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityMediumHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                }

                if (BiomeDictionary.hasType(b, BiomeDictionary.Type.SANDY)) {
                    EntityRegistry.addSpawn(EntitySandCrawler.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntitySpyclops.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityFireMage.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityBoom.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                }

                if (BiomeDictionary.hasType(b, BiomeDictionary.Type.JUNGLE)) {
                    EntityRegistry.addSpawn(EntityJungleGolem.class, 50, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityJungleTurtle.class, 100, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityJungleSpider.class, 100, 1, 1, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityFloro.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
                }
                if (BiomeDictionary.hasType(b, BiomeDictionary.Type.SWAMP)) {
                    EntityRegistry.addSpawn(EntitySwampFly.class, 15, 3, 4, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }

                for (Biome b1 : Biome.REGISTRY) {
                    if (b1 != null) {
                        EntityRegistry.addSpawn(EntitySpectre.class, 10, 1, 1, EnumCreatureType.MONSTER, b1);
                        EntityRegistry.addSpawn(EntityWraith.class, 10, 1, 1, EnumCreatureType.MONSTER, b1);
                    }
                }
            }
        }
    }
}