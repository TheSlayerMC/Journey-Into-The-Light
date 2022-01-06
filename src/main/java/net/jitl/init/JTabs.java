package net.jitl.init;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class JTabs {

    public static final CreativeModeTab BLOCKS = new CreativeModeTab("jitl.blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JBlocks.SHADIUM_ORE);
        }
    };

    public static final CreativeModeTab DECORATION = new CreativeModeTab("jitl.decoration") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JBlocks.DUNGEON_LAMP);
        }
    };

    public static final CreativeModeTab PORTALS = new CreativeModeTab("jitl.portals") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.FLINT_AND_STEEL);
        }
    };

    public static final CreativeModeTab ITEMS = new CreativeModeTab("jitl.items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.SAPPHIRE);
        }
    };

    public static final CreativeModeTab TOOLS = new CreativeModeTab("jitl.tools") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.SAPPHIRE_PICKAXE);
        }
    };

    public static final CreativeModeTab ARMOR = new CreativeModeTab("jitl.armor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.SAPPHIRE_HELMET);
        }
    };

    public static final CreativeModeTab WEAPONS = new CreativeModeTab("jitl.weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.LUNIUM_SWORD);
        }
    };

    public static final CreativeModeTab RANGED_WEAPONS = new CreativeModeTab("jitl.ranged_weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };

    public static final CreativeModeTab MISC = new CreativeModeTab("jitl.misc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.TEST_BUG);
        }
    };

    public static final CreativeModeTab SPAWNERS = new CreativeModeTab("jitl.spawners") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.SPAWNER);
        }
    };
}