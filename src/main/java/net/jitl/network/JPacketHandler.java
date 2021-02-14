package net.jitl.network;

import net.jitl.JITL;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class JPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(JITL.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION :: equals,
            PROTOCOL_VERSION :: equals
    );

    public static void registerPackets() {
        int packet = 0;
        INSTANCE.registerMessage(
                packet++,
                CurrentStructurePacket.class,
                CurrentStructurePacket::encode,
                CurrentStructurePacket::decode,
                CurrentStructurePacket::onReceivePacket
        );
    }
}
