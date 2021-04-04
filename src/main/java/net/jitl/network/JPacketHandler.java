package net.jitl.network;

import net.jitl.JITL;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import ru.timeconqueror.timecore.api.registry.PacketRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.mod.common.packet.InternalPacketManager;

public class JPacketHandler {
    @AutoRegistrable
    private static final PacketRegister REGISTER = new PacketRegister(JITL.MODID);

    private static final String PROTOCOL_STRING = "1";

    public static final SimpleChannel INSTANCE = REGISTER.createChannel("main", () -> PROTOCOL_STRING, PROTOCOL_STRING::equals, PROTOCOL_STRING::equals)
            .regPacket(SCurrentStructurePacket.class, new SCurrentStructurePacket.Handler())
            .regPacket(KeyPressedPacket.class, new KeyPressedPacket.Handler())
            .regPacket(EssenceUpdatePacket.class, new EssenceUpdatePacket.Handler())
            .asChannel();

    public static <MSG> void sendToPlayer(ServerPlayerEntity player, MSG message) {
        InternalPacketManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
