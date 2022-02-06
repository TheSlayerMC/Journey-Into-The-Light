package net.jitl.core.network.dialogue;

import net.jitl.client.dialogue.ClientDialoguePage;
import net.jitl.common.dialogue.DialogueNetHandler;
import net.jitl.common.dialogue.DialoguePage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SOpenDialogueGuiPacket {
    private DialoguePage page;
    private final ResourceLocation npcKey;
    private ClientDialoguePage clientPage;

    private SOpenDialogueGuiPacket(ResourceLocation npcKey, ClientDialoguePage clientPage) {
        this.npcKey = npcKey;
        this.clientPage = clientPage;
    }

    public SOpenDialogueGuiPacket(EntityType<? extends LivingEntity> entityType, DialoguePage page) {
        this.page = page;
        this.npcKey = Objects.requireNonNull(entityType.getRegistryName());
    }

    public ClientDialoguePage getClientPage() {
        return clientPage;
    }

    public static class Handler extends SimplePacketHandler<SOpenDialogueGuiPacket> {
        @Override
        public void encode(SOpenDialogueGuiPacket packet, FriendlyByteBuf buffer) {
            buffer.writeResourceLocation(packet.npcKey);

            buffer.writeUtf(packet.page.getTextKey());

            List<DialoguePage.Option> options = packet.page.getOptions();
            buffer.writeInt(options.size());
            for (DialoguePage.Option option : options) {
                buffer.writeUtf(option.getText());
            }
        }

        @Override
        public @NotNull SOpenDialogueGuiPacket decode(FriendlyByteBuf buffer) throws IOException {
            ResourceLocation npcKey = buffer.readResourceLocation();

            String text = buffer.readUtf();

            int size = buffer.readInt();
            List<String> optionKeys = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                optionKeys.add(buffer.readUtf());
            }

            ClientDialoguePage clientPage = new ClientDialoguePage(npcKey, text, optionKeys);

            return new SOpenDialogueGuiPacket(npcKey, clientPage);
        }

        @Override
        public void handleOnMainThread(SOpenDialogueGuiPacket packet, NetworkEvent.Context ctx) {
            DialogueNetHandler.handleDialogueOpenPacket(packet, ctx);
        }
    }
}