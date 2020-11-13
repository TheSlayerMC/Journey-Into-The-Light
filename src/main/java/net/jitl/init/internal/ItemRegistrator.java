package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.common.item.TestBugItem;
import net.jitl.util.JItemProperties;
import net.minecraft.item.Item;
import ru.timeconqueror.timecore.api.client.resource.StandardItemModelParents;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.ItemRegister;

import java.util.function.Function;

public class ItemRegistrator {
    @AutoRegistrable
    private static final ItemRegister REGISTER = new ItemRegister(JITL.MODID);

    @AutoRegistrable.InitMethod
    private static void register() {
        registerItem("sapphire", "Sapphire", Item::new, StandardItemModelParents.DEFAULT);
        registerItem("iridium_nugget", "Iridium Nugget", Item::new, StandardItemModelParents.DEFAULT);
        registerItem("lunium_ingot", "Lunium Ingot", Item::new, StandardItemModelParents.DEFAULT);
        registerItem("shadium_ingot", "Shadium Ingot", Item::new, StandardItemModelParents.DEFAULT);
        registerItem("lunium_sword", "Lunium Sword", Item::new, StandardItemModelParents.DEFAULT);
        registerItem("test_bug", "Test Bug", TestBugItem::new, StandardItemModelParents.DEFAULT);
    }

    private static void registerItem(String name, String enName, Function<Item.Properties, ? extends Item> itemFactory, StandardItemModelParents modelType) {
        REGISTER.register(name, () -> itemFactory.apply(JItemProperties.DEFAULT.create()))
                .genModel(modelType)
                .genLangEntry(enName);
    }
}
