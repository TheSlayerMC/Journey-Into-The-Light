package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.surfacebuilders.TestSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JSurfaceBuilders {

    @AutoRegistrable
    private static final SimpleForgeRegister<SurfaceBuilder<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.SURFACE_BUILDERS, JITL.MODID);

    public static final RegistryObject<SurfaceBuilder<?>> EUCA = REGISTER.register("euca_plains", () -> new TestSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));
    public static final RegistryObject<SurfaceBuilder<?>> EUCA_SILVER = REGISTER.register("euca_silver_plains", () -> new TestSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC));

}
