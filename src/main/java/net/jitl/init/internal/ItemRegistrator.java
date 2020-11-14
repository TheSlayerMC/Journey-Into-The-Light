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
        registerItem("sapphire", "Sapphire");
        registerItem("iridium_nugget", "Iridium Nugget");
        registerItem("lunium_ingot", "Lunium Ingot");
        registerItem("lunium_sword", "Lunium Sword");
        registerItem("shadium_ingot", "Shadium Ingot");
        registerItem("test_bug", "Test Bug", TestBugItem::new);
    }

    private static void registerItem(String name, String enName) {
        Function<Item.Properties, ? extends Item> itemFactory = Item::new;

        REGISTER.register(name, () -> itemFactory.apply(JItemProperties.DEFAULT.create()))
                .genModel(StandardItemModelParents.DEFAULT)
                .genLangEntry(enName);
    }

    private static void registerItem(String name, String enName, Function<Item.Properties, ? extends Item> itemFactory) {
        REGISTER.register(name, () -> itemFactory.apply(JItemProperties.DEFAULT.create()))
                .genModel(StandardItemModelParents.DEFAULT)
                .genLangEntry(enName);
    }

    private static void registerItem(String name, String enName, Function<Item.Properties, ? extends Item> itemFactory, StandardItemModelParents modelType) {
        REGISTER.register(name, () -> itemFactory.apply(JItemProperties.DEFAULT.create()))
                .genModel(modelType)
                .genLangEntry(enName);
    }
}
