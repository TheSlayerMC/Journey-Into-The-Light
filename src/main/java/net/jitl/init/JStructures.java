package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.euca.AlloyMenderStructure;
import net.jitl.common.world.gen.structures.euca.EucaDungeonStructure;
import net.jitl.common.world.gen.structures.overworld.BlacksmithStructure;
import net.jitl.common.world.gen.structures.overworld.MageHouseStructure;
import net.jitl.common.world.gen.structures.overworld.guardian.GuardianTowerStructure;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import ru.timeconqueror.timecore.api.registry.StructureRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static ru.timeconqueror.timecore.api.registry.StructureRegister.StructureHolder;
import static ru.timeconqueror.timecore.api.registry.StructureRegister.TimeStructureSeparationSettings;
import static ru.timeconqueror.timecore.api.storage.StructureTags.Tag;

public class JStructures {
    public static final Marker STRUCTURE_MARKER = MarkerManager.getMarker("JSTRUCTURES");

    @AutoRegistrable
    private static final StructureRegister REGISTER = new StructureRegister(JITL.MODID);

    public static final StructureHolder<NoFeatureConfig, GuardianTowerStructure> GUARDIAN_TOWER_HOLDER =
            REGISTER.register("guardian_tower", GuardianTowerStructure::new, TimeStructureSeparationSettings.create(10, 5), NoFeatureConfig.CODEC, NoFeatureConfig.NONE)
                    .transformsSurroundingLand()
                    .setDimensionPredicate(serverWorld -> serverWorld.dimension() == World.OVERWORLD)
                    .addToTag(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoFeatureConfig, BlacksmithStructure> BLACKSMITH =
            REGISTER.register("blacksmith", BlacksmithStructure::new, TimeStructureSeparationSettings.create(10, 5), NoFeatureConfig.CODEC, NoFeatureConfig.NONE)
                    .transformsSurroundingLand()
                    .setDimensionPredicate(serverWorld -> serverWorld.dimension() == World.OVERWORLD)
                    .addToTag(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoFeatureConfig, MageHouseStructure> MAGE_HOUSE =
            REGISTER.register("mage_house", MageHouseStructure::new, TimeStructureSeparationSettings.create(10, 5), NoFeatureConfig.CODEC, NoFeatureConfig.NONE)
                    .transformsSurroundingLand()
                    .setDimensionPredicate(serverWorld -> serverWorld.dimension() == World.OVERWORLD)
                    .addToTag(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoFeatureConfig, AlloyMenderStructure> ALLOY_MENDER =
            REGISTER.register("alloy_mender_hut", AlloyMenderStructure::new, TimeStructureSeparationSettings.create(10, 5), NoFeatureConfig.CODEC, NoFeatureConfig.NONE)
                    .transformsSurroundingLand()
                    .setDimensionPredicate(serverWorld -> serverWorld.dimension() == JDimensions.EUCA_WORLD)
                    .addToTag(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();

    public static final StructureHolder<NoFeatureConfig, EucaDungeonStructure> EUCA_SHPHERE_DUNGEON =
            REGISTER.register("euca_sphere_dungeon", EucaDungeonStructure::new, TimeStructureSeparationSettings.create(10, 5), NoFeatureConfig.CODEC, NoFeatureConfig.NONE)
                    .setDimensionPredicate(serverWorld -> serverWorld.dimension() == JDimensions.EUCA_WORLD)
                    .addToTag(Tag.DISABLE_BREAKING_BY_LAKES)
                    .asHolder();
}
