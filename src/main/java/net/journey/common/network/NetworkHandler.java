package net.journey.common.network;

import net.journey.JITL;
import net.journey.common.JManagers;
import net.journey.common.network.dialogue.C2SChosenOptionMsg;
import net.journey.common.network.dialogue.DialoguePacketHandler;
import net.journey.common.network.dialogue.S2CCloseDialogueGuiMsg;
import net.journey.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.journey.dialogue.DialogueNetHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.function.BiConsumer;

public class NetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(JITL.MOD_ID);
	private static int id;

	@SuppressWarnings("Convert2MethodRef") // never convert it to method ref here because it causes crashes
	public static void registerPackets() {
		registerMessage(S2CSyncJourneyCapMsg.class, S2CSyncJourneyCapMsg.Handler.class, Side.CLIENT);
		registerMessage(S2CKickPlayerFromSPMsg.class, S2CKickPlayerFromSPMsg.Handler.class, Side.CLIENT);

		DialogueNetHandler dialogueNetHandler = getDialogueNetHandler();

		registerDialoguePacket(S2COpenDialogueGuiMsg.class, (message, ctx) -> dialogueNetHandler.handleDialogueOpenPacket(message, ctx), Side.CLIENT);
		registerDialoguePacket(S2CCloseDialogueGuiMsg.class, (message, ctx) -> dialogueNetHandler.handleDialogueClosePacket(message, ctx), Side.CLIENT);
		registerDialoguePacket(C2SChosenOptionMsg.class, (message, ctx) -> dialogueNetHandler.handlePressOptionPacket(message, ctx), Side.SERVER);
	}

	private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<REQ> packetClass, Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Side side) {
		INSTANCE.registerMessage(messageHandler, packetClass, id++, side);
	}

	private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<REQ> packetClass, IMessageHandler<? super REQ, ? extends REPLY> messageHandler, Side side) {
		INSTANCE.registerMessage(messageHandler, packetClass, id++, side);
	}

	private static <REQ extends IMessage> void registerDialoguePacket(Class<REQ> packetClass, BiConsumer<REQ, MessageContext> handler, Side side) {
		registerMessage(packetClass, new DialoguePacketHandler<>(handler), side);
	}

	private static DialogueNetHandler getDialogueNetHandler() {
		return JManagers.DIALOGUE_MANAGER.getNetHandler();
	}
}