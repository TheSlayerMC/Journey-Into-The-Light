package net.jitl.init.world;

import net.jitl.JITL;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.*;
import ru.timeconqueror.timecore.api.registry.PlacedFeatureRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.List;

public class JPlacedFeatures {

    @AutoRegistrable
    public static final PlacedFeatureRegister REGISTER = new PlacedFeatureRegister(JITL.MODID);

    public static final PlacedFeature DEFAULT_OVERWORLD_RUINS = REGISTER.register(
                    "generic_overworld_ruins",
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    () -> JConfiguredFeatures.DEFAULT_OVERWORLD_RUINS.get()
                            .placed(patch(10)))
            .asPromise()
            .get();

    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
