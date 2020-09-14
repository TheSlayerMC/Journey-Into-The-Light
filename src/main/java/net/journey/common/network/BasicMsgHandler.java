package net.journey.common.network;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jetbrains.annotations.Nullable;

public abstract class BasicMsgHandler<REQ extends IMessage, REPLY extends IMessage> implements IMessageHandler<REQ, REPLY> {
	@Override
	public final REPLY onMessage(REQ message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> doOnMessage(message, ctx));

		return getReply();
	}

	/**
	 * Called when a message is received of the appropriate type.
	 * <p>
	 * Already on the main thread, so there is no need to write stuff like {@link Minecraft#addScheduledTask(Runnable)}
	 *
	 * @param message The message
	 */
	protected abstract void doOnMessage(REQ message, MessageContext ctx);

	/**
	 * Message that will be sent to the opposite side as an answer on received message.
	 */
	@Nullable
	protected REPLY getReply() {
		return null;
	}
}
