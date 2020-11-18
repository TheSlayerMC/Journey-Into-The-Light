package net.jitl.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class JTabs {

    public static final ItemGroup BLOCKS = new ItemGroup("journey.blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JBlocks.SHADIUM_ORE);
        }
    };

    public static final ItemGroup DECORATION = new ItemGroup("journey.decoration") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JBlocks.DUNGEON_LAMP);
        }
    };

    public static final ItemGroup PORTALS = new ItemGroup("journey.portals") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.FLINT_AND_STEEL);
        }
    };

    public static final ItemGroup ITEMS = new ItemGroup("journey.items") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.SAPPHIRE);
        }
    };

    public static final ItemGroup TOOLS = new ItemGroup("journey.tools") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND_PICKAXE);
        }
    };

    public static final ItemGroup ARMOR = new ItemGroup("journey.armor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND_HELMET);
        }
    };

    public static final ItemGroup WEAPONS = new ItemGroup("journey.weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.LUNIUM_SWORD);
        }
    };

    public static final ItemGroup RANGED_WEAPONS = new ItemGroup("journey.ranged_weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };

    public static final ItemGroup MISC = new ItemGroup("journey.misc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JItems.TEST_BUG);
        }
    };

    public static final ItemGroup SPAWNERS = new ItemGroup("journey.spawners") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.SPAWNER);
        }
    };
}