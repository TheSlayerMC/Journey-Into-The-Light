package net.jitl.init.world;

import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Dimensions {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, JITL.MODID);

    public static final ResourceKey<Level> EUCA = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("euca"));
    public static final ResourceKey<DimensionType> EUCA_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("euca"));

    public static final ResourceKey<Level> FROZEN_LANDS = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("frozen"));
    public static final ResourceKey<DimensionType> FROZEN_LANDS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("frozen"));

    public static final ResourceKey<Level> DEPTHS = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("depths"));
    public static final ResourceKey<DimensionType> DEPTHS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("depths"));
    public static final ResourceKey<Level> BOIL = ResourceKey.create(Registry.DIMENSION_REGISTRY, JITL.rl("boil"));
    public static final ResourceKey<DimensionType> BOIL_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, JITL.rl("boil"));

    public static RegistryObject<PoiType> EUCA_PORTAL = POI.register("euca_portal", () -> new PoiType("euca_portal", PoiType.getBlockStates(JBlocks.EUCA_PORTAL), 0, 1));
    public static RegistryObject<PoiType> FROZEN_PORTAL = POI.register("frozen_portal", () -> new PoiType("frozen_portal", PoiType.getBlockStates(JBlocks.FROZEN_PORTAL), 0, 1));
    public static RegistryObject<PoiType> BOILING_PORTAL = POI.register("boiling_portal", () -> new PoiType("boiling_portal", PoiType.getBlockStates(JBlocks.BOIL_PORTAL), 0, 1));
    public static RegistryObject<PoiType> DEPTHS_PORTAL = POI.register("depths_portal", () -> new PoiType("depths_portal", PoiType.getBlockStates(JBlocks.DEPTHS_PORTAL), 0, 1));

    public static void register(IEventBus bus) {
        POI.register(bus);
    }
}