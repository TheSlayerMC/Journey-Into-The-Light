package net.jitl.capabilities.currentstructure;

import net.jitl.JITL;
import net.jitl.capabilities.JourneyCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurrentStructureCapability implements ICurrentStructureCapability {
    private int structureId;

    public void setStructure(int id) {
        structureId = id;
    }

    public int getStructure() {
        return structureId;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().level.isClientSide() && event.getObject() instanceof PlayerEntity) {
            event.addCapability(JITL.rl("current_structure"), new JourneyCapabilityProvider());
        }
    }
}
