package net.journey.entity.mob.frozen.npc;

import net.journey.JourneyItems;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityFrozenMerchant extends EntityModVillager {

	public EntityFrozenMerchant(World var1) {
		super(var1);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "elf.welcome");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "elf.hello");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "elf.good");
			break;
		}
	}

	@Override
	public int guiID() {
		return GuiHandler.frozenMerchant;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 15), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyItems.frostySword, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 15), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyItems.frostyBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 10), new ItemStack(JourneyItems.frostGem, 10), new ItemStack(JourneyItems.frostyPiercer, 15)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 15), new ItemStack(JourneyItems.frostySword, 1), new ItemStack(JourneyItems.frostbittenSword, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 15), new ItemStack(JourneyItems.frostyBow, 1), new ItemStack(JourneyItems.frostbittenBow, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 10), new ItemStack(JourneyItems.frostyPiercer, 10), new ItemStack(JourneyItems.frostbittenPiercer, 15)));
		
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyItems.crystalFlakeHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyItems.crystalFlakeChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyItems.crystalFlakeLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.crystalFlake, 4), new ItemStack(JourneyItems.frostGem, 15), new ItemStack(JourneyItems.crystalFlakeBoots, 1)));
		
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyItems.crystalFlakeHelmet), new ItemStack(JourneyItems.frostbittenHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyItems.crystalFlakeChest), new ItemStack(JourneyItems.frostbittenChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyItems.crystalFlakeLegs), new ItemStack(JourneyItems.frostbittenLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.frostFlake, 4), new ItemStack(JourneyItems.crystalFlakeBoots), new ItemStack(JourneyItems.frostbittenBoots, 1)));
		
	}
}