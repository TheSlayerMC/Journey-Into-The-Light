package net.journey.util.recipes;

public class JourneyMiscRecipes {

	public static void init() {
		//initMiscCrafting();
	}

	/*public static void initMiscCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornHelmet), new Object[] {"idi", "d d", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornChest), new Object[] {"i i", "did", "idi", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornLegs), new Object[] {"idi", "d d", "i i", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornBoots), new Object[] {"d d", "i i", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundHelmet), new Object[] {"idi", "d d", 'd', Items.blaze_rod, 'i', JourneyBlocks.hellstoneBlock});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundChest), new Object[] {"i i", "did", "idi", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundLegs), new Object[] {"idi", "d d", "i i", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundBoots), new Object[] {"d d", "i i", 'd', Items.blaze_rod, 'i', JourneyBlocks.hellstoneBlock});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustHelmet), new Object[] {"idi", "d d", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustChest), new Object[] {"i i", "did", "idi", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustLegs), new Object[] {"idi", "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustBoots), new Object[] {"d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockHelmet), new Object[] {"idi", "d d", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockChest), new Object[] {"i i", "did", "idi", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockLegs), new Object[] {"idi", "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockBoots), new Object[] {"d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.woodMultiTool), new Object[] {Items.wooden_pickaxe, Items.wooden_shovel, Items.wooden_hoe, Items.wooden_axe});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.stoneMultiTool), new Object[] {Items.stone_pickaxe, Items.stone_shovel, Items.stone_hoe, Items.stone_axe});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.ironMultiTool), new Object[] {Items.iron_pickaxe, Items.iron_shovel, Items.iron_hoe, Items.iron_axe});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.diamondMultiTool), new Object[] {Items.diamond_pickaxe, Items.diamond_shovel, Items.diamond_hoe, Items.diamond_axe});
		
		addOre(JourneyBlocks.celestiumOre, JourneyItems.celestiumIngot, JourneyBlocks.celestiumBlock, JourneyItems.celestiumAxe, JourneyItems.celestiumPickaxe, JourneyItems.celestiumShovel, JourneyItems.celestiumHoe, JourneyItems.celestiumSword, JourneyItems.celestiumMultiTool, JourneyItems.celestiumHelmet, JourneyItems.celestiumChest, JourneyItems.celestiumLegs, JourneyItems.celestiumBoots, JourneyItems.celestiumDust);
		addOre(JourneyBlocks.hellstoneOre, JourneyItems.hellstoneIngot, JourneyBlocks.hellstoneBlock, JourneyItems.hellstoneAxe, JourneyItems.hellstonePickaxe, JourneyItems.hellstoneShovel, JourneyItems.hellstoneHoe, JourneyItems.hellstoneSword, JourneyItems.hellstoneMultiTool, JourneyItems.hellstoneHelmet, JourneyItems.hellstoneChest, JourneyItems.hellstoneLegs, JourneyItems.hellstoneBoots, JourneyItems.hellstoneDust);
		addOre(JourneyBlocks.flairiumOre, JourneyItems.flairiumIngot, JourneyBlocks.flairiumBlock, JourneyItems.flairiumAxe, JourneyItems.flairiumPickaxe, JourneyItems.flairiumShovel, JourneyItems.flairiumHoe, JourneyItems.flairiumSword, JourneyItems.flairiumMultiTool, JourneyItems.flairiumHelmet, JourneyItems.flairiumChest, JourneyItems.flairiumLegs, JourneyItems.flairiumBoots, JourneyItems.flairiumDust);
		addOre(JourneyBlocks.desOre, JourneyItems.desIngot, JourneyBlocks.desBlock, JourneyItems.desAxe, JourneyItems.desPickaxe, JourneyItems.desShovel, JourneyItems.desHoe, JourneyItems.desSword, JourneyItems.desMultiTool, null, null, null, null, null);
		addOre(JourneyBlocks.shadiumOre, JourneyItems.shadiumIngot, JourneyBlocks.shadiumBlock, JourneyItems.shadiumAxe, JourneyItems.shadiumPickaxe, JourneyItems.shadiumShovel, JourneyItems.shadiumHoe, JourneyItems.shadiumSword, JourneyItems.shadiumMultiTool, JourneyItems.shadiumHelmet, JourneyItems.shadiumChest, JourneyItems.shadiumLegs, JourneyItems.shadiumBoots, JourneyItems.shadiumDust);
		addOre(JourneyBlocks.luniumOre, JourneyItems.luniumIngot, JourneyBlocks.luniumBlock, JourneyItems.luniumAxe, JourneyItems.luniumPickaxe, JourneyItems.luniumShovel, JourneyItems.luniumHoe, JourneyItems.luniumSword, JourneyItems.luniumMultiTool, JourneyItems.luniumHelmet, JourneyItems.luniumChest, JourneyItems.luniumLegs, JourneyItems.luniumBoots, JourneyItems.luniumDust);
		addOre(JourneyBlocks.sapphireOre, JourneyItems.sapphire, JourneyBlocks.sapphireBlock, JourneyItems.sapphireAxe, JourneyItems.sapphirePickaxe, JourneyItems.sapphireShovel, JourneyItems.sapphireHoe, JourneyItems.sapphireSword, JourneyItems.sapphireMultiTool, JourneyItems.sapphireHelmet, JourneyItems.sapphireChest, JourneyItems.sapphireLegs, JourneyItems.sapphireBoots, JourneyItems.sapphireDust);
		addOre(JourneyBlocks.gorbiteOre, JourneyItems.gorbiteGem, JourneyBlocks.gorbiteBlock, JourneyItems.gorbiteAxe, JourneyItems.gorbitePickaxe, JourneyItems.gorbiteShovel, JourneyItems.gorbiteHoe, JourneyItems.gorbiteSword, JourneyItems.gorbiteMultiTool, JourneyItems.gorbiteHelmet, JourneyItems.gorbiteChest, JourneyItems.gorbiteLegs, JourneyItems.gorbiteBoots, JourneyItems.gorbiteDust);
		addOre(JourneyBlocks.orbaditeOre, JourneyItems.orbaditeIngot, JourneyBlocks.orbaditeBlock, JourneyItems.orbaditeAxe, JourneyItems.orbaditePickaxe, JourneyItems.orbaditeShovel, JourneyItems.orbaditeHoe, JourneyItems.orbaditeSword, JourneyItems.orbaditeMultiTool, JourneyItems.orbaditeHelmet, JourneyItems.orbaditeChest, JourneyItems.orbaditeLegs, JourneyItems.orbaditeBoots, JourneyItems.orbaditeDust);
		addOre(JourneyBlocks.koriteOre, JourneyItems.koriteIngot, JourneyBlocks.koriteBlock, JourneyItems.koriteAxe, JourneyItems.koritePickaxe, JourneyItems.koriteShovel, JourneyItems.koriteHoe, JourneyItems.koriteSword, JourneyItems.koriteMultiTool, null, null, null, null, null);
		addOre(JourneyBlocks.storonOre, JourneyItems.storonIngot, JourneyBlocks.storonBlock, JourneyItems.storonAxe, JourneyItems.storonPickaxe, JourneyItems.storonShovel, JourneyItems.storonHoe, JourneyItems.storonSword, JourneyItems.storonMultiTool, null, null, null, null, null);
		addOre(JourneyBlocks.mekyumOre, JourneyItems.mekyumIngot, JourneyBlocks.mekyumBlock, JourneyItems.mekyumAxe, JourneyItems.mekyumPickaxe, JourneyItems.mekyumShovel, JourneyItems.mekyumHoe, JourneyItems.mekyumSword, JourneyItems.mekyumMultiTool, null, null, null, null, null);
		//addOre(b.ashualOre, i.ash, b.ashualBlock, null, null, null, null, null, null, null, null, null, null, i.ashDust);
	}
	
	public static void addBlock(Block made, Item used) {
		GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addBlock(Block made, Block used) {
		GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addOPFood(Item nonOP, Item OP, Item base) {
		GameRegistry.addRecipe(new ItemStack(nonOP), new Object[] {"iii", "ibi", "iii", 'i', Items.gold_ingot, 'b', base});
		GameRegistry.addRecipe(new ItemStack(OP), new Object[] {"iii", "ibi", "iii", 'i', Blocks.gold_block, 'b', base});

	}

	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		addAxe(axe, ingot);
		addPickaxe(pick, ingot);
		addShovel(shovel, ingot);
		addHoe(hoe, ingot);
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"b", "b", "s", 'b', block, 's', Items.stick});
		addBlock(block, ingot);
		addHelmet(helmet, ingot);
		addChestplate(chest, ingot);
		addLeggings(legs, ingot);
		addBoots(boots, ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(multiTool), new Object[] {pick, shovel, hoe, axe});
		GameRegistry.addShapelessRecipe(new ItemStack(ingot, 9), new Object[] {block});
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
	}

	private static void addWood(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
		GameRegistry.addShapelessRecipe(new ItemStack(plank, 4), new Object[] {log});
		GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] {"i  ", "ii ", "iii", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Items.stick, 4), new Object[] {"i", "i", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table), new Object[] {"ii", "ii", 'i', plank});
		//GameRegistry.addRecipe(new ItemStack(EssenceBlocks.halfSlab, 6, slabMeta), new Object[] {"iii", 'i', plank});
		if(smelt) GameRegistry.addSmelting(log, new ItemStack(Items.coal), 0.5F);
	}

	private static void addAxe(Item axe, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] {" ii", " si", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addPickaxe(Item pick, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(pick), new Object[] {"iii", " s ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addShovel(Item shovel, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {" i ", " s ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addSword(Item sword, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {" i ", " i ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addHoe(Item hoe, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {" ii", " s ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addHelmet(Item helmet, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(helmet), new Object[] {"iii", "i i", 'i', ingot});
	}

	private static void addChestplate(Item chest, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(chest), new Object[] {"i i", "iii", "iii", 'i', ingot});
	}

	private static void addLeggings(Item legs, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(legs), new Object[] {"iii", "i i", "i i", 'i', ingot});
	}

	private static void addBoots(Item boots, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(boots), new Object[] {"i i", "i i", 'i', ingot});
	}*/
}