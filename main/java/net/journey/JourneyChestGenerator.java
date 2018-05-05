package net.journey;

public class JourneyChestGenerator {

	public static void init() { 
		/*ChestGenHooks.getInfo(ChestGenHooks.NETHER_FORTRESS).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.boilKey), 1, 1, 10));
		
		ChestGenHooks.getInfo(ChestGenHooks.NETHER_FORTRESS).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartSml), 1, 1, 30));
		
		ChestGenHooks.getInfo(ChestGenHooks.NETHER_FORTRESS).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 20));

		ChestGenHooks.getInfo(ChestGenHooks.NETHER_FORTRESS).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartMed), 1, 1, 2));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartLrg), 1, 1, 10));
		
		ChestGenHooks.getInfo(ChestGenHooks.NETHER_FORTRESS).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(JourneyItems.heartUlt), 1, 1, 5));*/
	}
}
