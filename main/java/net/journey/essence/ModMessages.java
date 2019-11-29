package net.journey.essence;

import net.journey.JITL;
import net.minecraftforge.fml.relauncher.Side;

public class ModMessages {

	public static void initServer(){
        JITL.network.registerMessage(MessageEssenceBar.Handler.class, MessageEssenceBar.class, 0, Side.SERVER);
        
    }
	public static void initClient(){
        JITL.network.registerMessage(MessageEssenceBar.Handler.class, MessageEssenceBar.class, 1, Side.CLIENT);
    }
}
