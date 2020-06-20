package net.journey.eventhandler;

import baubles.api.BaublesApi;
import net.journey.JITL;
import net.journey.init.items.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = JITL.MOD_ID)
public class BaubleEvent {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        if(!event.getEntity().world.isRemote) {
            if(event.getEntity() instanceof EntityPlayer) {
                if(BaublesApi.isBaubleEquipped((EntityPlayer)event.getEntity(), JourneyItems.skullOfDecay) != -1) {
                    if(event.getSource().getTrueSource() instanceof EntityLiving) {
                        EntityLiving entity = (EntityLiving) event.getSource().getTrueSource();
                        entity.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 400, 1));
                    }
                }
            }
        }
    }
}
