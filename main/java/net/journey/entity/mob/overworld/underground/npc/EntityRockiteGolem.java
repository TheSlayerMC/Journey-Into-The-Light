package net.journey.entity.mob.overworld.underground.npc;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.client.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityRockiteGolem extends EntityModVillager {

	public EntityRockiteGolem(World var1) {
		super(var1);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Rockite: What could you possibly need those ingots for? Those are my only food source!");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Rockite: What are you made out of? You don't look like rock to me.");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Rockite: It gets dark down here sometimes. The glowshrooms help a bit, however.");
			break;
		}
	}

	@Override
	public int guiID() {
		return GuiHandler.rockite;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 40.0D && super.getCanSpawnHere() && 
				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.STONE;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedStoneIngot, 4), new ItemStack(JourneyWeapons.backBiter, 1), new ItemStack(JourneyWeapons.rockyBattleaxe, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot, 4), new ItemStack(JourneyWeapons.backBiter, 1), new ItemStack(JourneyWeapons.crystalizedBattleaxe, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedStoneIngot, 4), new ItemStack(JourneyWeapons.earthenHammer, 1), new ItemStack(JourneyWeapons.rockyHammer, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot, 4), new ItemStack(JourneyWeapons.earthenHammer, 1), new ItemStack(JourneyWeapons.crystalizedHammer, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedStoneIngot, 4), new ItemStack(JourneyItems.stoneClump, 1), new ItemStack(JourneyWeapons.rockLauncher, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot, 4), new ItemStack(JourneyWeapons.staffOfEnlightenment, 1), new ItemStack(JourneyWeapons.staffOfDivineStone, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.reinforcedCrystalIngot, 4), new ItemStack(JourneyWeapons.staffOfDivineStone, 1), new ItemStack(JourneyWeapons.staffOfCrystal, 1)));
	}
} 