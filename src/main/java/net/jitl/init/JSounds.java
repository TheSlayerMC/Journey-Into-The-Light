package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static ru.timeconqueror.timecore.util.Hacks.promise;

@ObjectHolder(JITL.MODID)
public class JSounds {
	public static final SoundEvent TEST_SOUND = promise();
}
