package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JourneyMaterialRecipes {

	public static void init() {
		initMaterialCrafting();
	}

	public static void initMaterialCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();
		/*GameRegistry.addRecipe(new ItemStack(JourneyItems.flameCoin), new Object[] {"iii", "idi", "iii", 'i', Items.GOLD_INGOT, 'd', Items.DIAMOND});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.hellcrustIngot), new Object[] {"iii", "idi", "iii", 'i', JourneyItems.ash, 'd', JourneyItems.hellstoneIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.reinforcedStoneIngot), new Object[] {"ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', Blocks.STONE});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot), new Object[] {"ddd", "did", "ddd", 'd', JourneyItems.stoneClump, 'i', JourneyItems.caveCrystal});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.crystalBall), new Object[] {"idi", "ixi", 'i', Items.DIAMOND, 'd', Items.ENDER_PEARL, 'x', JourneyItems.sapphire});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.demonicBomb, 16), new Object[] {"ddd", "did", "ddd", 'd', JourneyItems.demonicDust, 'i', JourneyItems.crystalBall});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.stoneClump, 4), new Object[] {"ddd", "ddd", "ddd", 'd', JourneyItems.caveDust});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.stoneStick, 16), new Object[] {"d", "d", "d", 'd', Blocks.STONE});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.stoneClump, 16), new Object[] {"ddd", "ddd", "ddd", 'd', Blocks.STONE});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.stoneClump, 16), new Object[] {"ddd", "ddd", "ddd", 'd', Blocks.COBBLESTONE});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.withicSoul, 1), new Object[] {"ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.withicDust});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.concentratedBlood, 1), new Object[] {"ddd", "did", "ddd", 'i', JourneyItems.lostSoul, 'd', JourneyItems.blood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.boilKey, 1), new Object[] {"dgd", "did", "dgd", 'd', JourneyItems.flamingSpring, 'i', JourneyItems.flamingSprocket, 'g', Items.GOLD_INGOT});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.essenceArrow, 4), new Object[] {"d", "i", "g", 'd', Items.IRON_INGOT, 'i', JourneyItems.stoneStick, 'g', JourneyItems.rocFeather});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.withicHammer, 1),new Object[] {"ddd", "did", " i ", 'd', JourneyItems.hellcrustIngot, 'i', JourneyItems.withicSpine});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.nethicHammer, 1),new Object[] {"jdj", "did", " i ", 'd', JourneyBlocks.hellstoneBlock, 'i', JourneyItems.hellstoneClump, 'j', JourneyItems.flamingSpring});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.pocketCrafting, 1),new Object[] {"ddd", "did", "ddd", 'd', JourneyItems.flamingHide, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.demonicDust, 5), new Object[]{JourneyItems.demonicBone});*/
		
		addOPFood(JourneyItems.goldenPork, JourneyItems.goldenPorkOP, Items.PORKCHOP);
		addOPFood(JourneyItems.goldenSteak, JourneyItems.goldenSteakOP, Items.BEEF);
		addOPFood(JourneyItems.goldenPotato, JourneyItems.goldenPotatoOP, Items.POTATO);
		addOPFood(JourneyItems.goldenFish, JourneyItems.goldenFishOP, Items.FISH);
		addOPFood(JourneyItems.goldenChicken, JourneyItems.goldenChickenOP, Items.CHICKEN);
		addOPFood(JourneyItems.goldenRabbit, JourneyItems.goldenRabbitOP, Items.RABBIT);
		addOPFood(JourneyItems.goldenMutton, JourneyItems.goldenMuttonOP, Items.MUTTON);
		addOPFood(JourneyItems.goldenWing, JourneyItems.goldenWingOP, JourneyItems.rocMeat);
		/*GameRegistry.addRecipe(new ItemStack(JourneyItems.hellstoneClump), new Object[] {"iii", 'i', JourneyItems.hellstoneIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.shadiumClump), new Object[] {"iii", 'i', JourneyItems.shadiumIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.luniumClump), new Object[] {"iii", 'i', JourneyItems.luniumIngot});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.spawnerClump), new Object[]{JourneyItems.shadiumClump, JourneyItems.luniumClump, JourneyItems.hellstoneClump});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyBlocks.redGlowshroomTop});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyBlocks.redGlowshroomBottom});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyBlocks.blueGlowshroomTop});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyBlocks.blueGlowshroomBottom});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyBlocks.greenGlowshroomTop});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyBlocks.greenGlowshroomBottom});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.glowshroomPowder, 4), new Object[]{JourneyItems.glowshroom});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyCrops.floroSeeds, 4), new Object[]{JourneyItems.floroPedal});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.eucaPortalGem, 10), new Object[]{JourneyItems.eucaPortalPiece, JourneyItems.eucaPortalPiece_0, JourneyItems.eucaPortalPiece_1});*/
		GameRegistry.addSmelting(JourneyItems.spawnerClump, new ItemStack(JourneyItems.spawnerBar), 1.0F);
		GameRegistry.addSmelting(JourneyItems.flamingBeef, new ItemStack(JourneyItems.flamingBeefCooked), 0.5F);
		GameRegistry.addSmelting(JourneyItems.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		GameRegistry.addSmelting(JourneyItems.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(JourneyItems.diamondDust, new ItemStack(Items.DIAMOND), 0.5F);
		GameRegistry.addSmelting(JourneyItems.enderilliumDust, new ItemStack(JourneyItems.enderilliumShard), 0.5F);

		GameRegistry.addSmelting(new ItemStack(Items.LEATHER), new ItemStack(Items.ROTTEN_FLESH), 0.5F);
	}
	
	public static void addBlock(Block made, Item used) {
		//GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addBlock(Block made, Block used) {
		//GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addOPFood(Item nonOP, Item OP, Item base) {
		//GameRegistry.addRecipe(new ItemStack(nonOP), new Object[] {"iii", "ibi", "iii", 'i', Items.GOLD_INGOT, 'b', base});
		//GameRegistry.addRecipe(new ItemStack(OP), new Object[] {"iii", "ibi", "iii", 'i', Blocks.gold_block, 'b', base});
	}

	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		/*addAxe(axe, ingot);
		addPickaxe(pick, ingot);
		addShovel(shovel, ingot);
		addHoe(hoe, ingot);
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"b", "b", "s", 'b', block, 's', Items.STICK});
		addBlock(block, ingot);
		addHelmet(helmet, ingot);
		addChestplate(chest, ingot);
		addLeggings(legs, ingot);
		addBoots(boots, ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(multiTool), new Object[] {pick, shovel, hoe, axe});
		GameRegistry.addShapelessRecipe(new ItemStack(ingot, 9), new Object[] {block});
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);*/
	}

	private static void addWood(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
		/*GameRegistry.addShapelessRecipe(new ItemStack(plank, 4), new Object[] {log});
		GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] {"i  ", "ii ", "iii", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Items.STICK, 4), new Object[] {"i", "i", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table), new Object[] {"ii", "ii", 'i', plank});
		//GameRegistry.addRecipe(new ItemStack(EssenceBlocks.halfSlab, 6, slabMeta), new Object[] {"iii", 'i', plank});
		if(smelt) GameRegistry.addSmelting(log, new ItemStack(Items.coal), 0.5F);*/
	}

	private static void addAxe(Item axe, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(axe), new Object[] {" ii", " si", " s ", 'i', ingot, 's', Items.STICK});
	}

	private static void addPickaxe(Item pick, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(pick), new Object[] {"iii", " s ", " s ", 'i', ingot, 's', Items.STICK});
	}

	private static void addShovel(Item shovel, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {" i ", " s ", " s ", 'i', ingot, 's', Items.STICK});
	}

	private static void addSword(Item sword, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(sword), new Object[] {" i ", " i ", " s ", 'i', ingot, 's', Items.STICK});
	}

	private static void addHoe(Item hoe, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {" ii", " s ", " s ", 'i', ingot, 's', Items.STICK});
	}

	private static void addHelmet(Item helmet, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(helmet), new Object[] {"iii", "i i", 'i', ingot});
	}

	private static void addChestplate(Item chest, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(chest), new Object[] {"i i", "iii", "iii", 'i', ingot});
	}

	private static void addLeggings(Item legs, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(legs), new Object[] {"iii", "i i", "i i", 'i', ingot});
	}

	private static void addBoots(Item boots, Item ingot) {
		//GameRegistry.addRecipe(new ItemStack(boots), new Object[] {"i i", "i i", 'i', ingot});
	}
}