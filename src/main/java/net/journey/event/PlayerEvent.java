package net.journey.event;

import net.journey.JourneyArmory;
import net.journey.JourneyConsumables;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.PlayerHelper;

public class PlayerEvent {

    public static double rand;

    @SubscribeEvent
    public void onBlockHarvested(HarvestDropsEvent event) {
        EntityPlayer p = event.getHarvester();
        if (event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer && event.getHarvester().getHeldItemMainhand() != null && event.getHarvester().getHeldItemMainhand().getItem() == JourneyArmory.multiToolOfEternalSmelting) {
            if (!event.isSilkTouching()) {
                ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
                if (stack != null && event.getState().getBlock() != Blocks.REDSTONE_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE && event.getState().getBlock() != Blocks.LAPIS_ORE) {
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
            PlayerEvent.rand = Math.random();
            if (event.getEntityLiving() instanceof EntityGhast) {
                if (PlayerEvent.rand < 3) {
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