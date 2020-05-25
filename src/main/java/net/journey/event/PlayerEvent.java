package net.journey.event;

import java.util.List;
import java.util.Random;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.util.JourneyLootTables;
import net.journey.util.LootHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.PlayerHelper;
import net.slayer.api.item.ItemModShovel;

public class PlayerEvent {

	public double rand;
	public Random random;

	public PlayerEvent() {
		this.rand = 0;
		this.random = new Random();
	}
	
	@SubscribeEvent
	public void onBlockClicked(PlayerInteractEvent event) {
		EntityPlayer p = event.getEntityPlayer();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if(event.getEntityPlayer() !=null && event.getEntityPlayer().getHeldItemMainhand().getDisplayName().toString().contains("Shovel")) {
			if(world.getBlockState(pos) == JourneyBlocks.corbaGrass) {
				world.setBlockState(pos, JourneyBlocks.ashBlock.getDefaultState(), 2);
			}
		}
	}

	@SubscribeEvent
	public void onBlockHarvested(HarvestDropsEvent event) {
		EntityPlayer p = event.getHarvester();
		ItemStack helmet;
		if(event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer && event.getHarvester().getHeldItemMainhand() != null) {
			if(!event.isSilkTouching()) {
				if(event.getHarvester().getHeldItemMainhand().getItem() == JourneyArmory.multiToolOfEternalSmelting) {
					ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
					if(stack != null && event.getState().getBlock() != Blocks.REDSTONE_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE) {
						event.getDrops().clear();
						event.getDrops().add(stack.copy());
					}
				}
				if(event.getHarvester().getHeldItemMainhand().getItem() == JourneyArmory.SLIMY_PICKAXE) {
					for(ItemStack s : event.getDrops()) {
						EntityItem item = new EntityItem(event.getWorld(), p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
						item.setItem(s);
						event.getWorld().spawnEntity(item);
						event.getDrops().clear();
					}
				}
				helmet = p.inventory.armorInventory.get(3);
				if(helmet.getItem() == JourneyArmory.MASK_OF_HELLMETAL) {
					ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
					if(stack != null) {
						event.getDrops().clear();
						event.getDrops().add(stack.copy());
						helmet.damageItem(1, p);
					} else if (stack == null) {
						event.getDrops();
					}
				}
				if (event.getHarvester().getHeldItemMainhand().getItem() == JourneyArmory.HOE_OF_EARTH_LOVING) {
					List<ItemStack> i = LootHelper.readFromLootTable(JourneyLootTables.LOOT_SEEDS, (EntityPlayerMP)event.getHarvester());
					int index = random.nextInt(i.size()); 
					Item it = i.get(index).getItem();
					if(event.getState().getBlock().getRegistryName().toString().contains("grass")) {
						if(random.nextInt(3) == 0) 
							event.getDrops().add(new ItemStack(it));
					}
				}

				if(event.getHarvester().getHeldItemMainhand().getItem() == JourneyArmory.PICKAXE_OF_GOOD_FORTUNE) {

					List<ItemStack> i = LootHelper.readFromLootTable(JourneyLootTables.LOOT_POUCH, (EntityPlayerMP)event.getHarvester()); // make new loot table
					int index = random.nextInt(i.size()); 
					Item it = i.get(index).getItem();

					if(event.getState().getBlock().getRegistryName().toString().contains("ore")) {
						if(random.nextInt(3) == 0) 
							event.getDrops().add(new ItemStack(it));// make it spawn only 1 item not 2 and add it so its only a chance
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLogged(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			NBTTagCompound nbt = PlayerHelper.getPersistedpTag(player);
			if (!event.getWorld().isRemote) {
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
				if (nbt.hasKey("health")) {
					player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(nbt.getDouble("health"));
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.getSource().getDamageType().equals("player")) {
			this.rand = Math.random();
			if (event.getEntityLiving() instanceof EntityGhast) {
				if (this.rand < 3) {
					event.getEntityLiving().dropItem(JourneyConsumables.ghastTentacle, 1);
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
}