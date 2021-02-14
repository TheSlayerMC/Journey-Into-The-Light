package net.jitl.network;

import net.jitl.JITL;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class JPacketHandler {
    private static final String PROTOCOL_VERSION = "1"; //idk exactly what this does
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel( //this object handles packets. It includes methods for registering and sending them
            new ResourceLocation(JITL.MODID, "journey"), //specifies the name of the channel
            () -> PROTOCOL_VERSION, //a Supplier for PROTOCOL_VERSION
            PROTOCOL_VERSION :: equals, //provides the protocol version to check whether an incoming connection is compatible with the client
            PROTOCOL_VERSION :: equals //same as previous but for the server
    );

    public static void registerPackets() { //a method called in preInit to register all the packets in the mod
        int packet = 0; //every packet needs a unique id. This int will be incremented per packet to generate these ids
        //the arguments for registerMessage() are: an id, the class for the packet, the method which writes the packet, the method which reads the packet, and the method which handles the information in the packet
        INSTANCE.registerMessage(packet++, CurrentStructurePacket.class, CurrentStructurePacket::encode, CurrentStructurePacket::decode, CurrentStructurePacket::onReceivePacket);
    }
}
