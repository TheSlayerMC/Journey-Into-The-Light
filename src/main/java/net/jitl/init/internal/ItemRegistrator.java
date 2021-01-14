package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.common.entity.projectile.ConjuringProjectileEntity;
import net.jitl.common.entity.projectile.EssenciaProjectileEntity;
import net.jitl.common.entity.projectile.FloroMudProjectileEntity;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.*;
import net.jitl.init.JEntities;
import net.jitl.init.JFoods;
import net.jitl.init.JTabs;
import net.jitl.util.JItemProperties;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import ru.timeconqueror.timecore.api.client.resource.StandardItemModelParents;
import ru.timeconqueror.timecore.api.registry.ItemRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemRegistrator {
    @AutoRegistrable
    private static final ItemRegister REGISTER = new ItemRegister(JITL.MODID);

    @AutoRegistrable.InitMethod
    private static void register() {

        //OVERWORLD ITEMS
        registerItem("sapphire", "Sapphire");
        registerItem("iridium_nugget", "Iridium Nugget");
        registerItem("lunium_ingot", "Lunium Ingot");
        registerItem("shadium_ingot", "Shadium Ingot");
        registerItem("obsidian_rod", "Obsidian Rod");
        registerItem("green_gem", "Green Gem");
        registerItem("blue_gem", "blue Gem");
        registerItem("purple_gem", "purple Gem");
        registerItem("yellow_gem", "Yellow Gem");
        registerItem("floro_pedal", "Floro Pedal");

        registerItem("mud_ball", "Mud Ball", () -> new ThrowableItem(new Item.Properties().tab(JTabs.ITEMS),
                (world, thrower) -> new FloroMudProjectileEntity(JEntities.FLORO_MUD_PROJECTILE_TYPE, world, thrower, 0.0F)));

        registerItem("bradberry", "Bradberry", () -> new Item(new Item.Properties().food(JFoods.BRADBERRY).tab(JTabs.ITEMS)));

        registerItem("hongoshroom", "Hongoshroom", () -> new Item(new Item.Properties().food(JFoods.HONGOSROOM).tab(JTabs.ITEMS)));
        registerItem("red_honglowshroom", "Red Honglowshroom", () -> new Item(new Item.Properties().food(JFoods.RED_HONGLOWSROOM).tab(JTabs.ITEMS)));
        registerItem("green_honglowshroom", "Green Honglowshroom", () -> new Item(new Item.Properties().food(JFoods.GREEN_HONGLOWSROOM).tab(JTabs.ITEMS)));
        registerItem("blue_honglowshroom", "Blue Honglowshroom", () -> new Item(new Item.Properties().food(JFoods.BLUE_HONGLOWSROOM).tab(JTabs.ITEMS)));

        registerItem("lunium_powder", "Lunium Powder");

        registerHandheldItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(new Item.Properties().tab(JTabs.RANGED_WEAPONS),
                (world, thrower) -> new ConjuringProjectileEntity(JEntities.CONJURING_PROJECTILE_TYPE, world, thrower, 0.0F)));

        registerHandheldItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(new Item.Properties().tab(JTabs.RANGED_WEAPONS),
                (world, thrower) -> new EssenciaProjectileEntity(JEntities.ESSENCIA_PROJECTILE_TYPE, world, thrower, 0.0F)));

        //NETHER ITEMS
        registerItem("bloodcrust_ingot", "Bloodcrust Ingot");
        registerItem("firestone_shard", "Firestone Shard", () -> new JFuelItem(new Item.Properties().fireResistant().tab(JTabs.ITEMS), 200));
        registerItem("firestone_clump", "Firestone Clump", () -> new JFuelItem(new Item.Properties().fireResistant().tab(JTabs.ITEMS), 2000));
        registerItem("warped_quartz", "Warped Quartz");
        registerItem("crimson_quartz", "Crimson Quartz");
        registerItem("blood", "Blood");
        registerItem("powder_of_essencia", "Powder Of Essencia");

		//END ITEMS
		registerItem("enderillium_shard", "Enderillium Shard");

		//BOILING POINT ITEMS
		registerItem("ash", "Ash");

		//EUCA ITEMS
		registerItem("celestium_ingot", "Celestium Ingot");
		registerItem("korite_ingot", "Korite Ingot");
        registerItem("mekyum_ingot", "Mekyum Ingot");
        registerItem("storon_ingot", "Storon Ingot");

        //DEVELOPER ITEMS
        registerItem("test_bug", "Test Bug", TestBugItem::new);

        //OVERWORLD TOOLSETS
        registerArmorAndToolSet("sapphire", "Sapphire", JToolTiers.SAPPHIRE, JArmorMaterial.SAPPHIRE);
        registerArmorAndToolSet("lunium", "Lunium", JToolTiers.LUNIUM, JArmorMaterial.LUNIUM);
        registerArmorAndToolSet("shadium", "Shadium", JToolTiers.SHADIUM, JArmorMaterial.SHADIUM);

        //NETHER TOOLSETS
        registerArmorAndToolSet("bloodcrust", "Bloodcrust", JToolTiers.BLOODCRUST, JArmorMaterial.BLOODCRUST);

        //EUCA TOOLSETS
        registerArmorAndToolSet("celestium", "Celestium", JToolTiers.CELESTIUM, JArmorMaterial.CELESTIUM);
        registerToolSet("korite", "Korite", JToolTiers.KORITE);
        registerToolSet("mekyum", "Mekyum", JToolTiers.MEKYUM);
        registerToolSet("storon", "Storon", JToolTiers.STORON);
    }

    /**
     * Registers an armor set and a tool set.
     *
     * @param name          Registry name
     * @param engName       English name
     * @param toolTiers     Stats for the tool set
     * @param armorMaterial Stats for the armor set
     */
    public static void registerArmorAndToolSet(String name, String engName, JToolTiers toolTiers, JArmorMaterial armorMaterial) {
        registerToolSet(name, engName, toolTiers);
        registerArmorSet(name, engName, armorMaterial);
    }

    /**
     * Registers a full armor set.
     *
     * @param name          Registry name
     * @param engName       English name
     * @param armorMaterial Stats for the armor set
     */
    public static void registerArmorSet(String name, String engName, JArmorMaterial armorMaterial) {
        registerArmorItem(name + "_helmet", engName + " Helmet", () -> new JArmorItem(armorMaterial, EquipmentSlotType.HEAD));
        registerArmorItem(name + "_chestplate", engName + " Chestplate", () -> new JArmorItem(armorMaterial, EquipmentSlotType.CHEST));
        registerArmorItem(name + "_leggings", engName + " Leggings", () -> new JArmorItem(armorMaterial, EquipmentSlotType.LEGS));
        registerArmorItem(name + "_boots", engName + " Boots", () -> new JArmorItem(armorMaterial, EquipmentSlotType.FEET));
    }

    /**
     * Registers an armor set and a tool set.
     *
     * @param name      Registry name
     * @param engName   English name
     * @param toolTiers Stats for the tool set
     */
    public static void registerToolSet(String name, String engName, JToolTiers toolTiers) {
        registerSwordItem(name + "_sword", engName + " Sword", toolTiers);

        registerHandheldItem(name + "_multitool", engName + " Multitool", () -> new MultitoolItem(toolTiers));
        registerHandheldItem(name + "_pickaxe", engName + " Pickaxe", () -> new JPickaxeItem(toolTiers));
        registerHandheldItem(name + "_axe", engName + " Axe", () -> new JAxeItem(toolTiers));
        registerHandheldItem(name + "_shovel", engName + " Shovel", () -> new JShovelItem(toolTiers));
        registerHandheldItem(name + "_hoe", engName + " Hoe", () -> new JHoeItem(toolTiers));
    }

    /**
     * Registers an armor piece. The stats of which vary on the supplier's class and the args within its constructor.
     *
     * @param name          Registry name
     * @param enName        English name
     * @param armorSupplier Supplier for the JArmorItem class
     */
    private static void registerArmorItem(String name, String enName, Supplier<JArmorItem> armorSupplier) {
        REGISTER.register(name, armorSupplier)
                .genModel(StandardItemModelParents.DEFAULT)
                .genLangEntry(enName);
    }

    /**
     * Registers a sword item. The stats of which vary on the tool material given.
     *
     * @param name     Registry name
     * @param enName   English name
     * @param material Stats for the sword item
     */
    private static void registerSwordItem(String name, String enName, JToolTiers material) {
        REGISTER.register(name, () -> new JSwordItem(material))
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
    }

    /**
     * Registers a tool. The stats of which vary on the supplier's class and the args within its constructor.
     *
     * @param name             Registry name
     * @param enName           English name
     * @param toolItemSupplier Supplier for the ToolItem class
     */
    private static void registerHandheldItem(String name, String enName, Supplier<Item> toolItemSupplier) {
        REGISTER.register(name, toolItemSupplier)
                .genModel(StandardItemModelParents.HANDHELD)
                .genLangEntry(enName);
    }

    private static void registerItem(String name, String enName, Supplier<Item> itemSupplier) {
        REGISTER.register(name, itemSupplier)
                .genModel(StandardItemModelParents.DEFAULT)
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
