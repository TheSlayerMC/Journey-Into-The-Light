package net.jitl.core.network.dialogue;

import net.jitl.common.dialogue.DialogueNetHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;

public class SCloseDialogueGuiPacket {
    public static class Handler extends SimplePacketHandler<SCloseDialogueGuiPacket> {

        @Override
        public void encode(SCloseDialogueGuiPacket packet, FriendlyByteBuf buffer) {
        }

        @Override
        public @NotNull SCloseDialogueGuiPacket decode(FriendlyByteBuf buffer) throws IOException {
            return new SCloseDialogueGuiPacket();
        }

        @Override
        public void handleOnMainThread(SCloseDialogueGuiPacket packet, NetworkEvent.Context ctx) {
            DialogueNetHandler.handleDialogueClosePacket(packet, ctx);
        }
    }
}