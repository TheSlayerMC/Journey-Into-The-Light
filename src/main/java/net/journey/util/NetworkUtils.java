package net.journey.util;

import net.journey.common.network.NetworkHandler;
import net.journey.common.network.S2CKickPlayerFromSPMsg;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkUtils {
	/**
	 * Kicks player from current server.
	 * We need this method because if server is integrated (SP), it freezes the client on disconnect.
	 */
	public static void kickPlayer(EntityPlayerMP player, ITextComponent reason) {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {// if it's an integrated server
			NetworkHandler.INSTANCE.sendTo(new S2CKickPlayerFromSPMsg(reason), player);
		} else {
			player.connection.disconnect(reason);
		}
	}
}
