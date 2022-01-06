package net.jitl.util;

import net.jitl.init.JTabs;
import net.minecraft.world.item.Item;
import ru.timeconqueror.timecore.api.registry.util.ItemPropsFactory;

public class JItemProperties {
    private static final ItemPropsFactory ITEM_GROUP = ItemPropsFactory.of(JTabs.ITEMS);
    private static final ItemPropsFactory TOOLS_GROUP = ItemPropsFactory.of(JTabs.TOOLS);
    private static final ItemPropsFactory WEAPONS_GROUP = ItemPropsFactory.of(JTabs.WEAPONS);
    private static final ItemPropsFactory MISC_GROUP = ItemPropsFactory.of(JTabs.MISC);
    private static final ItemPropsFactory RANGED_WEAPONS_GROUP = ItemPropsFactory.of(JTabs.RANGED_WEAPONS);

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
