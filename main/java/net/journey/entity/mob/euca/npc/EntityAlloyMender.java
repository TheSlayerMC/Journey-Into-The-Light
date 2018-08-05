package net.journey.entity.mob.euca.npc;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityAlloyMender extends EntityModVillager {

	public EntityAlloyMender(World var1) {
		super(var1);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Mender: It takes over a thousand degreese to melt this gold!");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Mender: My weapons have been melted and shaped into deadly perfection!");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Mender: It's a real struggle living here, when everything wants to kill you...");
			break;
		}
	}

	@Override
	public int guiID() {
		return GuiHandler.alloyMender;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.shimmerdust, 64), new ItemStack(JourneyItems.metalDisk, 1), new ItemStack(JourneyItems.royalBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.golderDust, 16), new ItemStack(JourneyItems.goldClump, 16), new ItemStack(JourneyItems.royalBlade, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.shimmerdust, 64), new ItemStack(JourneyItems.metalDisk, 2), new ItemStack(JourneyItems.royalHammer, 1)));
		//list.add(new MerchantRecipe(new ItemStack(JourneyItems.golderDust, 10), new ItemStack(JourneyItems.goldClump, 10), new ItemStack(JourneyItems.royalKnife, 16)));
	/*	list.add(new MerchantRecipe(new ItemStack(JourneyBlocks.celestiumBlock, 8), new ItemStack(JourneyBlocks.mekyumBlock, 8), new ItemStack(JourneyItems.celekiumBattleaxe, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyBlocks.celestiumBlock, 7), new ItemStack(JourneyBlocks.koriteBlock, 7), new ItemStack(JourneyItems.celestiteBattleaxe, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyBlocks.storonBlock, 6), new ItemStack(JourneyItems.mekyumIngot, 6), new ItemStack(JourneyItems.storumBattleaxe, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyBlocks.storonBlock, 5), new ItemStack(JourneyItems.koriteIngot, 5), new ItemStack(JourneyItems.bronzedBattleaxe, 1))); */
	}
}