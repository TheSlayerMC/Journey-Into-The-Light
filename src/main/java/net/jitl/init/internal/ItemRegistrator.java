package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.common.entity.projectile.*;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.*;
import net.jitl.common.item.curios.HeartContainerItem;
import net.jitl.common.item.curios.JCurioItem;
import net.jitl.common.item.curios.amulet.DynasterAmuletItem;
import net.jitl.common.item.curios.catalyst.EssenceCatalystItem;
import net.jitl.common.item.curios.catalyst.RegenCatalystItem;
import net.jitl.common.item.curios.ring.JRingItem;
import net.jitl.init.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import ru.timeconqueror.timecore.api.client.resource.StandardItemModelParents;
import ru.timeconqueror.timecore.api.registry.ItemRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.jitl.util.JItemProperties.itemGrouped;
import static net.jitl.util.JItemProperties.rangedWeaponsGrouped;

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
        registerItem("ancient_piece", "Ancient Piece");
        registerItem("ancient_shard", "Ancient Shard");
        registerItem("ancient_chunk", "Ancient Chunk");
        registerItem("ancient_fragment", "Ancient Fragment");
        registerItem("balmy_teardrop", "Balmy Teardrop");
        registerItem("cave_crystal", "Cave Crystal");
        registerItem("stone_clump", "Stone Clump");
        registerItem("crystal_ball", "Crystal Ball");
        registerItem("cave_dust", "Cave Dust");
        registerItem("smithstone", "Smithstone");
        registerItem("bleedstone", "Bleedstone");
        registerItem("soulstone", "Soulstone");
        registerItem("magic_dust", "Magic Dust");
        registerItem("demonic_skull", "Demonic Skull");
        registerItem("glossy_eye", "Glossy Eye");
        registerItem("volcanic_stone", "Volcanic Stone");
        registerItem("sentacoin", "Sentacoin");//TODO make an entity
        registerItem("sentacoin_bag", "Sentacoin Bag", () -> new Item(itemGrouped()), "item/sentacoin_bag_model");

        registerItem("mud_ball", "Mud Ball", () -> new ThrowableItem(itemGrouped(),
                (world, thrower) -> new FloroMudEntity(JEntities.FLORO_MUD_TYPE, world, thrower, 0.0F)).setSound(() -> SoundEvents.SNOWBALL_THROW));

        registerItem("bradberry", "Bradberry", () -> new Item(itemGrouped().food(JFoods.BRADBERRY)));

        registerItem("fried_ghast_tenticle", "Fried Ghast Tenticle", () -> new Item(itemGrouped().food(JFoods.FRIED_GHAST_TENTACLE)));
        registerItem("flaming_ghast_tenticle", "Flaming Ghast Tenticle", () -> new Item(itemGrouped().food(JFoods.FLAMING_GHAST_TENTACLE)));
        registerItem("fried_flaming_ghast_tenticle", "Fried Flaming Ghast Tenticle", () -> new Item(itemGrouped().food(JFoods.FRIED_FLAMING_GHAST_TENTACLE)));
        registerItem("ghast_tenticle", "Ghast Tenticle", () -> new Item(itemGrouped().food(JFoods.GHAST_TENTACLE)));
        registerItem("fried_egg", "Fried Egg", () -> new Item(itemGrouped().food(JFoods.FRIED_EGG)));
        registerItem("mint_candy_cane", "Mint Candy Cane", () -> new Item(itemGrouped().food(JFoods.MINT_CANDY_CANE)));
        registerItem("fruity_candy_cane", "Fruity Candy Cane", () -> new Item(itemGrouped().food(JFoods.FRUITY_CANDY_CANE)));
        registerItem("cherry_candy_cane", "Cherry Candy Cane", () -> new Item(itemGrouped().food(JFoods.CHERRY_CANDY_CANE)));
        registerItem("peppermint", "Peppermint", () -> new Item(itemGrouped().food(JFoods.PEPPERMINT)));
        registerItem("jellybeans", "Jellybeans", () -> new Item(itemGrouped().food(JFoods.JELLYBEANS)));
        registerItem("chocolate", "Chocolate", () -> new Item(itemGrouped().food(JFoods.CHOCOLATE)));
        registerItem("vanilla_wafer", "Vanilla Wafer", () -> new Item(itemGrouped().food(JFoods.VANILLA_WAFER)));
        registerItem("euca_meat", "Euca Meat", () -> new Item(itemGrouped().food(JFoods.EUCA_MEAT)));

        registerItem("hongoshroom", "Hongoshroom", () -> new Item(itemGrouped().food(JFoods.HONGOSROOM)));
        registerItem("honglowshroom", "Honglowshroom", () -> new Item(itemGrouped().food(JFoods.HONGLOWSROOM)));

        registerItem("tomato", "Tomato", () -> new Item(itemGrouped().food(JFoods.TOMATO)));
        registerItem("tomato_seeds", "Tomato Seeds", () -> new SeedsItem(JBlocks.TOMATO_CROP));

        registerItem("floro_pedal", "Floro Pedal", () -> new Item(itemGrouped().food(JFoods.FLORO_PEDAL)));
        registerItem("floro_seeds", "Floro Seeds", () -> new SeedsItem(JBlocks.FLORO_CROP));

        registerItem("lunium_powder", "Lunium Powder");

        registerHandheldItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(rangedWeaponsGrouped(),
                (world, thrower) -> new ConjuringProjectileEntity(JEntities.CONJURING_PROJECTILE_TYPE, world, thrower, 0.0F)));

        registerHandheldItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(rangedWeaponsGrouped(),
                (world, thrower) -> new EssenciaProjectileEntity(JEntities.ESSENCIA_PROJECTILE_TYPE, world, thrower, 0.0F)));

        registerItem("loot_pouch_basic", "Loot Pouch", () -> new LootItem(JLootTables.LOOT_POUCH_BASIC, false));
        registerItem("loot_pouch_gold", "Gold Loot Pouch", () -> new LootItem(JLootTables.LOOT_POUCH_GOLD, true));
        registerItem("loot_pouch_diamond", "Diamond Loot Pouch", () -> new LootItem(JLootTables.LOOT_POUCH_DIAMOND, true));

        //NETHER ITEMS
        registerItem("bloodcrust_ingot", "Bloodcrust Ingot");
        registerItem("firestone_shard", "Firestone Shard", () -> new JFuelItem(itemGrouped().fireResistant(), 200));
        registerItem("firestone_clump", "Firestone Clump", () -> new JFuelItem(itemGrouped().fireResistant(), 2000));
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

        registerItem("molten_knife", "Molten Knife", () -> new ThrowableItem(rangedWeaponsGrouped(), (worldIn, owner) -> new EntityMoltenKnife(worldIn, owner).withBaseDamage(10)).setSound(JSounds.STAFF_0::get));

        registerItem("euca_piercer", "Euca Piercer", () -> new ThrowableItem(rangedWeaponsGrouped(), (world, livingEntity) -> new EucaPiercerEntity(world, livingEntity, 10, 5)).setSound(JSounds.STAFF_0::get));
        registerItem("skull_of_decay", "Skull Of Decay", () -> new JCurioItem(itemGrouped().stacksTo(1)).ability(true).drawback(true).overview(true));

        registerItem("heart_container_small", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(1).ability(true).drawback(true).overview(true));
        registerItem("heart_container_medium", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(4));
        registerItem("heart_container_large", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(8));
        registerItem("heart_container_ultimate", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(16));

        registerItem("ring_of_poison", "Ring of Poison", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.POISON::getEffect));
        registerItem("ring_of_blindness", "Ring of Blindness", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.BLINDNESS::getEffect));
        registerItem("ring_of_harming", "Ring of Harming", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.HARM::getEffect));
        registerItem("ring_of_mining_fatigue", "Ring of Mining Fatigue", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.DIG_SLOWDOWN::getEffect));
        registerItem("ring_of_nausea", "Ring of Nausea", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.CONFUSION::getEffect));
        registerItem("ring_of_slowness", "Ring of Slowness", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.MOVEMENT_SLOWDOWN::getEffect));
        registerItem("ring_of_withering", "Ring of Withering", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(Effects.WITHER::getEffect));

        registerItem("dynaster_amulet", "Dynaster Amulet", () -> new DynasterAmuletItem(itemGrouped().stacksTo(1)));
        registerItem("weak_essence_catalyst", "Essence Catalyst", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(10F));
        registerItem("weak_regen_catalyst", "Regeneration Catalyst", () -> new RegenCatalystItem(itemGrouped().stacksTo(1)).speed(0.0112F));
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
                .model(StandardItemModelParents.DEFAULT)
                .name(enName);
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
                .model(StandardItemModelParents.HANDHELD)
                .name(enName);
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
                .model(StandardItemModelParents.HANDHELD)
                .name(enName);
    }

    private static void registerItem(String name, String enName, Supplier<Item> itemSupplier) {
        REGISTER.register(name, itemSupplier)
                .model(StandardItemModelParents.DEFAULT)
                .name(enName);
    }

    private static void registerItem(String name, String enName, Supplier<Item> itemSupplier, String itemLoc) {
        REGISTER.register(name, itemSupplier)
                .model(JITL.iml(itemLoc))
                .name(enName);
    }

    private static void registerItem(String name, String enName) {
        Function<Item.Properties, ? extends Item> itemFactory = Item::new;

        REGISTER.register(name, () -> itemFactory.apply(itemGrouped()))
                .model(StandardItemModelParents.DEFAULT)
                .name(enName);
    }

    private static void registerItem(String name, String enName, Function<Item.Properties, ? extends Item> itemFactory) {
        REGISTER.register(name, () -> itemFactory.apply(itemGrouped()))
                .model(StandardItemModelParents.DEFAULT)
                .name(enName);
    }

    private static void registerItem(String name, String enName, Function<Item.Properties, ? extends Item> itemFactory, StandardItemModelParents modelType) {
        REGISTER.register(name, () -> itemFactory.apply(itemGrouped()))
                .model(modelType)
                .name(enName);
    }
}
