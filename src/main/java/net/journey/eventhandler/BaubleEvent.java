package net.journey.eventhandler;

import baubles.api.BaublesApi;
import net.journey.JITL;
import net.journey.init.items.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = JITL.MOD_ID)
public class BaubleEvent {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        EntityLiving entity = (EntityLiving) event.getSource().getTrueSource();

        if(!event.getEntity().world.isRemote) {
            if(event.getEntity() instanceof EntityPlayer) {
                if(BaublesApi.isBaubleEquipped((EntityPlayer)event.getEntity(), JourneyItems.skullOfDecay) != -1) {
                    if(event.getSource().getTrueSource() instanceof EntityLiving) {
                        entity.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 400, 1));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
        Random rand = new Random();
        EntityPlayer player = event.getHarvester();
        IBlockState state = event.getState();
        List<ItemStack> drops = event.getDrops();
        if (player != null && !player.world.isRemote) {
            if(BaublesApi.isBaubleEquipped(player, JourneyItems.luckyCharm) != -1) {
                if(rand.nextInt(2) == 0) {
                    if(state.getBlock() instanceof IGrowable) {
                        drops.add(new ItemStack(state.getBlock().getItemDropped(state, rand, 0), 1));
                    }
                }
            }
        }
    }
}
