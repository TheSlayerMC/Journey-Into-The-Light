package net.journey.eventhandler;

import net.journey.JITL;
import net.journey.items.bows.ItemModBow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = JITL.MOD_ID)
public class BowZoomEvent {

	/**
	 * <p>
	 * Used on using custom bow and changing the FOV to vanilla style
	 * </p>
	 * @param event
	 */	
	@SubscribeEvent
    public void FOVUpdate(FOVUpdateEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            if(event.getEntity().isHandActive()  && event.getEntity().getActiveItemStack().getItem() instanceof ItemModBow) {
                ItemModBow bow = (ItemModBow) event.getEntity().getActiveItemStack().getItem();
                int duration = event.getEntity().getItemInUseMaxCount();
                float time = ItemModBow.DEFAULT_MAX_USE_DURATION / bow.getMaxItemUseDuration(null);
                float fovModifier = getNewFov(duration, time);
                float FOV = 1.0F;
                FOV *= 1.0F - fovModifier * 0.15F;
                event.setNewfov(FOV);
            }
        }
    }

    private float getNewFov(int itemInUseDuration, float timeRatio) {
        float fov = ((float) itemInUseDuration / 20.0F) * timeRatio;
        if(fov > 1.0F) fov = 1.0F;
        else fov *= fov;
        return fov;
    }
}