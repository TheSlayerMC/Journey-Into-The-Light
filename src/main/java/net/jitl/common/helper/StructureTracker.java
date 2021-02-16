package net.jitl.common.helper;

import net.jitl.JITL;
import net.jitl.network.JPacketHandler;
import net.jitl.network.SCurrentStructurePacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class StructureTracker {
    private static final HashMap<UUID, Integer> playerStructures = new HashMap<>(1); //all players and their current structure will be saved here

    @SubscribeEvent()
    public static void onPlayerTick(TickEvent.PlayerTickEvent structureEvent) {
        if (structureEvent.side == LogicalSide.SERVER && structureEvent.phase == TickEvent.Phase.START) {
            ServerPlayerEntity player = (ServerPlayerEntity) structureEvent.player;
            int id = findStructure(player);
            if (!playerStructures.containsKey(player.getUUID()) || playerStructures.get(player.getUUID()) != id) {
                playerStructures.put(player.getUUID(), id); //Any way to do this without sending pointless packets on player joins?
                JPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SCurrentStructurePacket(id));
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