package net.journey.eventhandler;

import net.journey.init.JourneyLootTables;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.util.Config;
import net.journey.util.LootHelper;
import net.journey.util.RandHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.PlayerHelper;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class PlayerEventsHandler {

	@SubscribeEvent
	public static void onBlockClicked(PlayerInteractEvent event) {
		EntityPlayer p = event.getEntityPlayer();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if (event.getEntityPlayer() != null && event.getEntityPlayer().getHeldItemMainhand().getDisplayName().contains("Shovel")) {
			if (world.getBlockState(pos) == JourneyBlocks.corbaGrass) {
				world.setBlockState(pos, JourneyBlocks.ashBlock.getDefaultState(), 2);
			}
		}
	}

	@SubscribeEvent
	public static void onBlockHarvested(HarvestDropsEvent event) {
		EntityPlayer p = event.getHarvester();
		IBlockState harvestedState = event.getState();
		List<ItemStack> drops = event.getDrops();
		Random random = RandHelper.RANDOM;
		ItemStack helmet;
		if (p != null && !p.world.isRemote) {
			EntityPlayerMP playerMP = (EntityPlayerMP) p;
			Item heldItem = playerMP.getHeldItemMainhand().getItem();

			if (!event.isSilkTouching()) {
				if (heldItem == JourneyArmory.multiToolOfEternalSmelting) {
					ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(harvestedState.getBlock()));
					if (harvestedState.getBlock() != Blocks.REDSTONE_ORE && harvestedState.getBlock() != Blocks.LAPIS_ORE && harvestedState.getBlock() != Blocks.LAPIS_ORE) {
						drops.clear();
						drops.add(stack.copy());
					}
				}
				if (heldItem == JourneyArmory.SLIMY_PICKAXE) {
					for (ItemStack s : drops) {
						EntityItem item = new EntityItem(event.getWorld(), p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
						item.setItem(s);
						event.getWorld().spawnEntity(item);
						drops.clear();
					}
				}
				helmet = p.inventory.armorInventory.get(3);
				if (helmet.getItem() == JourneyArmory.MASK_OF_HELLMETAL) {
					ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(harvestedState.getBlock()));
					drops.clear();
					drops.add(stack.copy());
					helmet.damageItem(1, p);
				}
				if (heldItem == JourneyArmory.HOE_OF_EARTH_LOVING) {
					List<ItemStack> i = LootHelper.genFromLootTable(JourneyLootTables.LOOT_SEEDS, playerMP, builder -> {
					});
					int index = random.nextInt(i.size());
					Item it = i.get(index).getItem();
					if (harvestedState.getBlock().getRegistryName().toString().contains("grass")) {
						if (random.nextInt(3) == 0)
							drops.add(new ItemStack(it));
					}
				}
				if (heldItem == JourneyArmory.PICKAXE_OF_GOOD_FORTUNE) {

					List<ItemStack> i = LootHelper.genFromLootTable(JourneyLootTables.LOOT_BASIC, playerMP, builder -> {
					}); // make new loot table
					int index = random.nextInt(i.size());
					Item it = i.get(index).getItem();

					if (harvestedState.getBlock().getRegistryName().toString().contains("ore")) {
						if (random.nextInt(3) == 0)
							drops.add(new ItemStack(it));// make it spawn only 1 item not 2 and add it so its only a chance
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLogged(EntityJoinWorldEvent event) {
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
	public static void onEntityDrop(LivingDropsEvent event) {
		Random random = RandHelper.RANDOM;
		EntityLivingBase entity = event.getEntityLiving();

		if (entity instanceof EntityGhast) {
			if (random.nextInt(3) == 0) {
				entity.dropItem(JourneyConsumables.ghastTentacle, 1);
			}
		}

		if (Config.enableLootPouchDrops && event.getSource().getTrueSource() instanceof EntityPlayer) {
			if (random.nextInt(Config.commonLootBagRarity) == 0) {
				entity.dropItem(JourneyItems.LOOT_POUCH, 1);
			}
			if (random.nextInt(Config.goldLootBagRarity) == 0) {
				entity.dropItem(JourneyItems.LOOT_POUCH_GOLD, 1);
			}
			if (random.nextInt(Config.diamondLootBagRarity) == 0) {
				entity.dropItem(JourneyItems.LOOT_POUCH_DIAMOND, 1);
			}
		}
	}
}