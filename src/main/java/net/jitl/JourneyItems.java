package net.jitl;

import net.jitl.init.JourneyTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class JourneyItems {

    public static final RegistryObject<Item> SAPPHIRE = Registration.ITEMS.register("sapphire", () -> new Item(new Item.Properties().group(JourneyTabs.items)));//make JItem

    public static void register() {

    }
}