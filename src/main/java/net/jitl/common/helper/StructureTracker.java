package net.jitl.common.helper;

import net.jitl.JITL;
import net.jitl.client.music.StructureMusicHandler;
import net.jitl.network.JPacketHandler;
import net.jitl.network.SCurrentStructurePacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class StructureTracker {
    private static final HashMap<UUID, StructureStart> playerStructures = new HashMap<>(1); //all players and their current structure will be saved here

    @SubscribeEvent()
    public static void onPlayerTick(TickEvent.PlayerTickEvent structureEvent) {
        if (structureEvent.side == LogicalSide.SERVER && structureEvent.phase == TickEvent.Phase.START) {
            for (StructureMusicHandler.MusicStructure currentStructure : StructureMusicHandler.MusicStructure.values()) {
                if (((ServerPlayerEntity) structureEvent.player).getLevel().structureFeatureManager().getStructureAt(structureEvent.player.blockPosition(),
                        true,
                        currentStructure.getStructure()).isValid()) {
                    JPacketHandler.INSTANCE.send(
                            PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) structureEvent.player),
                            new SCurrentStructurePacket(currentStructure.getID())
                    );
                }
            }
            /*StructureStart structure = ((ServerPlayerEntity) structureEvent.player).getLevel().structureFeatureManager().getStructureAt(structureEvent.player.blockPosition(), true, JStructures.GUARDIAN_TOWER_HOLDER.getStructure());
            if (playerStructures.get(structureEvent.player.getUUID()) != structure) {
                //TODO: Sync
                playerStructures.put(structureEvent.player.getUUID(), structure);
            }*/
        }
    }
}