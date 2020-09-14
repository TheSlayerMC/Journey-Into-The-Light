package net.journey.common.network;

import net.journey.JITL;
import net.journey.common.network.dialogue.C2SChosenOptionMsg;
import net.journey.common.network.dialogue.S2COpenDialogueGuiMsg;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(JITL.MOD_ID);

	public static void registerPackets() {
		int idx = 0;
		INSTANCE.registerMessage(S2CSyncJourneyCapMsg.Handler.class, S2CSyncJourneyCapMsg.class, idx++, Side.CLIENT);

		INSTANCE.registerMessage(S2COpenDialogueGuiMsg.Handler.class, S2COpenDialogueGuiMsg.class, idx++, Side.CLIENT);
		INSTANCE.registerMessage(C2SChosenOptionMsg.Handler.class, C2SChosenOptionMsg.class, idx++, Side.SERVER);

		INSTANCE.registerMessage(S2CKickPlayerFromSPMsg.Handler.class, S2CKickPlayerFromSPMsg.class, idx++, Side.CLIENT);
	}
}