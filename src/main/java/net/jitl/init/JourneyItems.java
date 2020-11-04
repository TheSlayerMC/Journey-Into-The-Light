package net.jitl.init;

import net.jitl.Registration;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class JourneyItems {

    public static final RegistryObject<Item> SAPPHIRE = Registration.ITEMS.register("sapphire", () -> new Item(new Item.Properties().tab(JTabs.ITEMS)));
    public static final RegistryObject<Item> LUNIUM_INGOT = Registration.ITEMS.register("lunium_ingot", () -> new Item(new Item.Properties().tab(JTabs.ITEMS)));

    public static final RegistryObject<Item> LUNIUM_SWORD = Registration.ITEMS.register("lunium_sword", () -> new Item(new Item.Properties().tab(JTabs.ITEMS)));

    public static void register() {
    }
}