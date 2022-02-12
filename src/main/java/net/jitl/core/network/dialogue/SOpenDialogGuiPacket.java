package net.jitl.core.network.dialogue;

import net.jitl.common.dialog.ClientDialogPage;
import net.jitl.common.dialog.DialogNetHandler;
import net.jitl.common.dialog.DialogPage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SOpenDialogGuiPacket {
    private DialogPage page;
    private ClientDialogPage clientPage;

    private SOpenDialogGuiPacket(ClientDialogPage clientPage) {
        this.clientPage = clientPage;
    }

    public SOpenDialogGuiPacket(DialogPage page) {
        this.page = page;
    }

    public ClientDialogPage getClientPage() {
        return clientPage;
    }

    public static class Handler extends SimplePacketHandler<SOpenDialogGuiPacket> {
        @Override
        public void encode(SOpenDialogGuiPacket packet, FriendlyByteBuf buffer) {
            buffer.writeUtf(packet.page.getPhrase());

            List<String> options = packet.page.getOptions();
            Objects.requireNonNull(options);

            buffer.writeInt(options.size());
            for (String option : options) {
                buffer.writeUtf(option);
            }
        }

        @Override
        public @NotNull SOpenDialogGuiPacket decode(FriendlyByteBuf buffer) throws IOException {
            String text = buffer.readUtf();

            int size = buffer.readInt();
            List<String> optionKeys = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                optionKeys.add(buffer.readUtf());
            }

            return new SOpenDialogGuiPacket(new ClientDialogPage(text, optionKeys));
        }

        @Override
        public void handleOnMainThread(SOpenDialogGuiPacket packet, NetworkEvent.Context ctx) {
            DialogNetHandler.handleOpenDialogPacket(packet, ctx);
        }
    }
}