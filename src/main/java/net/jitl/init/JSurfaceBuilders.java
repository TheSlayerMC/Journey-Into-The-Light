package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.surfacebuilders.TestSurfaceBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JSurfaceBuilders {

    //TODO: surface builders dont exist anymore

    @AutoRegistrable
    private static final SimpleForgeRegister<SurfaceBuilder<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.SURFACE_BUILDERS, JITL.MODID);

    public static final RegistryObject<SurfaceBuilder<?>> EUCA = REGISTER.register("euca_plains", () -> new TestSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));
    public static final RegistryObject<SurfaceBuilder<?>> EUCA_SILVER = REGISTER.register("euca_silver_plains", () -> new TestSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));

}
