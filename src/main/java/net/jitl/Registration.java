package net.jitl;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
    }
}