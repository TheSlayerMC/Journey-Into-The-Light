package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.common.block.base.JBlock;
import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ItemEventHandler {

    @SubscribeEvent
    public static void itemUsedEvent(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        Block blockUsedOn = event.getWorld().getBlockState(event.getPos()).getBlock();
        boolean isHoe = player.getMainHandItem().getItem() instanceof HoeItem && player.getMainHandItem().getItem() != null;
        boolean usedOnEucaSoil = blockUsedOn == JBlocks.EUCA_GOLD_GRASS_BLOCK || blockUsedOn == JBlocks.GOLDITE_DIRT || blockUsedOn == JBlocks.GOLDITE_GRASS_BLOCK;
        if(isHoe && usedOnEucaSoil) {
            event.getWorld().setBlock(event.getPos(), JBlocks.GOLDITE_FARMLAND.defaultBlockState(), 0);
        }
    }
}
