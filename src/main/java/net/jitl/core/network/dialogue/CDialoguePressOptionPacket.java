package net.jitl.core.network.dialogue;

import net.jitl.common.dialogue.DialogueNetHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;

public record CDialoguePressOptionPacket(int optionIndex) {

    public static class Handler extends SimplePacketHandler<CDialoguePressOptionPacket> {
        @Override
        public void encode(CDialoguePressOptionPacket packet, FriendlyByteBuf buffer) {
            buffer.writeInt(packet.optionIndex);
        }

        @Override
        public @NotNull
        CDialoguePressOptionPacket decode(FriendlyByteBuf buffer) throws IOException {
            return new CDialoguePressOptionPacket(buffer.readInt());
        }

        @Override
        public void handleOnMainThread(CDialoguePressOptionPacket packet, NetworkEvent.Context ctx) {
            DialogueNetHandler.handlePressOptionPacket(packet, ctx);
        }
    }
}