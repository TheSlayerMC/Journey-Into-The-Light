package net.journey.entity.mob.overworld.npc;

import net.journey.JourneyItems;
import net.journey.client.GuiHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityMage extends EntityModVillager {

	public EntityMage(World var1) {
		super(var1);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "mage.valuables");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "mage.greetings");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "mage.deals");
			break;
		}
	}
	
	@Override
	public ItemStack getHeldItem() {
		return new ItemStack(JourneyItems.conjuringStaff);
	}

	@Override
	public int guiID() {
		return GuiHandler.mage;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(Items.stick, 1), new ItemStack(Items.gold_ingot, 5), new ItemStack(JourneyItems.wandBase)));
		list.add(new MerchantRecipe(new ItemStack(Items.stick, 1), new ItemStack(Items.diamond, 2), new ItemStack(JourneyItems.staffBase)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.stoneStick, 1), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyItems.earthenHammer)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyItems.staffOfGreenpace)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.hellstoneIngot, 10), new ItemStack(JourneyItems.staffOfHellstone)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.purpleGem, 10), new ItemStack(JourneyItems.doomsBringer)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.yellowGem, 10), new ItemStack(JourneyItems.wizardsStar)));
		//list.add(new MerchantRecipe(new ItemStack(EssenceItems.staffBase, 1), new ItemStack(EssenceItems.hellstoneIngot, 5), new ItemStack(EssenceItems.chaosCannon)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.staffBase, 1), new ItemStack(JourneyItems.blueGem, 10), new ItemStack(JourneyItems.staffOfEnlightenment)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.greenGem, 10), new ItemStack(JourneyItems.staffOfGreenpace, 1), new ItemStack(JourneyItems.conjuringStaff)));		
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.wandBase, 1), new ItemStack(Blocks.ice, 32), new ItemStack(JourneyItems.iceWand)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.wandBase, 1), new ItemStack(Items.fire_charge, 32), new ItemStack(JourneyItems.fireWand)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.wandBase, 1), new ItemStack(Items.diamond, 32), new ItemStack(JourneyItems.lightningWand)));
	}
}