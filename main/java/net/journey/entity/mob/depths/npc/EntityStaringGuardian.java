package net.journey.entity.mob.depths.npc;

import net.journey.JourneyItems;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityStaringGuardian extends EntityModVillager {

	public EntityStaringGuardian(World var1) {
		super(var1);
	}
	
	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Guardian: It sure is dark down here... I've always speculated about what's on the surface.");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Guardian: You don't look like any normal creature I've seen here before. We don't get a lot of travelers.");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Guardian: It's always too dark here to see. Hopefully, there'll be a journey to the light as some wish.");
			break;
		}
	}

	@Override
	public int guiID() {
		return GuiHandler.staringGuardian;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.darkCrystal, 16), new ItemStack(JourneyItems.depthsSlayer, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.beastlyStomach, 16), new ItemStack(JourneyItems.depthsBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.depthsSlayer, 1), new ItemStack(JourneyItems.depthsDarksword, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.depthsBow, 1), new ItemStack(JourneyItems.darkEnforcer, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.depthsFlake, 15), new ItemStack(JourneyItems.darkCrystal, 16), new ItemStack(JourneyItems.darkKey, 1)));
		
	}
}