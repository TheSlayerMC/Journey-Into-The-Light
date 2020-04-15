package net.journey.init;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JourneyTabs extends CreativeTabs {

    public static final JourneyTabs BLOCKS = new JourneyTabs("journey.blocks");
    public static final JourneyTabs DECORATION = new JourneyTabs("journey.decoration");
    public static final JourneyTabs ITEMS = new JourneyTabs("journey.items");
    public static final JourneyTabs TOOLS = new JourneyTabs("journey.tools");
    public static final JourneyTabs WEAPONS = new JourneyTabs("journey.weapons");
    public static final JourneyTabs UTIL = new JourneyTabs("journey.util");
    public static final JourneyTabs ARMOR = new JourneyTabs("journey.armor");
    public static final JourneyTabs SPAWNERS = new JourneyTabs("journey.spawners");
    public static final JourneyTabs CROPS = new JourneyTabs("journey.crops");
    public static final JourneyTabs MACHINE_BLOCKS = new JourneyTabs("journey.machineBlocks");
    public static final JourneyTabs PORTAL_BLOCKS = new JourneyTabs("journey.portalBlocks");

    public Item item;

    public JourneyTabs(String name) {
        super(name);
    }

    public static void init() {
        BLOCKS.setIcon(JourneyBlocks.eucaGrass);
        DECORATION.setIcon(JourneyBlocks.boilingLamp);
        ITEMS.setIcon(JourneyItems.koriteIngot);
        TOOLS.setIcon(JourneyArmory.multiToolOfEternalSmelting);
        WEAPONS.setIcon(JourneyWeapons.coreMender);
        UTIL.setIcon(JourneyItems.flameCoin);
        ARMOR.setIcon(JourneyArmory.blazehornHelmet);
        SPAWNERS.setIcon(JourneyItems.sentryKingOrb);
        CROPS.setIcon(JourneyConsumables.hongoShroom);
        MACHINE_BLOCKS.setIcon(JourneyBlocks.summoningTable);
        PORTAL_BLOCKS.setIcon(JourneyBlocks.eucaPortal);
    }

    public void setIcon(Item icon) {
        this.item = icon;
    }

    public void setIcon(Block icon) {
        this.item = Item.getItemFromBlock(icon);
    }

    public void setIcon(ItemStack icon) {
        this.item = icon.getItem();
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(item);
    }
}
