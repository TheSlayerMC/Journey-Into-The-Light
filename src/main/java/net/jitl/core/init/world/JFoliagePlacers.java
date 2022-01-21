package net.jitl.core.init.world;

import net.jitl.common.world.gen.foliageplacer.SphericalFoliagePlacer;
import net.jitl.core.JITL;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JFoliagePlacers {

    @AutoRegistrable
    //TODO: change back to forge registry once they get their shit together
    public static final SimpleVanillaRegister<FoliagePlacerType<?>> REGISTER = new SimpleVanillaRegister<>(JITL.MODID, Registry.FOLIAGE_PLACER_TYPES);

    public static final Promised<FoliagePlacerType<SphericalFoliagePlacer>> SPHERICAL_FOLIAGE_PLACER = REGISTER.register("spherical_foliage_placer", () -> new FoliagePlacerType<>(SphericalFoliagePlacer.CODEC));
}
