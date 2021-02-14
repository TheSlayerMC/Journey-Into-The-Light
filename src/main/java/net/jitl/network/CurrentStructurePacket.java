package net.jitl.network;

import net.jitl.client.music.StructureMusicHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CurrentStructurePacket {
    private final int storedID;
    public CurrentStructurePacket(int id) {
        storedID = id;
    }
    public void encode(PacketBuffer buffer) {
        buffer.writeInt(storedID);
    }

    public static CurrentStructurePacket decode(PacketBuffer buffer) {
        return new CurrentStructurePacket(buffer.readInt());
    }

    public static void onReceivePacket(CurrentStructurePacket packet, Supplier<NetworkEvent.Context> context) {
        StructureMusicHandler.currentMusic = StructureMusicHandler.MusicStructure.getFromID(packet.storedID);
        System.out.println("Packet received");
        context.get().setPacketHandled(true);
    }
}
