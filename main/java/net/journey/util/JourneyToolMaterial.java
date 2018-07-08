package net.journey.util;

import net.journey.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.slayer.api.SlayerAPI;

public enum JourneyToolMaterial {

	HELLSTONE_SWORD(JourneyItems.hellstoneSwordMat, JourneyItems.hellstoneIngot),
	FLAIRIUM_SWORD(JourneyItems.flairiumSwordMat, JourneyItems.flairiumIngot),
	LUNIUM_SWORD(JourneyItems.luniumSwordMat, JourneyItems.luniumIngot),
	CELESTIUM_SWORD(JourneyItems.celestiumSwordMat, JourneyItems.celestiumIngot),
	SHADIUM_SWORD(JourneyItems.shadiumSwordMat, JourneyItems.shadiumIngot),
	SAPPHIRE_SWORD(JourneyItems.sapphiretoolSwordMat, JourneyItems.sapphire),
	ORBADITE_SWORD(JourneyItems.orbaditeSwordMat, JourneyItems.orbaditeIngot),
	GORBITE_SWORD(JourneyItems.gorbiteSwordMat, JourneyItems.gorbiteGem),
	DES_SWORD(JourneyItems.desSwordMat, JourneyItems.desIngot),
	
	HELLSTONE(JourneyItems.hellstone, JourneyItems.hellstoneIngot, 12),
	FLAIRIUM(JourneyItems.flairium, JourneyItems.flairiumIngot, 16),
	LUNIUM(JourneyItems.lunium, JourneyItems.luniumIngot, 10),
	CELESTIUM(JourneyItems.celestium, JourneyItems.celestiumIngot, 14),
	SHADIUM(JourneyItems.shadium, JourneyItems.shadiumIngot, 10),
	SAPPHIRE(JourneyItems.sapphiretool, JourneyItems.sapphire, 8),
	ORBADITE(JourneyItems.orbadite, JourneyItems.orbaditeIngot, 148),
	GORBITE(JourneyItems.gorbite, JourneyItems.gorbiteGem, 148),
	DES(JourneyItems.des, JourneyItems.desIngot, 26),
	KORITE(JourneyItems.korite, JourneyItems.koriteIngot, 14),
	
	NETHER_BEAST_SWORD(SlayerAPI.addMeleeMaterial(3000, 16, 25)),
	WITHERING_BEAST_SWORD(SlayerAPI.addMeleeMaterial(3000, 16, 25)),
	CALCIA_SWORD(SlayerAPI.addMeleeMaterial(3000, 13, 25)),
	CHAMPIONS_SWORD(SlayerAPI.addMeleeMaterial(3000, 14, 25)),
	THE_WRAITH(SlayerAPI.addMeleeMaterial(3000, 17, 25)),
	BUBBLE_SWORD(SlayerAPI.addMeleeMaterial(3000, 23, 25)),

	POISON_SWORD(SlayerAPI.addMeleeMaterial(1500, 10, 25)),
	CLOUD_SLICER(SlayerAPI.addMeleeMaterial(1500, 12, 25)),
	DRAGONS_TOOTH(SlayerAPI.addMeleeMaterial(1500, 13, 25)),
	BOILING_BLADE(SlayerAPI.addMeleeMaterial(3000, 14, 25)),
	MOLTEN_KNIFE(SlayerAPI.addMeleeMaterial(3000, 10, 25)),
	LOGGERS_SWORD(SlayerAPI.addMeleeMaterial(3000, 30, 25)),
	NATURES_BLADE(SlayerAPI.addMeleeMaterial(3000, 34, 25)),
	DEPTHS_DARKSWORD(SlayerAPI.addMeleeMaterial(3000, 28, 25)),
	DEPTHS_SLAYER(SlayerAPI.addMeleeMaterial(3000, 22, 25)),
	SNOW_SHOVELER(SlayerAPI.addMeleeMaterial(3000, 15, 25)),
	FROSTBITTEN_SWORD(SlayerAPI.addMeleeMaterial(3000, 15, 25)),
	FROSTY_SWORD(SlayerAPI.addMeleeMaterial(3000, 13, 25)),
	TREE_HUGGER(SlayerAPI.addMeleeMaterial(3000, 32, 25)),
	HEALERS_BLADE(SlayerAPI.addMeleeMaterial(3000, 15, 25)),
	CORE_MENDER(SlayerAPI.addMeleeMaterial(3000, 26, 25)),
	ROYAL_BLADE(SlayerAPI.addMeleeMaterial(3000, 18, 25)),
	ROYAL_STABBER(SlayerAPI.addMeleeMaterial(3000, 21, 25)),
	ROC_SWORD(SlayerAPI.addMeleeMaterial(3000, 29, 25)),
	SWORD_THUNDERBIRD(SlayerAPI.addMeleeMaterial(3000, 32, 25)),
	BLOODWIELD_SWORD(SlayerAPI.addMeleeMaterial(3000, 14, 25)),
	CHARRED_BLADE(SlayerAPI.addMeleeMaterial(3000, 18, 25)),
	SIZZLER_SWORD(SlayerAPI.addMeleeMaterial(3000, 22, 25)),
	FLUFFY_BLADE(SlayerAPI.addMeleeMaterial(3000, 40, 25)),
	GOLEM_SWORD(SlayerAPI.addMeleeMaterial(3000, 38, 25)),
	THUNDERBLADE(SlayerAPI.addMeleeMaterial(3000, 35, 25)),
	SENTRY_SWORD(SlayerAPI.addMeleeMaterial(3000, 35, 25)),
	CRYSTAL_BLADE(SlayerAPI.addMeleeMaterial(1690, 9, 25)),
	STARLIGHT_BLADE(SlayerAPI.addMeleeMaterial(3000, 38, 25)),
	KORITE_SWORD(SlayerAPI.addMeleeMaterial(3000, 12, 25)),
	PEDAL_SWORD(SlayerAPI.addMeleeMaterial(100, 5.5F, 25)),
	RE_CRYSTAL_SWORD(SlayerAPI.addMeleeMaterial(512, 9.5F, 25)),
	RE_STONE_SWORD(SlayerAPI.addMeleeMaterial(256, 6.5F, 25)),
	WITHIC_BLADE(SlayerAPI.addMeleeMaterial(70, 12, 25)),
	TERRALIGHT_BLADE(SlayerAPI.addMeleeMaterial(3500, 35, 25)),
	TERRANA_SWORD(SlayerAPI.addMeleeMaterial(3400, 34, 25)),
	TERROLICA_SWORD(SlayerAPI.addMeleeMaterial(3500, 36, 25)),
	VOLITE_SWORD(SlayerAPI.addMeleeMaterial(3500, 35, 25)),
	TERRONIC_BLADE(SlayerAPI.addMeleeMaterial(1000, 34, 25)),
	KINGS_SWORD(SlayerAPI.addMeleeMaterial(1500, 20, 25)),
	VINESTRAND_BLADE(SlayerAPI.addMeleeMaterial(2000, 30, 25)),
	DARK_PINE_SWORD(SlayerAPI.addMeleeMaterial(2000, 31, 25)),
	DEMONIC_SWORD(SlayerAPI.addMeleeMaterial(850, 5.5F, 25)),
	
	CREATIVE(SlayerAPI.addMeleeMaterial(1000, 39, 25)),
	EARTHEN_HAMMER(SlayerAPI.addMeleeMaterial(1, 7, 25)),
	FLAMING_HAMMER(SlayerAPI.addMeleeMaterial(1000, 10, 25)),
	NETHIC_HAMMER(SlayerAPI.addMeleeMaterial(1000, 8, 25)),
	WITHIC_HAMMER(SlayerAPI.addMeleeMaterial(1000, 9, 25)),
	ROYAL_HAMMER(SlayerAPI.addMeleeMaterial(1000, 11, 25)),
	OVERGROWN_HAMMER(SlayerAPI.addMeleeMaterial(1000, 16, 25)),
	ROCKY_HAMMER(SlayerAPI.addMeleeMaterial(2230, 8, 25)),
	CRYSTAL_HAMMER(SlayerAPI.addMeleeMaterial(3320, 9, 25)),
	
	DEVELOPER_SWORD(SlayerAPI.addMeleeMaterial(3000, 9000, 25)),
	
	HELLSTONE_MULTI_TOOL(JourneyItems.hellstoneMulti, JourneyItems.hellstoneIngot, 3),
	FLAIRIUM_MULTI_TOOL(JourneyItems.flairiumMulti, JourneyItems.flairiumIngot, 3),
	LUNIUM_MULTI_TOOL(JourneyItems.luniumMulti, JourneyItems.luniumIngot, 3),
	CELESTIUM_MULTI_TOOL(JourneyItems.celestiumMulti, JourneyItems.celestiumIngot, 3),
	SHADIUM_MULTI_TOOL(JourneyItems.shadiumMulti, JourneyItems.shadiumIngot, 3),
	SAPPHIRE_MULTI_TOOL(JourneyItems.sapphireMulti, JourneyItems.sapphire, 2),
	ORBADITE_MULTI_TOOL(JourneyItems.orbaditeMulti, JourneyItems.orbaditeIngot, 3),
	GORBITE_MULTI_TOOL(JourneyItems.gorbiteMulti, JourneyItems.gorbiteGem, 3),
	DES_MULTI_TOOL(JourneyItems.desMulti, JourneyItems.desIngot, 3),
	KORITE_MULTI_TOOL(JourneyItems.koriteMulti, JourneyItems.koriteIngot, 3),
	SMELTING_TOOL(JourneyItems.smeltingMulti, null, 3),
	WOOD_MULTI_TOOL(JourneyItems.woodMulti, SlayerAPI.toItem(Blocks.PLANKS), 1),
	STONE_MULTI_TOOL(JourneyItems.stoneMulti, SlayerAPI.toItem(Blocks.COBBLESTONE), 2),
	IRON_MULTI_TOOL(JourneyItems.ironMulti, Items.IRON_INGOT, 3),
	GOLD_MULTI_TOOL(JourneyItems.goldMulti, Items.GOLD_INGOT, 3),
	DIAMOND_MULTI_TOOL(JourneyItems.diamondMulti, Items.DIAMOND, 3),

	CRYSTAL_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 3600, 13, 17, 25)),
	ROCKY_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 2600, 13, 16, 25)),
	BACK_BITER(SlayerAPI.addAxeMaterial(3, 1300, 13, 15, 25)),
	DAWN_BREAKER(SlayerAPI.addAxeMaterial(3, 1300, 13, 10, 25)),
	TEMPEST_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 1300, 13, 13, 25)),
	BRONZED_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 1300, 13, 16, 25)),
	STORUM_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 1300, 13, 17, 25)),
	CELESTITE_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 1300, 13, 18, 25)),
	CELEKIUM_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 1300, 13, 19, 25)),
	THUNDERBIRD_BATTLEAXE(SlayerAPI.addAxeMaterial(3, 1300, 13, 27, 25));

	private ToolMaterial toolMaterial;
	private Item repairItem;
	private int harvestLevel = 0;

	private JourneyToolMaterial(ToolMaterial toolMaterial, Item repair) {
		this.toolMaterial = toolMaterial;
		this.repairItem = repair;
		harvestLevel = 0;
	}
	
	private JourneyToolMaterial(ToolMaterial toolMaterial, Item repair, int level) {
		this.toolMaterial = toolMaterial;
		this.repairItem = repair;
		this.harvestLevel = level;
	}

	private JourneyToolMaterial(ToolMaterial toolMaterial) {
		this(toolMaterial, null);
	}

	public int getHarvestLevel(){
		return harvestLevel;
	}
	
	public Item getRepairItem(){
		return repairItem;
	}

	public ToolMaterial getToolMaterial() {
		return toolMaterial;
	}
}