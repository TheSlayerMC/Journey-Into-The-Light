package ru.timeconqueror.timecore.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.timeconqueror.timecore.TimeCore;

public class TCNetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(TimeCore.MODID);

	public static void registerPackets() {
		int idx = 0;
		INSTANCE.registerMessage(S2CStartAnimationMsg.Handler.class, S2CStartAnimationMsg.class, idx++, Side.CLIENT);
		INSTANCE.registerMessage(S2CEndAnimationMsg.Handler.class, S2CEndAnimationMsg.class, idx++, Side.CLIENT);
		INSTANCE.registerMessage(S2CSyncEntityAnimationsMsg.Handler.class, S2CSyncEntityAnimationsMsg.class, idx++, Side.CLIENT);
	}
}
