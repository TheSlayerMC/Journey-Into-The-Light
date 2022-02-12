package net.jitl.common.dialog;

import net.jitl.common.capability.dialog.DialogManager;
import net.jitl.core.network.dialogue.CDialogPressOptionPacket;
import net.jitl.core.network.dialogue.SCloseDialogGuiPacket;
import net.jitl.core.network.dialogue.SOpenDialogGuiPacket;
import net.jitl.core.network.dialogue.SSetCharacterPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;

public class DialogNetHandler {
    @OnlyIn(Dist.CLIENT)
    public static void handleCloseDialogPacket(SCloseDialogGuiPacket message, NetworkEvent.Context ctx) {
        ClientDialogManager.getInstance().closeDialog();
    }

    @OnlyIn(Dist.CLIENT)
    public static void handleOpenDialogPacket(SOpenDialogGuiPacket message, NetworkEvent.Context ctx) {
        ClientDialogManager.getInstance().openPage(message.getClientPage());
    }

    public static void handlePressOptionPacket(CDialogPressOptionPacket message, NetworkEvent.Context ctx) {
        ServerPlayer player = Objects.requireNonNull(ctx.getSender()); // sender is not null on server
        DialogManager.of(player).pressOption(message.optionIndex());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handleSetCharacter(SSetCharacterPacket packet) {
        ClientDialogManager.getInstance().setCharacter(packet.character());
    }
}