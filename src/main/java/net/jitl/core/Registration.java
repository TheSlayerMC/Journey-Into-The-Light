package net.jitl.core;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//TODO: remove this?
public class Registration {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
    }
}