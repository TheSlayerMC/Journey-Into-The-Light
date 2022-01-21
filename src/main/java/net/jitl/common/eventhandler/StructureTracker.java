package net.jitl.common.eventhandler;

import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.currentstructure.ICurrentStructureCapability;
import net.jitl.common.helper.EnumStructureMusic;
import net.jitl.core.JITL;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.SCurrentStructurePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class StructureTracker {
    //private static final HashMap<UUID, Integer> playerStructures = new HashMap<>(1); //all players and their current structure will be saved here

    @SubscribeEvent()
    public static void onPlayerTick(TickEvent.PlayerTickEvent structureEvent) {
        if (structureEvent.side == LogicalSide.SERVER && structureEvent.phase == TickEvent.Phase.START) {
            ServerPlayer player = (ServerPlayer) structureEvent.player;
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

    private static int findStructure(ServerPlayer player) {
        for (EnumStructureMusic currentStructure : EnumStructureMusic.values()) {
            if (player.getLevel().structureFeatureManager().getStructureAt(player.blockPosition(), currentStructure.getStructure()).isValid()) {
                return currentStructure.getID();
            }
        }
        return 0;
    }
}