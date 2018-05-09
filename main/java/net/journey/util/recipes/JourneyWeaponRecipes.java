package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.item.Item;

public class JourneyWeaponRecipes {

	public static void init() {
		initWeaponCrafting();
	}

	public static void initWeaponCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();
		/*GameRegistry.addRecipe(new ItemStack(JourneyItems.demonicBomb, 16), new Object[] {"ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.withicBlade), new Object[] {"i", "i", "d", 'd', JourneyItems.withicDust, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.reinforcedStoneSword), new Object[] {"d", "d", "i", 'd', JourneyItems.reinforcedStoneIngot, 'i', JourneyItems.stoneStick});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.reinforcedCrystalSword), new Object[] {"d", "d", "i", 'd', JourneyItems.reinforcedCrystalIngot, 'i', JourneyItems.stoneStick});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.pedalSword), new Object[] {"d", "d", "i", 'd', JourneyItems.floroPedal, 'i', JourneyItems.stoneClump});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.crystalBlade), new Object[] {"d", "d", "i", 'd', JourneyItems.caveCrystal, 'i', Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.flameBow), new Object[] {" fs", "f s", " fs", 'f', Items.fire_charge, 's', Items.string});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.demonicSword),new Object[] {"d", "d", "i", 'd', JourneyItems.demonicBone, 'i', JourneyItems.demonicDust});*/
	}

	private static void addSword(Item sword, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(sword), new Object[] {" i ", " i ", " s ", 'i', ingot, 's', Items.stick});
	}
}