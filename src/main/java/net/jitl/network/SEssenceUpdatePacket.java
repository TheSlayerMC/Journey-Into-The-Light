package net.jitl.network;

import net.jitl.client.eventhandler.GuiEventHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacket;

public class SEssenceUpdatePacket implements ITimePacket {
    private final float essence;

    public SEssenceUpdatePacket(float value) {
        essence = value;
    }

    public static class Handler implements ITimePacketHandler<SEssenceUpdatePacket> {
        @Override
        public void encode(SEssenceUpdatePacket packet, PacketBuffer buffer) {
            buffer.writeFloat(packet.essence);
        }

        @Override
        public @NotNull SEssenceUpdatePacket decode(PacketBuffer buffer) {
            return new SEssenceUpdatePacket(buffer.readFloat());
        }

        @Override
        public boolean handle(SEssenceUpdatePacket packet, NetworkEvent.Context ctx) {
            ctx.enqueueWork(() -> {
                GuiEventHandler.essence = packet.essence;//TODO check if really needed to be enqueued?
            });

            return true;
        }
    }
}