package net.journey.entity.mob.cloudia.npc;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityStarlightBlacksmith extends EntityModVillager {

	public EntityStarlightBlacksmith(World par1World) {
		super(par1World);
		setSize(1.7F, 2.5F);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Blacksmith: You're a tiny creature, aren't you?");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Blacksmith: I have some great weapons. Is there anything you'd like in particular?");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Blacksmith: I always fear that I'll fall off the edge one day.");
			break;
		}
		
	}

	@Override
	public int guiID() {
		return GuiHandler.starlightBlacksmith;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.fluffyFeather, 15), new ItemStack(JourneyItems.golemChunk, 5), new ItemStack(JourneyWeapons.golemSword, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.fluffyFeather, 15), new ItemStack(JourneyItems.golemChunk, 5), new ItemStack(JourneyWeapons.golemBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.luniteChunk, 15), new ItemStack(JourneyItems.golemChunk, 15), new ItemStack(JourneyWeapons.starlightBlade, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.luniteChunk, 15), new ItemStack(JourneyItems.golemChunk, 15), new ItemStack(JourneyWeapons.starlightBow, 1)));
		
	}
}