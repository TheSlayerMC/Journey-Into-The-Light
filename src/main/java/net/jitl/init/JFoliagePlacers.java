package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.foliageplacer.SphericalFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JFoliagePlacers {

    @AutoRegistrable
    public static final SimpleForgeRegister<FoliagePlacerType<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.FOLIAGE_PLACER_TYPES, JITL.MODID);

    public static final RegistryObject<FoliagePlacerType<SphericalFoliagePlacer>> SPHERICAL_FOLIAGE_PLACER = REGISTER.register("spherical_foliage_placer", () -> new FoliagePlacerType<>(SphericalFoliagePlacer.CODEC));
}
