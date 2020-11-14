package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.*;
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
        registerItem("shadium_ingot", "Shadium Ingot");
        registerItem("test_bug", "Test Bug", TestBugItem::new);

        registerToolSet("sapphire", "Sapphire", JToolTiers.SAPPHIRE);
        registerToolSet("lunium", "Lunium", JToolTiers.LUNIUM);
        registerToolSet("shadium", "Shadium", JToolTiers.SHADIUM);
    }

    public static void registerToolSet(String name, String engName, JToolTiers mat) {
        registerSwordItem(name + "_sword", engName + " Sword", mat);
        registerPickaxeItem(name + "_pickaxe", engName + " Pickaxe", mat);
        registerAxeItem(name + "_axe", engName + " Axe", mat);
        registerShovelItem(name + "_shovel", engName + " Shovel", mat);
        registerHoeItem(name + "_hoe", engName + " Hoe", mat);
    }

    private static void registerSwordItem(String name, String enName, JToolTiers material) {
        REGISTER.register(name, () -> new JItemSword(material))
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
    }

    private static void registerShovelItem(String name, String enName, JToolTiers material) {
        REGISTER.register(name, () -> new JItemShovel(material))
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
    }

    private static void registerHoeItem(String name, String enName, JToolTiers material) {
        REGISTER.register(name, () -> new JItemHoe(material))
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
    }

    private static void registerAxeItem(String name, String enName, JToolTiers material) {
        REGISTER.register(name, () -> new JItemAxe(material))
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
    }

    private static void registerPickaxeItem(String name, String enName, JToolTiers material) {
        REGISTER.register(name, () -> new JItemPickaxe(material))
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
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
