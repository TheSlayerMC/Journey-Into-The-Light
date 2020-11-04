package net.jitl.registry.internal;

import net.jitl.JITL;
import net.jitl.init.JTabs;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.BlockPropsFactory;
import ru.timeconqueror.timecore.registry.newreg.BlockRegister;

public class BlockRegistrator {
    private static final BlockPropsFactory STONE_PROPS = new BlockPropsFactory(() -> Properties.of(Material.STONE).sound(SoundType.STONE));

    @AutoRegistrable
    private static final BlockRegister REGISTER = new BlockRegister(JITL.MODID);

    @AutoRegistrable.InitMethod
    private static void register() {
        registerDefaultBlock("sapphire_ore", "Sapphire Ore");
        registerDefaultBlock("lunium_ore", "Lunium Ore");
        registerDefaultBlock("shadium_ore", "Shadium Ore");
        registerDefaultBlock("iridium_ore", "Iridium Ore");
        registerDefaultBlock("hellstone_ore", "Hellstone Ore");
        registerDefaultBlock("ashual_ore", "Ashual Ore");
        registerDefaultBlock("blazium_ore", "Blazium Ore");
        registerDefaultBlock("mekyum_ore", "Mekyum Ore");
        registerDefaultBlock("celestium_ore", "Celestium Ore");
        registerDefaultBlock("storon_ore", "Storon Ore");
        registerDefaultBlock("flairium_ore", "Flairium Ore");
        registerDefaultBlock("des_ore", "Des Ore");
        registerDefaultBlock("enderillium_ore", "Enderillium Ore");
        registerDefaultBlock("gorbite_ore", "Gorbite Ore");
        registerDefaultBlock("orbadite_ore", "Orbadite Ore");
        registerDefaultBlock("lunite_ore", "Lunite Ore");
        registerDefaultBlock("firestone_ore", "Firestone Ore");
        registerDefaultBlock("lava_rock", "Lava Rock");

        registerDefaultBlock("sapphire_block", "Sapphire Block");
        registerDefaultBlock("lunium_block", "Lunium Block");
        registerDefaultBlock("shadium_block", "Shadium Block");
        registerDefaultBlock("iridium_block", "Iridium Block");
        registerDefaultBlock("hellstone_block", "Hellstone Block");
        registerDefaultBlock("ashual_block", "Ashual Block");
        registerDefaultBlock("blazium_block", "Blazium Block");
        registerDefaultBlock("mekyum_block", "Mekyum Block");
        registerDefaultBlock("celestium_block", "Celestium Block");
        registerDefaultBlock("storon_block", "Storon Block");
        registerDefaultBlock("flairium_block", "Flairium Block");
        registerDefaultBlock("des_block", "Des Block");
        registerDefaultBlock("enderillium_block", "Enderillium Block");
        registerDefaultBlock("gorbite_block", "Gorbite Block");
        registerDefaultBlock("orbadite_block", "Orbadite Block");
        registerDefaultBlock("lunite_block", "Lunite Block");
        registerDefaultBlock("nethic_gemstone_block", "Nethic Gemstone Block");
        registerDefaultBlock("frost_gem_block", "Frost Gems Block");
    }

    private static void registerDefaultBlock(String name, String enName) {
        REGISTER.register(name, () -> new Block(STONE_PROPS.create()))
                .genLangEntry(enName)
                .regDefaultBlockItem(JTabs.BLOCKS)
                .genDefaultStateAndModel();
    }
}
