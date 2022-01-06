package net.jitl.network;

import net.jitl.JITL;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import ru.timeconqueror.timecore.api.registry.PacketRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JPacketHandler {
    @AutoRegistrable
    private static final PacketRegister REGISTER = new PacketRegister(JITL.MODID);

    private static final String PROTOCOL_STRING = "1";

    public static final SimpleChannel INSTANCE = REGISTER.createChannel("main", () -> PROTOCOL_STRING, PROTOCOL_STRING::equals, PROTOCOL_STRING::equals)
            .regPacket(SCurrentStructurePacket.class, new SCurrentStructurePacket.Handler(), NetworkDirection.PLAY_TO_CLIENT)
            .regPacket(KeyPressedPacket.class, new KeyPressedPacket.Handler(), NetworkDirection.PLAY_TO_SERVER)
            .regPacket(JBossPacket.class, new JBossPacket.Handler(), NetworkDirection.PLAY_TO_CLIENT)
            .asChannel();

    public static <MSG> void sendToPlayer(ServerPlayer player, MSG message) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
