package net.jitl.core.network.dialogue;

import net.jitl.common.dialog.DialogNetHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;

public record CDialogPressOptionPacket(int optionIndex) {

    public static class Handler extends SimplePacketHandler<CDialogPressOptionPacket> {
        @Override
        public void encode(CDialogPressOptionPacket packet, FriendlyByteBuf buffer) {
            buffer.writeInt(packet.optionIndex);
        }

        @Override
        public @NotNull
        CDialogPressOptionPacket decode(FriendlyByteBuf buffer) throws IOException {
            return new CDialogPressOptionPacket(buffer.readInt());
        }

        @Override
        public void handleOnMainThread(CDialogPressOptionPacket packet, NetworkEvent.Context ctx) {
            DialogNetHandler.handlePressOptionPacket(packet, ctx);
        }
    }
}