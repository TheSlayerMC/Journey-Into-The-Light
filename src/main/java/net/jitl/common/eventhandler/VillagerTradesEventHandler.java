package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.common.entity.base.trades.MapTrade;
import net.jitl.init.JStructures;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class VillagerTradesEventHandler {

    @SubscribeEvent()
    public static void addTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CARTOGRAPHER) {
            List<VillagerTrades.ITrade> level5_trades = event.getTrades().get(5);
            level5_trades.add(new MapTrade(Items.EMERALD, 15, Items.COMPASS, 1, JStructures.ILlAGER_BUNKER.getStructure(), MapDecoration.Type.BANNER_LIGHT_GRAY, 12, 5));
            //trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(JItems.SAPPHIRE, 1), 1, 1, 0));
        }
    }
}
