package net.journey.event;

import net.journey.JourneyItems;

import java.util.Random;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.PlayerHelper;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockModBush;

public class PlayerEvent {

	public static double rand;
	
	@SubscribeEvent
	public void onBlockHarvested(HarvestDropsEvent event) {
		EntityPlayer p = event.harvester;
		boolean isWorking = false;
		if(hasItemEnchantment(JITL.hotTouch, p)) isWorking = true;
		if(isWorking){
			if(event.harvester != null && event.harvester instanceof EntityPlayer && event.harvester.getHeldItem() != null) {
				if(!event.isSilkTouching){
					ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.state.getBlock()));
					if(stack != null && event.state.getBlock() != Blocks.redstone_ore && event.state.getBlock() != Blocks.lapis_ore && event.state.getBlock() != Blocks.lapis_ore) {
						event.drops.clear();
						event.drops.add(stack.copy());
					}
				}
			}
		}
		if(event.harvester != null && event.harvester instanceof EntityPlayer && event.harvester.getHeldItem() != null && event.harvester.getHeldItem().getItem() == JourneyItems.multiToolOfEternalSmelting) {
			if(!event.isSilkTouching){
				ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.state.getBlock()));
				if(stack != null && event.state.getBlock() != Blocks.redstone_ore && event.state.getBlock() != Blocks.lapis_ore && event.state.getBlock() != Blocks.lapis_ore) {
					event.drops.clear();
					event.drops.add(stack.copy());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLogged(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			NBTTagCompound nbt = PlayerHelper.getPersistedpTag(player);
			if(!event.world.isRemote) {
				player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
				if(nbt.hasKey("health")) {
					player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(nbt.getDouble("health"));
				}
			}
		}
	}
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.source.getDamageType().equals("player")) {
			PlayerEvent.rand = Math.random();
			if (event.entityLiving instanceof EntityGhast) {
				if (PlayerEvent.rand < 3){ 
					event.entityLiving.dropItem(JourneyItems.ghastTentacle, 1);
				}
			}
		}
	}
	
	/* @SubscribeEvent
	public void bonemealUsed(BonemealEvent event, BlockPos pos) {
		if(event.world.getBlockState(new BlockPos(event.pos.getX(), event.pos.getY(), event.pos.getZ())) == JourneyBlocks.sizzleberryBush) {
			((BlockModBush)JourneyBlocks.sizzleberryBush)
		}
	} */

	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		int i = MathHelper.floor_double(player.posX);
		int j = MathHelper.floor_double(player.posY);
		int k = MathHelper.floor_double(player.posZ);
		Material m = event.player.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial();
		boolean mat = (m == Material.water);
		boolean isWorking = false;
		if(hasArmorEnchantment(JITL.waterWalk, player)) isWorking = true;
		if(isWorking) {
			if(mat && player.motionY < 0.0D) {
				if(player.worldObj.getBlockState(new BlockPos(i, j - 1, k)).getBlock().getMaterial() == Material.water || player.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.water) player.motionY = 0.0D;
				if(!Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) player.motionY = 0.0D; 
				else if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) player.motionY = 0.5D;
			}
		}
	}

	public static int getItemEnchantment(Enchantment en, EntityLivingBase e) {
		if(en != null && e != null) return EnchantmentHelper.getEnchantmentLevel(en.effectId, e.getHeldItem());
		else return 0;
	}

	public static boolean hasItemEnchantment(Enchantment en, EntityLivingBase e) {
		return getItemEnchantment(en, e) > 0;
	}

	public static int getArmorEnchantment(Enchantment en, EntityLivingBase e) {
		if(en != null && e != null) return EnchantmentHelper.getMaxEnchantmentLevel(en.effectId, e.getInventory());
		else return 0;
	}

	public static boolean hasArmorEnchantment(Enchantment en, EntityLivingBase e) {
		return getArmorEnchantment(en, e) > 0;
	}
}