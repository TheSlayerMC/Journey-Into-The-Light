package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.GuardianTowerStructure;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.StructureRegister;
import ru.timeconqueror.timecore.registry.newreg.StructureRegister.StructureHolder;
import ru.timeconqueror.timecore.registry.newreg.StructureRegister.TimeStructureSeparationSettings;

public class JStructures {
    @AutoRegistrable
    private static final StructureRegister REGISTER = new StructureRegister(JITL.MODID);

    public static final StructureHolder<NoFeatureConfig, GuardianTowerStructure> GUARDIAN_TOWER_HOLDER =
            REGISTER.register("guardian_tower", GuardianTowerStructure::new, TimeStructureSeparationSettings.create(10, 5), NoFeatureConfig.CODEC, NoFeatureConfig.NONE)
                    .transformsSurroundingLand()
                    .setDimensionPredicate(serverWorld -> serverWorld.dimension() == World.OVERWORLD)
                    .asHolder();
}
