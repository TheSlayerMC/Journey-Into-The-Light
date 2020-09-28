package net.journey.dialogue;

import net.journey.client.render.gui.dialogue.GuiDialogue;
import net.journey.common.JManagers;
import net.journey.common.network.BasicMsgHandler;
import net.journey.common.network.dialogue.C2SChosenOptionMsg;
import net.journey.common.network.dialogue.S2CCloseDialogueGuiMsg;
import net.journey.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.journey.util.NetworkUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DialogueNetHandler {
    public static class S2CCloseDialogueHandler extends BasicMsgHandler<S2CCloseDialogueGuiMsg, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        protected void doOnMessage(S2CCloseDialogueGuiMsg message, MessageContext ctx) {
            JManagers.DIALOGUE_MANAGER.getNetHandler().handleDialogueClosePacket(message, ctx);
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleDialogueClosePacket(S2CCloseDialogueGuiMsg message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen instanceof GuiDialogue) {
            mc.displayGuiScreen(null);
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleDialogueOpenPacket(S2COpenDialogueGuiMsg message, MessageContext ctx) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiDialogue(message.getClientNode()));
    }

    public void handlePressOptionPacket(C2SChosenOptionMsg message, MessageContext ctx) {
        try {
            JManagers.DIALOGUE_MANAGER.handleDialogueChosenOption(ctx.getServerHandler().player, message.getOptionIndex());
        } catch (DialogueSystemException e) {
            NetworkUtils.kickPlayer(ctx.getServerHandler().player, new TextComponentString(e.getLocalizedMessage()));
        }
    }
}
