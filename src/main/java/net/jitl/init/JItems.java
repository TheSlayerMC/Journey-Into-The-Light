package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.item.TestBugItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static ru.timeconqueror.timecore.util.Hacks.promise;

@ObjectHolder(JITL.MODID)
public class JItems {

    public static final Item SAPPHIRE = promise();
    public static final Item IRIDIUM_NUGGET = promise();
    public static final Item SHADIUM_INGOT = promise();
    public static final Item LUNIUM_INGOT = promise();

    public static final Item LUNIUM_SWORD = promise();

    public static final TestBugItem TEST_BUG = promise();
}