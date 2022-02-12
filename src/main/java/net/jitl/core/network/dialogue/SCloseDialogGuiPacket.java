package net.jitl.core.network.dialogue;

import net.jitl.common.dialog.DialogNetHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;

public class SCloseDialogGuiPacket {
    public static class Handler extends SimplePacketHandler<SCloseDialogGuiPacket> {

        @Override
        public void encode(SCloseDialogGuiPacket packet, FriendlyByteBuf buffer) {
        }

        @Override
        public @NotNull SCloseDialogGuiPacket decode(FriendlyByteBuf buffer) throws IOException {
            return new SCloseDialogGuiPacket();
        }

        @Override
        public void handleOnMainThread(SCloseDialogGuiPacket packet, NetworkEvent.Context ctx) {
            DialogNetHandler.handleCloseDialogPacket(packet, ctx);
        }
    }
}