package net.journey.integration.jei.info;

import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class JEIIngredientInfo {

	public static void init(IModRegistry registry) {
		registry.addIngredientInfo(new ItemStack(JourneyItems.ancientEyeOfOpening), VanillaTypes.ITEM, I18n.format("jei.jitl.item.ancient_eye.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece1), VanillaTypes.ITEM, I18n.format("jei.jitl.item.ancient_fragment.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece2), VanillaTypes.ITEM, I18n.format("jei.jitl.item.ancient_fragment.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece3), VanillaTypes.ITEM, I18n.format("jei.jitl.item.ancient_fragment.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.ancientPiece4), VanillaTypes.ITEM, I18n.format("jei.jitl.item.ancient_fragment.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.ANCIENT_OBELISK), VanillaTypes.ITEM, I18n.format("jei.jitl.block.ancient_obelisk.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.ANCIENT_SOCKET), VanillaTypes.ITEM, I18n.format("jei.jitl.block.ancient_socket.desc"));

		registry.addIngredientInfo(new ItemStack(JourneyBlocks.sapphireOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_sapphire.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.shadiumOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_shadium.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.luniumOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_lunium.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.iridiumOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_iridium.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.hellstoneOre), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_hellstone.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.bloodRock), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_bloodrock.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.bleedstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_bleedstone.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyBlocks.smithstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_smithstone.desc"));

		registry.addIngredientInfo(new ItemStack(JourneyItems.sapphire), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_sapphire.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.shadiumIngot), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_shadium.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.luniumIngot), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_lunium.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.iridium), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_iridium.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.hellstoneIngot), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_hellstone.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.bleedstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_bleedstone.desc"));
		registry.addIngredientInfo(new ItemStack(JourneyItems.smithstone), VanillaTypes.ITEM, I18n.format("jei.jitl.ore_smithstone.desc"));

		registry.addIngredientInfo(new ItemStack(JourneyItems.flamingSprocket), VanillaTypes.ITEM, I18n.format("jei.jitl.item.flaming_sprocket.desc"));
	}
}
