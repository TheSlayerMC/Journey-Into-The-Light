package net.jitl.network;

import net.jitl.client.music.StructureMusicHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CurrentStructurePacket {
    private final int storedID; //this packet just holds a single int

    public CurrentStructurePacket(int id) {
        storedID = id;
    } //basic constructor

    /*
    Right before a packet is sent away, this method writes the information to be sent into the PacketBuffer
     */
    public void encode(PacketBuffer buffer) {
        buffer.writeInt(storedID);
    }

    /*
    The opposite of the above, this method runs after a packet is received.
    The information in the incoming PacketBuffer is written stored into a new object which is returned.
    This method doesn't actually need to do anything if you just want to react to something sent from the client/server
     */
    public static CurrentStructurePacket decode(PacketBuffer buffer) {
        return new CurrentStructurePacket(buffer.readInt());
    }

    /*
    This method is the packet's behavior when it is received.
    Here is where you use the packet and its info to somehow affect your code.
     */
    public static void onReceivePacket(CurrentStructurePacket packet, Supplier<NetworkEvent.Context> context) {
        StructureMusicHandler.currentMusic = StructureMusicHandler.MusicStructure.getFromID(packet.storedID);
        System.out.println("Packet received");
        context.get().setPacketHandled(true); //this must be called to confirm the packet was received
    }
}
