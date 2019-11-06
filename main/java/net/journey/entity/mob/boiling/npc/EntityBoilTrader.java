package net.journey.entity.mob.boiling.npc;

import net.journey.JourneyItems;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityBoilTrader extends EntityModVillager {

	public EntityBoilTrader(World var1) {
		super(var1);
		this.isImmuneToFire = true;

	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Boil Trader: It's not often that newcomers arrive here. Can you stand the heat?");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Boil Trader: I'm an unusual collector, but I have the best deals of any realm!");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Boil Trader: I can see that you're having a tough time getting around. An armor upgrade, perhaps?");
			break;
		}
	}

	@Override
	public int guiID() {
		return GuiHandler.boilTrader;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.ash, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.flamingBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.ash, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.boilingBlade, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.ash, 10), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.moltenKnife, 15)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.charskullHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.charskullChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.charskullLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.boilingSkull, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.charskullBoots, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.snakeskinHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.snakeskinChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.snakeskinLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.snakeFlesh, 15), new ItemStack(JourneyItems.boilPowder, 10), new ItemStack(JourneyItems.snakeskinBoots, 1)));
		
	}
}