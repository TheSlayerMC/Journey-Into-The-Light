package net.jitl.core.init.world;

import net.jitl.common.world.gen.structures.frozen.EskimoCampStructure;
import net.jitl.common.world.gen.structures.frozen.guardianruins.GuardianRuinStructure;
import net.jitl.common.world.gen.structures.overworld.IllagerBunkerStructure;
import net.jitl.common.world.gen.structures.overworld.MageHouseStructure;
import net.jitl.core.JITL;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import ru.timeconqueror.timecore.api.registry.StructureFeatureRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.storage.StructureTags;

import java.util.Objects;

public class JStructures {

    public static final Marker STRUCTURE_MARKER = MarkerManager.getMarker("JSTRUCTURES");

    @AutoRegistrable
    private static final StructureFeatureRegister REGISTER = new StructureFeatureRegister(JITL.MODID);

    public static final StructureFeatureRegister.StructureHolder<JigsawConfiguration, IllagerBunkerStructure> ILlAGER_BUNKER =
            REGISTER.register("illager_bunker", IllagerBunkerStructure::new, StructureFeatureRegister.TimeStructureSeparationSettings.create(40, 10), JigsawConfiguration.CODEC, new JigsawConfiguration(() -> PlainVillagePools.START, 0))
                    .allowedInBiomes((biome, biomeKey) -> biomeKey.getBiomeCategory().equals(Biome.BiomeCategory.MESA))
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .tagged(StructureTags.Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureFeatureRegister.StructureHolder<JigsawConfiguration, EskimoCampStructure> ESKIMO_CAMP_STRUCTURE =
            REGISTER.register("eskimo_camp", EskimoCampStructure::new, StructureFeatureRegister.TimeStructureSeparationSettings.create(20, 10), JigsawConfiguration.CODEC, new JigsawConfiguration(() -> PlainVillagePools.START, 0))
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Dimensions.FROZEN_LANDS)
                    .allowedInBiomes(((biomeResourceKey, biome) -> Objects.equals(biomeResourceKey.location(), JITL.rl("frozen/dying_forest"))))
                    .tagged(StructureTags.Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();


    public static final StructureFeatureRegister.StructureHolder<NoneFeatureConfiguration, GuardianRuinStructure> GUARDIAN_RUIN =
            REGISTER.register("guardian_ruin", GuardianRuinStructure::new, StructureFeatureRegister.TimeStructureSeparationSettings.create(20, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Dimensions.FROZEN_LANDS)
                    .allowedInBiomes(((biomeResourceKey, biome) -> Objects.equals(biomeResourceKey.location(), JITL.rl("frozen/frozen_wastes"))))
                    .tagged(StructureTags.Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureFeatureRegister.StructureHolder<NoneFeatureConfiguration, MageHouseStructure> MAGE_HOUSE =
            REGISTER.register("mage_house", MageHouseStructure::new, StructureFeatureRegister.TimeStructureSeparationSettings.create(10, 5), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .allowedInBiomes((biomeResourceKey, biome) -> Objects.equals(biome.getRegistryName(), new ResourceLocation("dark_forest")))
                    .tagged(StructureTags.Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();


   /* public static final StructureHolder<NoneFeatureConfiguration, GuardianTowerStructure> GUARDIAN_TOWER_HOLDER =
            REGISTER.register("guardian_tower", GuardianTowerStructure::new, TimeStructureSeparationSettings.create(10, 5), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, BlacksmithStructure> BLACKSMITH =
            REGISTER.register("blacksmith", BlacksmithStructure::new, TimeStructureSeparationSettings.create(10, 5), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();



    public static final StructureHolder<NoneFeatureConfiguration, AlloyMenderStructure> ALLOY_MENDER =
            REGISTER.register("alloy_mender_hut", AlloyMenderStructure::new, TimeStructureSeparationSettings.create(10, 5), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == JDimensions.EUCA_WORLD)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, EucaDungeonStructure> EUCA_SHPHERE_DUNGEON =
            REGISTER.register("euca_sphere_dungeon", EucaDungeonStructure::new, TimeStructureSeparationSettings.create(10, 5), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == JDimensions.EUCA_WORLD)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, GolditeWindmillStructure> GOLDITE_WINDMILL =
            REGISTER.register("goldite_windmill", GolditeWindmillStructure::new, TimeStructureSeparationSettings.create(20, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == JDimensions.EUCA_WORLD)
                    .allowedInBiomes((biomeResourceKey, biome) -> Objects.equals(biome.getRegistryName(), JITL.rl("euca/euca_goldite_grains")))
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, AncientRuinsStructure> ANCIENT_RUINS =
            REGISTER.register("ancient_ruins", AncientRuinsStructure::new, TimeStructureSeparationSettings.create(20, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, DirerockStrongholdStructure> DIREROCK_STRONGHOLD =
            REGISTER.register("direrock_stronghold", DirerockStrongholdStructure::new, TimeStructureSeparationSettings.create(15, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == JDimensions.BOIL_WORLD)
                    .allowedInBiomes((biomeResourceKey, biome) -> Objects.equals(biome.getRegistryName(), JITL.rl("boil/scorched_wastelands")))
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();*/
}
