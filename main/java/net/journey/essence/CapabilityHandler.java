package net.journey.essence;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

public class CapabilityHandler {
	
    public static final ResourceLocation ESSNECE_CAP = new ResourceLocation(SlayerAPI.MOD_ID, "essence");

    @SubscribeEvent
    public void attachCapability(final AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        event.addCapability(ESSNECE_CAP, new EssenceProvider());
    }
}