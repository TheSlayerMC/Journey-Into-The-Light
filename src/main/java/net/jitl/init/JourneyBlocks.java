package net.jitl.init;

import net.jitl.Registration;
import net.jitl.common.block.JBlockOre;
import net.jitl.common.block.base.JBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class JourneyBlocks {

    public static RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new JBlockOre("Sapphire Ore"));
    public static RegistryObject<Block> LUNIUM_ORE = register("lunium_ore", () -> new JBlockOre("Lunium Ore"));
    public static RegistryObject<Block> SHADIUM_ORE = register("shadium_ore", () -> new JBlockOre("Shadium Ore"));
    public static RegistryObject<Block> IRIDIUM_ORE = register("iridium_ore", () -> new JBlockOre("Iridium Ore"));
    public static RegistryObject<Block> HELLSTONE_ORE = register("hellstone_ore", () -> new JBlockOre("Hellstone Ore"));
    public static RegistryObject<Block> ASHUAL_ORE = register("ashual_ore", () -> new JBlockOre("Ashual Ore"));
    public static RegistryObject<Block> BLAZIUM_ORE = register("blazium_ore", () -> new JBlockOre("Blazium Ore"));
    public static RegistryObject<Block> CELESTIUM_ORE = register("celestium_ore", () -> new JBlockOre("Celestium Ore"));
    public static RegistryObject<Block> MEKYUM_ORE = register("mekyum_ore", () -> new JBlockOre("Mekyum Ore"));
    public static RegistryObject<Block> STORON_ORE = register("storon_ore", () -> new JBlockOre("Storon Ore"));
    public static RegistryObject<Block> FLAIRIUM_ORE = register("flairium_ore", () -> new JBlockOre("Flairium Ore"));
    public static RegistryObject<Block> DES_ORE = register("des_ore", () -> new JBlockOre("Des Ore"));
    public static RegistryObject<Block> ENDERILLIUM_ORE = register("enderillium_ore", () -> new JBlockOre("Enderillium Ore"));
    public static RegistryObject<Block> GORBITE_ORE = register("gorbite_ore", () -> new JBlockOre("Gorbite Ore"));
    public static RegistryObject<Block> ORBADITE_ORE = register("orbadite_ore", () -> new JBlockOre("Orbadite Ore"));
    public static RegistryObject<Block> LUNITE_ORE = register("lunite_ore", () -> new JBlockOre("Lunite Ore"));
    public static RegistryObject<Block> FIRESTONE_ORE = register("firestone_ore", () -> new JBlockOre("Firestone Ore"));
    public static RegistryObject<Block> LAVA_ROCK = register("lava_rock", () -> new JBlockOre("Lava Rock"));

    public static RegistryObject<Block> SAPPHIRE_BLOCK = register("sapphire_block", () -> new JBlock("Sapphire Block"));
    public static RegistryObject<Block> LUNIUM_BLOCK = register("lunium_block", () -> new JBlock("Lunium Block"));
    public static RegistryObject<Block> SHADIUM_BLOCK = register("shadium_block", () -> new JBlock("Shadium Block"));
    public static RegistryObject<Block> IRIDIUM_BLOCK = register("iridium_block", () -> new JBlock("Iridium Block"));
    public static RegistryObject<Block> HELLSTONE_BLOCK = register("hellstone_block", () -> new JBlock("Hellstone Block"));
    public static RegistryObject<Block> ASHUAL_BLOCK = register("ashual_block", () -> new JBlock("Ashual Block"));
    public static RegistryObject<Block> BLAZIUM_BLOCK = register("blazium_block", () -> new JBlock("Blazium Block"));
    public static RegistryObject<Block> CELESTIUM_BLOCK = register("celestium_block", () -> new JBlock("Celestium Block"));
    public static RegistryObject<Block> MEKYUM_BLOCK = register("mekyum_block", () -> new JBlock("Mekyum Block"));
    public static RegistryObject<Block> STORON_BLOCK = register("storon_block", () -> new JBlock("Storon Block"));
    public static RegistryObject<Block> FLAIRIUM_BLOCK = register("flairium_block", () -> new JBlock("Flairium Block"));
    public static RegistryObject<Block> DES_BLOCK = register("des_block", () -> new JBlock("Des Block"));
    public static RegistryObject<Block> ENDERILLIUM_BLOCK = register("enderillium_block", () -> new JBlock("Enderillium Block"));
    public static RegistryObject<Block> GORBITE_BLOCK = register("gorbite_block", () -> new JBlock("Gorbite Block"));
    public static RegistryObject<Block> ORBADITE_BLOCK = register("orbadite_block", () -> new JBlock("Orbadite Block"));
    public static RegistryObject<Block> LUNITE_BLOCK = register("lunite_block", () -> new JBlock("Lunite Block"));
    public static RegistryObject<Block> NETHIC_GEMSTONE_BLOCK = register("nethic_gemstone_block", () -> new JBlock("Nethic Gemstone Block"));
    public static RegistryObject<Block> FROST_GEM_BLOCK = register("frost_gem_block", () -> new JBlock("Frost Gems Block"));

    public static void register() { }

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