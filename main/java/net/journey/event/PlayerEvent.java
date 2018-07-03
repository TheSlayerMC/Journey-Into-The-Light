package net.journey.event;

import net.journey.JITL;
import net.journey.JourneyItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.slayer.api.PlayerHelper;

public class PlayerEvent {

	public static double rand;
	
	@SubscribeEvent
	public void onBlockHarvested(HarvestDropsEvent event) {
		EntityPlayer p = event.getHarvester();
		boolean isWorking = false;
		if(hasItemEnchantment(JITL.hotTouch, p)) isWorking = true;
		if(isWorking){
			if(event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer && event.getHarvester().getHeldItemMainhand() != null) {
				if(!event.isSilkTouching()){
					ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
					if(stack != null && event.getState().getBlock() != Blocks.REDSTONE_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE) {
						event.getDrops().clear();
						event.getDrops().add(stack.copy());
					}
				}
			}
		}
		if(event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer && event.getHarvester().getHeldItemMainhand() != null && event.getHarvester().getHeldItemMainhand().getItem() == JourneyItems.multiToolOfEternalSmelting) {
			if(!event.isSilkTouching()){
				ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
				if(stack != null && event.getState().getBlock() != Blocks.REDSTONE_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE) {
					event.getDrops().clear();
					event.getDrops().add(stack.copy());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLogged(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			NBTTagCompound nbt = PlayerHelper.getPersistedpTag(player);
			if(!event.getWorld().isRemote) {
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
				if(nbt.hasKey("health")) {
					player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(nbt.getDouble("health"));
				}
			}
		}
	}
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.getSource().getDamageType().equals("player")) {
			PlayerEvent.rand = Math.random();
			if (event.getEntityLiving() instanceof EntityGhast) {
				if (PlayerEvent.rand < 3){ 
					event.getEntityLiving().dropItem(JourneyItems.ghastTentacle, 1);
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
		int i = MathHelper.floor(player.posX);
		int j = MathHelper.floor(player.posY);
		int k = MathHelper.floor(player.posZ);
		Material m = event.player.world.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial(null);
		boolean mat = (m == Material.WATER);
		boolean isWorking = false;
		if(hasArmorEnchantment(JITL.waterWalk, player)) isWorking = true;
		if(isWorking) {
			if(mat && player.motionY < 0.0D) {
				if(player.world.getBlockState(new BlockPos(i, j - 1, k)).getBlock().getMaterial(null) == Material.WATER || player.world.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial(null) == Material.WATER) player.motionY = 0.0D;
				if(!Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) player.motionY = 0.0D; 
				else if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) player.motionY = 0.5D;
			}
		}
	}

	public static int getItemEnchantment(Enchantment en, EntityLivingBase e) {
		if(en != null && e != null) return EnchantmentHelper.getEnchantmentLevel(en, e.getHeldItemMainhand());
		else return 0;
	}

	public static boolean hasItemEnchantment(Enchantment en, EntityLivingBase e) {
		return getItemEnchantment(en, e) > 0;
	}

	public static int getArmorEnchantment(Enchantment en, EntityLivingBase e) {
		if(en != null && e != null) return EnchantmentHelper.getEnchantmentLevel(en, e.getHeldItemMainhand());
		else return 0;
	}

	public static boolean hasArmorEnchantment(Enchantment en, EntityLivingBase e) {
		return getArmorEnchantment(en, e) > 0;
	}
}