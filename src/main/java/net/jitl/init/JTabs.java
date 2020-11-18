package net.jitl.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class JTabs {

    public static final ItemGroup BLOCKS = new ItemGroup("jitl.blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JBlocks.SHADIUM_ORE);
        }
    };

    public static final ItemGroup DECORATION = new ItemGroup("jitl.decoration") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JBlocks.DUNGEON_LAMP);
        }
    };

    public static final ItemGroup PORTALS = new ItemGroup("jitl.portals") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.FLINT_AND_STEEL);
        }
    };

    public static final ItemGroup ITEMS = new ItemGroup("jitl.items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.SAPPHIRE);
        }
    };

    public static final ItemGroup TOOLS = new ItemGroup("jitl.tools") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND_PICKAXE);
        }
    };

    public static final ItemGroup ARMOR = new ItemGroup("jitl.armor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND_HELMET);
        }
    };

    public static final ItemGroup WEAPONS = new ItemGroup("jitl.weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.LUNIUM_SWORD);
        }
    };

    public static final ItemGroup RANGED_WEAPONS = new ItemGroup("jitl.ranged_weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };

    public static final ItemGroup MISC = new ItemGroup("jitl.misc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.TEST_BUG);
        }
    };

    public static final ItemGroup SPAWNERS = new ItemGroup("jitl.spawners") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.SPAWNER);
        }
    };
}