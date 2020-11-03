package net.jitl;

import net.jitl.block.JBlockOre;
import net.jitl.block.base.JBlock;
import net.jitl.init.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class JourneyBlocks {

//    public static RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new JBlockOre("Sapphire Ore"));//drops are loot tables now?
//    public static RegistryObject<Block> SAPPHIRE_BLOCK = register("sapphire_block", () -> new JBlock("Sapphire Block"));

    public static void register() {

    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ItemGroup tab) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(tab)));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(JourneyTabs.BLOCKS)));
        return ret;
    }
}