package net.jitl.common.dialogue;

import net.jitl.client.render.gui.dialogue.DialogueScreen;
import net.jitl.core.network.dialogue.CDialoguePressOptionPacket;
import net.jitl.core.network.dialogue.SCloseDialogueGuiPacket;
import net.jitl.core.network.dialogue.SOpenDialogueGuiPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import ru.timeconqueror.timecore.api.util.NetworkUtils;

import java.util.Objects;

public class DialogueNetHandler {
    @OnlyIn(Dist.CLIENT)
    public static void handleDialogueClosePacket(SCloseDialogueGuiPacket message, NetworkEvent.Context ctx) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof DialogueScreen) {
            mc.setScreen(null);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void handleDialogueOpenPacket(SOpenDialogueGuiPacket message, NetworkEvent.Context ctx) {
        Minecraft.getInstance().setScreen(new DialogueScreen(message.getClientPage()));
    }

    public static void handlePressOptionPacket(CDialoguePressOptionPacket message, NetworkEvent.Context ctx) {
        try {
            DialogueManager.getInstance().handlePressedOption(Objects.requireNonNull(ctx.getSender()), message.optionIndex());
        } catch (DialogueSystemException e) {
            NetworkUtils.kickPlayer(ctx.getSender(), new TextComponent(e.getLocalizedMessage()));//FIXME TEST
        }
    }
}