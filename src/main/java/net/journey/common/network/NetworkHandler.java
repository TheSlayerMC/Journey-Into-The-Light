package net.journey.common.network;

import net.journey.JITL;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(JITL.MOD_ID);

	public static void registerPackets() {
		int idx = 0;
		INSTANCE.registerMessage(S2CSyncJourneyCap.Handler.class, S2CSyncJourneyCap.class, idx++, Side.CLIENT);
	}
}