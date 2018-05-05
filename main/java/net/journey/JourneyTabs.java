package net.journey;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JourneyTabs extends CreativeTabs {
	
	public static final JourneyTabs blocks = new JourneyTabs("journey.blocks");
	public static final JourneyTabs decoration = new JourneyTabs("journey.decoration");
	public static final JourneyTabs items = new JourneyTabs("journey.items");
	public static final JourneyTabs tools = new JourneyTabs("journey.tools");
	public static final JourneyTabs swords = new JourneyTabs("journey.swords");
	public static final JourneyTabs battleaxes = new JourneyTabs("journey.battleaxes");
	public static final JourneyTabs bows = new JourneyTabs("journey.bows");
	public static final JourneyTabs piercers = new JourneyTabs("journey.piercers");
	public static final JourneyTabs staves = new JourneyTabs("journey.staves");
	public static final JourneyTabs util = new JourneyTabs("journey.util");
	public static final JourneyTabs misc = new JourneyTabs("journey.misc");
	public static final JourneyTabs armor = new JourneyTabs("journey.armor");
	public static final JourneyTabs spawners = new JourneyTabs("journey.spawners");
	public static final JourneyTabs crops = new JourneyTabs("journey.crops");
	public static final JourneyTabs machineBlocks = new JourneyTabs("journey.machineBlocks");
	public static final JourneyTabs portalBlocks = new JourneyTabs("journey.portalBlocks");
	public static final JourneyTabs hammers = new JourneyTabs("journey.hammers");

	public Item item;
	
	public JourneyTabs(String name) {
		super(name);
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
	public ItemStack getTabIconItem() {
		return new ItemStack(item);
	}
	
	public static void init(){
		blocks.setIcon(JourneyBlocks.cloudiaRock);
		decoration.setIcon(JourneyBlocks.boilingLamp);
		items.setIcon(JourneyItems.koriteIngot);
		tools.setIcon(JourneyItems.multiToolOfEternalSmelting);
		swords.setIcon(JourneyItems.terronicBlade);
		battleaxes.setIcon(JourneyItems.backBiter);
		bows.setIcon(JourneyItems.flamingBow);
		piercers.setIcon(JourneyItems.eucaPiercer);
		staves.setIcon(JourneyItems.conjuringStaff);
		util.setIcon(JourneyItems.flameCoin);
		misc.setIcon(JourneyItems.weakDarkEnergyPotion);
		armor.setIcon(JourneyItems.twilightHelmet);
		spawners.setIcon(JourneyItems.sentryKingOrb);
		crops.setIcon(JourneyCrops.crackenCaneSeeds);
		machineBlocks.setIcon(JourneyBlocks.summoningTable);
		portalBlocks.setIcon(JourneyBlocks.eucaPortal);
		hammers.setIcon(JourneyItems.flamingHammer);
	}
}
