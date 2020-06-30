package net.journey.network;

import net.journey.JITL;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.timeconqueror.timecore.common.network.S2CEndAnimationMsg;
import ru.timeconqueror.timecore.common.network.S2CStartAnimationMsg;

public class NetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(JITL.MOD_ID);

	public static void registerPackets() {
		int idx = 0;
		INSTANCE.registerMessage(S2CStartAnimationMsg.Handler.class, S2CStartAnimationMsg.class, idx++, Side.CLIENT);
		INSTANCE.registerMessage(S2CEndAnimationMsg.Handler.class, S2CEndAnimationMsg.class, idx++, Side.CLIENT);
	}
}
