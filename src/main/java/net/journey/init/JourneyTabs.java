package net.journey.init;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.LangHelper;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JourneyTabs extends CreativeTabs {

    public static final JourneyTabs BLOCKS = new JourneyTabs("Blocks");
    public static final JourneyTabs DECORATION = new JourneyTabs("Decoration");
    public static final JourneyTabs ITEMS = new JourneyTabs("Items");
    public static final JourneyTabs TOOLS = new JourneyTabs("Tools");
    public static final JourneyTabs WEAPONS = new JourneyTabs("Weapons");
    public static final JourneyTabs UTIL = new JourneyTabs("Utilities");
    public static final JourneyTabs ARMOR = new JourneyTabs("Armor");
    public static final JourneyTabs SPAWNERS = new JourneyTabs("Spawners");
    public static final JourneyTabs CROPS = new JourneyTabs("Crops");
    public static final JourneyTabs MACHINE_BLOCKS = new JourneyTabs("Machine Blocks");
    public static final JourneyTabs PORTAL_BLOCKS = new JourneyTabs("Portal Blocks");

    public ItemStack item;

    /**
     * @param enName - localized name for tab
     *               "JITL: " prefix will be added to it.
     */
    public JourneyTabs(String enName) {
        super(LangHelper.withModPrefix(LangHelper.nameToKey(enName)));

        LangGeneratorFacade.addCreativeTabEntry(this, enName.contains("JITL:") ? enName : "JITL: " + enName);
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

    public void setIcon(Block icon) {
        setIcon(Item.getItemFromBlock(icon));
    }

    public void setIcon(Item icon) {
        setIcon(new ItemStack(icon));
    }

    public void setIcon(ItemStack icon) {
        this.item = icon;
    }

    @Override
    public ItemStack createIcon() {
        return item;
    }
}
