package net.journey.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class S2CKickPlayerFromSPMsg extends BaseMsg {
	private ITextComponent kickReason;

	@Deprecated // is called via reflection, not for direct use
	public S2CKickPlayerFromSPMsg() {
	}

	public S2CKickPlayerFromSPMsg(ITextComponent kickReason) {
		this.kickReason = kickReason;
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeTextComponent(kickReason);
	}

	@Override
	protected void read(PacketBuffer buffer) {
		try {
			kickReason = buffer.readTextComponent();
		} catch (IOException e) {
			kickReason = new TextComponentString("Reason text corrupted: " + e);
		}
	}

	public static class Handler extends BasicMsgHandler<S2CKickPlayerFromSPMsg, BaseMsg> {
		@Override
		@SideOnly(Side.CLIENT)
		protected void doOnMessage(S2CKickPlayerFromSPMsg message, MessageContext ctx) {
			Minecraft mc = Minecraft.getMinecraft();

			mc.world.sendQuittingDisconnectingPacket();
			mc.loadWorld(null);

			mc.displayGuiScreen(new GuiDisconnected(new GuiWorldSelection(new GuiMainMenu()), "disconnect.lost", message.kickReason));
		}
	}
}
