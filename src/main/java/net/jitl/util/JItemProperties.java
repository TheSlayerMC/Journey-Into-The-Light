package net.jitl.util;

import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import ru.timeconqueror.timecore.api.registry.util.ItemPropsFactory;

public class JItemProperties {
    private static final ItemPropsFactory ITEM_GROUP = new ItemPropsFactory(JTabs.ITEMS);
    private static final ItemPropsFactory TOOLS_GROUP = new ItemPropsFactory(JTabs.TOOLS);
    private static final ItemPropsFactory WEAPONS_GROUP = new ItemPropsFactory(JTabs.WEAPONS);
    private static final ItemPropsFactory MISC_GROUP = new ItemPropsFactory(JTabs.MISC);
    private static final ItemPropsFactory RANGED_WEAPONS_GROUP = new ItemPropsFactory(JTabs.MISC);

    public static Item.Properties itemGrouped() {
        return ITEM_GROUP.create();
    }

    public static Item.Properties toolsGrouped() {
        return TOOLS_GROUP.create();
    }

    public static Item.Properties weaponsGrouped() {
        return WEAPONS_GROUP.create();
    }

    public static Item.Properties miscGrouped() {
        return MISC_GROUP.create();
    }

    public static Item.Properties rangedWeaponsGrouped() {
        return RANGED_WEAPONS_GROUP.create();
    }
}
