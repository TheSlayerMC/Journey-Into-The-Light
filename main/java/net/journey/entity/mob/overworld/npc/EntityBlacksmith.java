package net.journey.entity.mob.overworld.npc;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityBlacksmith extends EntityModVillager {

	public EntityBlacksmith(World var1) {
		super(var1);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "smith.valuables");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "smith.greetings");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "smith.deals");
			break;
		}
	}
	
	@Override
	public ItemStack getHeldItem(EnumHand hand) {
		return new ItemStack(JourneyWeapons.dragonsTooth);
	}

	@Override
	public int guiID() {
		return GuiHandler.blacksmith;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(Items.STICK, 10), new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyWeapons.dawnBreaker)));
		list.add(new MerchantRecipe(new ItemStack(Items.STICK, 10), new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyWeapons.tempestBattleaxe)));
		list.add(new MerchantRecipe(new ItemStack(Items.STICK, 10), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyWeapons.dragonsTooth)));
		list.add(new MerchantRecipe(new ItemStack(Items.STICK, 10), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyWeapons.poisonSword)));
		list.add(new MerchantRecipe(new ItemStack(Items.STICK, 10), new ItemStack(JourneyItems.blueGem, 64), new ItemStack(JourneyWeapons.cloudSlicer)));
		list.add(new MerchantRecipe(new ItemStack(Items.STICK, 10), new ItemStack(JourneyItems.yellowGem, 10), new ItemStack(JourneyWeapons.backBiter)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.blueGem, 10), new ItemStack(JourneyItems.yellowGem, 10), new ItemStack(JourneyWeapons.sunsetPiercer, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.blueGem, 10), new ItemStack(JourneyItems.yellowGem, 10), new ItemStack(JourneyWeapons.aquaticKnife, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyWeapons.poisonBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyItems.yellowGem, 10), new ItemStack(JourneyWeapons.darknessBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyItems.blueGem, 10), new ItemStack(JourneyWeapons.frozenBow, 1)));
	}
}