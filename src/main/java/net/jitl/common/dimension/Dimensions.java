package net.jitl.common.dimension;

import net.jitl.JITL;
import net.jitl.common.block.base.JBlock;
import net.jitl.init.JBlocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class Dimensions {

    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

    public static final RegistryKey<World> EUCA = RegistryKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("euca"));
    public static final RegistryKey<World> FROZEN_LANDS = RegistryKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("frozen"));
    public static final RegistryKey<World> DEPTHS = RegistryKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("depths"));
    public static final RegistryKey<World> BOIL = RegistryKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("boil"));

    public static RegistryObject<PointOfInterestType> EUCA_PORTAL = POI.register("euca_portal", () -> new PointOfInterestType("euca_portal", PointOfInterestType.getBlockStates(JBlocks.EUCA_PORTAL), 0, 1));
    public static RegistryObject<PointOfInterestType> FROZEN_PORTAL = POI.register("frozen_portal", () -> new PointOfInterestType("frozen_portal", PointOfInterestType.getBlockStates(JBlocks.FROZEN_PORTAL), 0, 1));
    public static RegistryObject<PointOfInterestType> BOILING_PORTAL = POI.register("boiling_portal", () -> new PointOfInterestType("boiling_portal", PointOfInterestType.getBlockStates(JBlocks.BOIL_PORTAL), 0, 1));
    public static RegistryObject<PointOfInterestType> DEPTHS_PORTAL = POI.register("depths_portal", () -> new PointOfInterestType("depths_portal", PointOfInterestType.getBlockStates(JBlocks.DEPTHS_PORTAL), 0, 1));

    public static void register(IEventBus bus) {
        POI.register(bus);
    }
}