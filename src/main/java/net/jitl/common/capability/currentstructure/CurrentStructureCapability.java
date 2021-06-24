package net.jitl.common.capability.currentstructure;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CurrentStructureCapability implements ICurrentStructureCapability {
    private int structureId;

    public void setStructure(int id) {
        structureId = id;
    }

    public int getStructure() {
        return structureId;
    }
}
