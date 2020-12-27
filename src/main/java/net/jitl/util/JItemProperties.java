package net.jitl.util;

import net.jitl.init.JTabs;
import ru.timeconqueror.timecore.api.registry.util.ItemPropsFactory;

public class JItemProperties {
    public static final ItemPropsFactory DEFAULT = new ItemPropsFactory(JTabs.ITEMS);
    public static final ItemPropsFactory TOOLS = new ItemPropsFactory(JTabs.TOOLS);
    public static final ItemPropsFactory WEAPONS = new ItemPropsFactory(JTabs.WEAPONS);
    public static final ItemPropsFactory MISC = new ItemPropsFactory(JTabs.MISC);


}
