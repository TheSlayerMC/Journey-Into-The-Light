package net.jitl.init.world;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.boil.DirerockStrongholdStructure;
import net.jitl.common.world.gen.structures.euca.AlloyMenderStructure;
import net.jitl.common.world.gen.structures.euca.EucaDungeonStructure;
import net.jitl.common.world.gen.structures.euca.goldite.windmill.GolditeWindmillStructure;
import net.jitl.common.world.gen.structures.frozen.EskimoCampStructure;
import net.jitl.common.world.gen.structures.frozen.guardianruins.GuardianRuinStructure;
import net.jitl.common.world.gen.structures.overworld.AncientRuinsStructure;
import net.jitl.common.world.gen.structures.overworld.BlacksmithStructure;
import net.jitl.common.world.gen.structures.overworld.IllagerBunkerStructure;
import net.jitl.common.world.gen.structures.overworld.MageHouseStructure;
import net.jitl.common.world.gen.structures.overworld.guardian.GuardianTowerStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import ru.timeconqueror.timecore.api.registry.StructureFeatureRegister;
import ru.timeconqueror.timecore.api.registry.StructureFeatureRegister.StructureHolder;
import ru.timeconqueror.timecore.api.registry.StructureFeatureRegister.TimeStructureSeparationSettings;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.storage.StructureTags.Tag;

import java.util.Objects;

public class JStructures {
    public static final Marker STRUCTURE_MARKER = MarkerManager.getMarker("JSTRUCTURES");

    @AutoRegistrable
    private static final StructureFeatureRegister REGISTER = new StructureFeatureRegister(JITL.MODID);

    /*public static final StructureHolder<NoneFeatureConfiguration, GuardianTowerStructure> GUARDIAN_TOWER_HOLDER =
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

    public static final StructureHolder<NoneFeatureConfiguration, MageHouseStructure> MAGE_HOUSE =
            REGISTER.register("mage_house", MageHouseStructure::new, TimeStructureSeparationSettings.create(10, 5), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .allowedInBiomes((biomeResourceKey, biome) -> Objects.equals(biome.getRegistryName(), new ResourceLocation("dark_forest")))
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

    public static final StructureHolder<NoneFeatureConfiguration, IllagerBunkerStructure> ILlAGER_BUNKER =
            REGISTER.register("illager_bunker", IllagerBunkerStructure::new, TimeStructureSeparationSettings.create(40, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == Level.OVERWORLD)
                    .allowedInBiomes((biomeResourceKey, biome) -> biome.getBiomeCategory() == Biome.BiomeCategory.MESA)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, EskimoCampStructure> ESKIMO_CAMP =
            REGISTER.register("eskimo_camp", EskimoCampStructure::new, TimeStructureSeparationSettings.create(20, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == JDimensions.FROZEN_WORLD)
                    .tagged(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoneFeatureConfiguration, GuardianRuinStructure> GUARDIAN_RUIN =
            REGISTER.register("guardian_ruin", GuardianRuinStructure::new, TimeStructureSeparationSettings.create(20, 10), NoneFeatureConfiguration.CODEC, NoneFeatureConfiguration.NONE)
                    .transformsSurroundingLand()
                    .allowedInDimensions(serverWorld -> serverWorld.dimension() == JDimensions.FROZEN_WORLD)
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
