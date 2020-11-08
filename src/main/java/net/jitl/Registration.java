package net.jitl;

import net.jitl.init.JItems;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JITL.MODID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JITL.MODID);

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
        ITEMS.register(bus);
        JItems.register();
    }
}