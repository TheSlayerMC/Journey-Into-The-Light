package net.jitl.common.eventhandler;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.jitl.common.helper.EnumStructureMusic;
import net.jitl.network.JPacketHandler;
import net.jitl.network.SCurrentStructurePacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class StructureTracker {
    //private static final HashMap<UUID, Integer> playerStructures = new HashMap<>(1); //all players and their current structure will be saved here

    @SubscribeEvent()
    public static void onPlayerTick(TickEvent.PlayerTickEvent structureEvent) {
        if (structureEvent.side == LogicalSide.SERVER && structureEvent.phase == TickEvent.Phase.START) {
            ServerPlayerEntity player = (ServerPlayerEntity) structureEvent.player;
            ICurrentStructureCapability capability = JCapabilityProvider.getCapability(player, JCapabilityProvider.STRUCTURE);
            if (capability != null) {
                int id = findStructure(player);
                if (id != capability.getStructure()) {
                    JPacketHandler.sendToPlayer(player, new SCurrentStructurePacket(id));
                    capability.setStructure(id);
                    System.out.println("Packet sent");
                }
            }
        }
    }

    private static int findStructure(ServerPlayerEntity player) {
        for (EnumStructureMusic currentStructure : EnumStructureMusic.values()) {
            if (player.getLevel().structureFeatureManager().getStructureAt(player.blockPosition(), true, currentStructure.getStructure()).isValid()) {
                return currentStructure.getID();
            }
        }
        return 0;
    }
}