package net.jitl.common.dialogue;

/*import net.jitl.network.S2CCloseDialogueGuiMsg;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DialogueNetHandler {
    public static class S2CCloseDialogueHandler extends BasicMsgHandler<S2CCloseDialogueGuiMsg, IMessage> {
        @Override
        @OnlyIn(Side.CLIENT)
        protected void doOnMessage(S2CCloseDialogueGuiMsg message, MessageContext ctx) {
            JManagers.DIALOGUE_MANAGER.getNetHandler().handleDialogueClosePacket(message, ctx);
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleDialogueClosePacket(S2CCloseDialogueGuiMsg message, MessageContext ctx) {
        Minecraft mc = Minecraft.getInstance();
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
}*/