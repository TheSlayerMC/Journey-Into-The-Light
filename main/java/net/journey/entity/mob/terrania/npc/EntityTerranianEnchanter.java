package net.journey.entity.mob.terrania.npc;

import net.journey.JourneyArmory;
import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.client.GuiHandler;
import net.journey.entity.JourneyMerchantRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityTerranianEnchanter extends EntityModVillager {

	public EntityTerranianEnchanter(World var1) {
		super(var1);
		setSize(2.0F, 4.0F);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "I infuse my weapons with the best magic in the land!");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "You are a very strange looking creature. And short, too.");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "This place has the most magic out of any other realm known.");
			break;
		}
	}

	@Override
	public int guiID() {
		return GuiHandler.terranianEnchanter;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.slugSlime, 64), new ItemStack(JourneyItems.earthenCrystal, 4), new ItemStack(JourneyWeapons.terrolicaSword)));
		list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.purplePowder, 16), new ItemStack(JourneyItems.earthenCrystal, 4), new ItemStack(JourneyWeapons.terralightBow)));
		list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.purplePowder, 4), new ItemStack(JourneyItems.earthenCrystal, 1), new ItemStack(JourneyArmory.enlightenerHelmet)));
		list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.purplePowder, 4), new ItemStack(JourneyItems.earthenCrystal, 1), new ItemStack(JourneyArmory.enlightenerChest)));
		list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.purplePowder, 4), new ItemStack(JourneyItems.earthenCrystal, 1), new ItemStack(JourneyArmory.enlightenerLegs)));
		list.add(new JourneyMerchantRecipe(new ItemStack(JourneyItems.purplePowder, 4), new ItemStack(JourneyItems.earthenCrystal, 1), new ItemStack(JourneyArmory.enlightenerBoots)));
	}
}