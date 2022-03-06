package net.jitl.core.init.internal;

import net.jitl.common.entity.projectile.*;
import net.jitl.common.entity.vehicle.JBoat;
import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.*;
import net.jitl.common.item.curios.HeartContainerItem;
import net.jitl.common.item.curios.JCurioItem;
import net.jitl.common.item.curios.amulet.CloudwalkingAmuletItem;
import net.jitl.common.item.curios.amulet.DynasterAmuletItem;
import net.jitl.common.item.curios.amulet.IceAmuletItem;
import net.jitl.common.item.curios.catalyst.EssenceCatalystItem;
import net.jitl.common.item.curios.catalyst.RegenCatalystItem;
import net.jitl.common.item.curios.ring.JRingItem;
import net.jitl.common.item.food.CrystalAppleItem;
import net.jitl.common.item.gear.*;
import net.jitl.common.item.gear.abilities.IAbility;
import net.jitl.common.item.gear.abilities.LuniumAbility;
import net.jitl.common.item.gear.abilities.ShadiumAbility;
import net.jitl.common.item.gear.abilities.bloodcrust.BloodcrustArmorAbility;
import net.jitl.common.item.gear.abilities.bloodcrust.BloodcrustSwordAbility;
import net.jitl.common.item.gear.abilities.bloodcrust.BloodcrustToolAbility;
import net.jitl.common.item.gear.abilities.celestium.CelestiumArmorAbility;
import net.jitl.common.item.gear.abilities.korite.KoriteSwordAbility;
import net.jitl.common.item.gear.abilities.mekyum.MekyumSwordAbility;
import net.jitl.common.item.interactive.*;
import net.jitl.common.item.throwable.KnifeItem;
import net.jitl.common.item.throwable.PiercerItem;
import net.jitl.common.item.throwable.ThrowableItem;
import net.jitl.core.JITL;
import net.jitl.core.init.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import ru.timeconqueror.timecore.api.client.resource.StandardItemModelParents;
import ru.timeconqueror.timecore.api.registry.ItemRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.jitl.core.util.JItemProperties.*;

public class ItemRegistrator {
    private static final IAbility BASIC = new IAbility() {};
    private static final IAbility LUNIUM = new LuniumAbility();
    private static final IAbility SHADIUM_SWORD = new ShadiumAbility.ShadiumSwordAbility();
    private static final IAbility SHADIUM_TOOL = new ShadiumAbility.ShadiumToolAbility();
    private static final IAbility SHADIUM_ARMOR = new ShadiumAbility.ShadiumArmorAbility();
    private static final IAbility BLOODCRUST_SWORD = new BloodcrustSwordAbility();
    private static final IAbility BLOODCRUST_TOOL = new BloodcrustToolAbility();
    private static final IAbility BLOODCRUST_ARMOR = new BloodcrustArmorAbility();
    private static final IAbility CELESTIUM_ARMOR = new CelestiumArmorAbility();
    private static final IAbility KORITE_SWORD = new KoriteSwordAbility();
    private static final IAbility MEKYUM_SWORD = new MekyumSwordAbility();
    @AutoRegistrable
    private static final ItemRegister REGISTER = new ItemRegister(JITL.MODID);

    @AutoRegistrable.Init
    private static void register() {

        //OVERWORLD ITEMS
        registerItem("lore_scroll", "Lore Scroll", () -> new LoreScrollItem(itemGrouped().stacksTo(1)));
        registerItem("dreadiron_ingot", "Dreadiron Ingot");
        registerItem("sapphire", "Sapphire");
        registerItem("iridium_nugget", "Iridium Nugget");
        registerItem("lunium_ingot", "Lunium Ingot");
        registerItem("shadium_ingot", "Shadium Ingot");
        registerItem("lunium_powder", "Lunium Powder");
        registerItem("raw_shadium", "Raw shadium");
        registerItem("obsidian_rod", "Obsidian Rod");
        registerItem("green_gem", "Green Gem");
        registerItem("blue_gem", "blue Gem");
        registerItem("purple_gem", "purple Gem");
        registerItem("yellow_gem", "Yellow Gem");
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
        registerItem("peridot_gemstone", "Peridot Gemstone");
        registerItem("rimestone", "Rimestone");
        registerItem("redcurrant_berry", "Redcurrant Berry", () -> new Item(itemGrouped().food(JFoods.REDCURRANT)));
        registerHandheldRodItem("redcurrant_on_a_stick", "Redcurrant On A Stick", () -> new FoodOnAStickItem<>(itemGrouped(), JEntities.CAPYBARA_TYPE, 7));
        registerItem("crystal_apple", "Crystal Apple", () -> new CrystalAppleItem(itemGrouped().food(JFoods.CRYSTAL_APPLE).rarity(Rarity.EPIC)));
        registerItem("frostborn_soul", "Frostborn Soul");
        registerItem("ice_amulet", "Amulet of Glacial Bloodlust", () -> new IceAmuletItem(itemGrouped().stacksTo(1)));
        registerItem("padlock", "Padlock", () -> new Item(itemGrouped().stacksTo(16)));

        registerItem("mud_ball", "Mud Ball", () -> new ThrowableItem(itemGrouped(),
                (world, thrower) -> new FloroMudEntity(JEntities.FLORO_MUD_TYPE, world, thrower, 0.0F)).setSound(() -> SoundEvents.SNOWBALL_THROW));

        registerItem("tartberry", "Tartberry", () -> new Item(itemGrouped().food(JFoods.TARTBERRY)));

        registerItem("fried_ghast_tentacle", "Fried Ghast Tentacle", () -> new Item(itemGrouped().food(JFoods.FRIED_GHAST_TENTACLE)));
        registerItem("flaming_ghast_tentacle", "Flaming Ghast Tentacle", () -> new Item(itemGrouped().food(JFoods.FLAMING_GHAST_TENTACLE)));
        registerItem("fried_flaming_ghast_tentacle", "Fried Flaming Ghast Tentacle", () -> new Item(itemGrouped().food(JFoods.FRIED_FLAMING_GHAST_TENTACLE)));
        registerItem("ghast_tentacle", "Ghast Tentacle", () -> new Item(itemGrouped().food(JFoods.GHAST_TENTACLE)));
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


        registerHandheldItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(rangedWeaponsGrouped().stacksTo(1),
                (world, thrower) -> new ConjuringProjectileEntity(JEntities.CONJURING_PROJECTILE_TYPE, world, thrower, 0.0F)));

        registerHandheldItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(rangedWeaponsGrouped().stacksTo(1),
                (world, thrower) -> new EssenciaProjectileEntity(JEntities.ESSENCIA_PROJECTILE_TYPE, world, thrower, 0.0F)));

        //todo: change loot pouches to use JITL loot tables
        registerItem("loot_pouch_basic", "Loot Pouch", () -> new LootItem(JLootTables.LOOT_POUCH_BASIC, false));
        registerItem("loot_pouch_gold", "Gold Loot Pouch", () -> new LootItem(BuiltInLootTables.FISHING_TREASURE, true));
        registerItem("loot_pouch_diamond", "Diamond Loot Pouch", () -> new LootItem(BuiltInLootTables.END_CITY_TREASURE, true));

        registerItem("flame_coin", "Flame Coin", FlameCoinItem::new);

        registerItem("ancient_piece", "Ancient Piece", AncientPieceItem::new);
        registerItem("ancient_shard", "Ancient Shard", AncientPieceItem::new);
        registerItem("ancient_chunk", "Ancient Chunk", AncientPieceItem::new);
        registerItem("ancient_fragment", "Ancient Fragment", AncientPieceItem::new);

        //NETHER ITEMS
        registerItem("bloodcrust_ingot", "Bloodcrust Ingot");
        registerItem("raw_bloodcrust", "Raw Bloodcrust");
        registerItem("firestone_shard", "Firestone Shard", () -> new JFuelItem(itemGrouped().fireResistant(), 200));
        registerItem("firestone_clump", "Firestone Clump", () -> new JFuelItem(itemGrouped().fireResistant(), 2000));
        registerItem("warped_quartz", "Warped Quartz");
        registerItem("crimson_quartz", "Crimson Quartz");
        registerItem("blood", "Blood");
        registerItem("powder_of_essencia", "Powder Of Essencia");
        registerItem("boil_key", "Boil Lock Key");

        //END ITEMS
        registerItem("enderillium_shard", "Enderillium Shard");

        //BOILING POINT ITEMS
        registerItem("ash", "Ash");

        //EUCA ITEMS
        registerItem("celestium_gemstone", "Celestium Gemstone");
        registerItem("korite_gemstone", "Korite Gemstone");
        registerItem("mekyum_gemstone", "Mekyum Gemstone");
        registerItem("storon_gemstone", "Storon Gemstone");

        //DEVELOPER ITEMS
        registerItem("test_bug", "Test Bug", DebugItem::new);
        registerHandheldItem("creative_sword", "Creative Sword", () -> new JSwordItem(JToolTiers.CREATIVE, BASIC));

        //Mat armor and tools
        addMaterialToolsArmorWeapons("Sapphire", JToolTiers.SAPPHIRE, JArmorMaterial.SAPPHIRE, BASIC, BASIC, BASIC);
        addMaterialToolsArmorWeapons("Lunium", JToolTiers.LUNIUM, JArmorMaterial.LUNIUM, LUNIUM, LUNIUM, LUNIUM);
        addMaterialToolsArmorWeapons("Shadium", JToolTiers.SHADIUM, JArmorMaterial.SHADIUM, SHADIUM_SWORD, SHADIUM_TOOL, SHADIUM_ARMOR);
        addMaterialToolsArmorWeapons("Bloodcrust", JToolTiers.BLOODCRUST, JArmorMaterial.BLOODCRUST, BLOODCRUST_SWORD, BLOODCRUST_TOOL, BLOODCRUST_ARMOR);
        addMaterialToolsArmorWeapons("Celestium", JToolTiers.CELESTIUM, JArmorMaterial.CELESTIUM, BASIC, BASIC, CELESTIUM_ARMOR);

        //Mat tools
        addMaterialToolsWeapons("Korite", JToolTiers.KORITE, KORITE_SWORD, BASIC);
        addMaterialToolsWeapons("Mekyum", JToolTiers.MEKYUM, MEKYUM_SWORD, BASIC);
        addMaterialToolsWeapons("Storon", JToolTiers.STORON, BASIC, BASIC);

        //Armor
        addArmor("Dreadiron", JArmorMaterial.DREADIRON, BASIC);


        registerItem("molten_knife", "Molten Knife", () ->
                new KnifeItem(rangedWeaponsGrouped(), (worldIn, owner, stack) ->
                        new KnifeEntity(worldIn, owner, stack, 10.0F)));

        registerItem("iron_throwing_knife", "Iron Throwing Knife", () ->
                new KnifeItem(rangedWeaponsGrouped(), (worldIn, owner, stack) ->
                        new KnifeEntity(worldIn, owner, stack, 3.0F)));

        registerItem("euca_piercer", "Euca Piercer", () ->
                new PiercerItem(rangedWeaponsGrouped().durability(50), (worldIn, owner, stack)
                        -> new PiercerEntity(owner, worldIn, stack, 3, 10.0F)));

        registerItem("piercer", "Piercer", () ->
                new PiercerItem(rangedWeaponsGrouped().durability(128), (worldIn, owner, stack) ->
                        new PiercerEntity(owner, worldIn, stack, 3, 3.0F)));

        registerItem("skull_of_decay", "Skull Of Decay", () -> new JCurioItem(itemGrouped().stacksTo(1).durability(256)).ability(true));

        registerItem("miners_pearl", "Miners Pearl", () -> new MinersPearlItem(itemGrouped().stacksTo(1).durability(1)));

        registerItem("heart_container_small", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(1));
        registerItem("heart_container_medium", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(4));
        registerItem("heart_container_large", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(8));
        registerItem("heart_container_ultimate", "Heart Container", () -> new HeartContainerItem(itemGrouped().stacksTo(1)).health(16));

        registerItem("ring_of_poison", "Ring of Poison", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.POISON));
        registerItem("ring_of_blindness", "Ring of Blindness", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.BLINDNESS));
        registerItem("ring_of_harming", "Ring of Harming", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.HARM));
        registerItem("ring_of_mining_fatigue", "Ring of Mining Fatigue", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.DIG_SLOWDOWN));
        registerItem("ring_of_nausea", "Ring of Nausea", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.CONFUSION));
        registerItem("ring_of_slowness", "Ring of Slowness", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.MOVEMENT_SLOWDOWN));
        registerItem("ring_of_withering", "Ring of Withering", () -> new JRingItem(itemGrouped().stacksTo(1)).effect(() -> MobEffects.WITHER));

        registerItem("dynaster_amulet", "Amulet of the Dynaster", () -> new DynasterAmuletItem(itemGrouped().stacksTo(1)));
        registerItem("cloudwalker_amulet", "Cloudwalker's Amulet", () -> new CloudwalkingAmuletItem(itemGrouped().stacksTo(1)));

        registerItem("very_weak_essence_crystal", "Very Weak Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(1F));
        registerItem("weak_essence_crystal", "Weak Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(2.5F));
        registerItem("strong_essence_crystal", "Strong Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(5F));
        registerItem("very_strong_essence_crystal", "Very Strong Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(10F));

        registerItem("breathing_stone", "Breathing Stone", () -> new RegenCatalystItem(itemGrouped().stacksTo(1)).speed(0.0112F));

        registerItem("eye_of_the_blizzard", "Eye Of The Blizzard", () -> new JCurioItem(itemGrouped().stacksTo(1)).overview(true));

        registerItem("sulphur_powder", "Sulphur Powder");
        registerItem("blazium_ingot", "Blazium Ingot");
        registerItem("raw_blazium", "Raw Blazium");

        registerItem("boiling_skull", "Charred Skull");

        registerItem("pottery_shard", "Pottery Shard");

        registerItem("music_disc_haunt_muskie_2", "Music Disc", () -> new RecordItem(1, JSounds.HAUNT_MUSKIE_2::get, itemGrouped().stacksTo(1)));
        registerItem("music_disc_jitl_theme", "Music Disc", () -> new RecordItem(1, JSounds.MENU_MUSIC::get, itemGrouped().stacksTo(1)));
        registerItem("music_disc_snowflakesss", "Music Disc", () -> new RecordItem(1, JSounds.SNOWFLAKESSS::get, itemGrouped().stacksTo(1)));

        registerItem("gold_euca_boat", "Golden Euca Boat", () -> new JBoatItem(JBoat.Type.GOLD_EUCA, itemGrouped()));
        registerItem("brown_euca_boat", "Brown Euca Boat", () -> new JBoatItem(JBoat.Type.BROWN_EUCA, itemGrouped()));
        registerItem("frozen_boat", "Frosty Boat", () -> new JBoatItem(JBoat.Type.FROZEN, itemGrouped()));
        registerItem("burned_boat", "Burned Boat", () -> new JBoatItem(JBoat.Type.BURNED, itemGrouped()));
    }

    public static void addMaterialToolsWeapons(String name, JToolTiers tiers, IAbility sworldAbility, IAbility toolAbility) {
        registerHandheldItem(name.toLowerCase() + "_sword", name + " Sword", () -> new JSwordItem(tiers, sworldAbility));
        registerHandheldItem(name.toLowerCase() + "_pickaxe", name + " Pickaxe", () -> new JPickaxeItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_axe", name + " Axe", () -> new JAxeItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_shovel", name + " Shovel", () -> new JShovelItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_hoe", name + " Hoe", () -> new JHoeItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_multitool", name + " Multitool", () -> new MultitoolItem(tiers, toolAbility));
    }

    public static void addMaterialToolsArmorWeapons(String name, JToolTiers tiers, JArmorMaterial armorMaterial, IAbility sworldAbility, IAbility toolAbility, IAbility armorAbility) {
        registerHandheldItem(name.toLowerCase() + "_sword", name + " Sword", () -> new JSwordItem(tiers, sworldAbility));
        registerHandheldItem(name.toLowerCase() + "_pickaxe", name + " Pickaxe", () -> new JPickaxeItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_axe", name + " Axe", () -> new JAxeItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_shovel", name + " Shovel", () -> new JShovelItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_hoe", name + " Hoe", () -> new JHoeItem(tiers, toolAbility));
        registerHandheldItem(name.toLowerCase() + "_multitool", name + " Multitool", () -> new MultitoolItem(tiers, toolAbility));
        registerArmorItem(name.toLowerCase() + "_helmet", name + " Helmet", () -> new JArmorItem(armorMaterial, EquipmentSlot.HEAD, armorAbility));
        registerArmorItem(name.toLowerCase() + "_chestplate", name + " Chestplate", () -> new JArmorItem(armorMaterial, EquipmentSlot.CHEST, armorAbility));
        registerArmorItem(name.toLowerCase() + "_leggings", name + " Leggings", () -> new JArmorItem(armorMaterial, EquipmentSlot.LEGS, armorAbility));
        registerArmorItem(name.toLowerCase() + "_boots", name + " Boots", () -> new JArmorItem(armorMaterial, EquipmentSlot.FEET, armorAbility));
    }

    public static void addArmor(String name, JArmorMaterial armorMaterial, IAbility armorAbility) {
        registerArmorItem(name.toLowerCase() + "_helmet", name + " Helmet", () -> new JArmorItem(armorMaterial, EquipmentSlot.HEAD, armorAbility));
        registerArmorItem(name.toLowerCase() + "_chestplate", name + " Chestplate", () -> new JArmorItem(armorMaterial, EquipmentSlot.CHEST, armorAbility));
        registerArmorItem(name.toLowerCase() + "_leggings", name + " Leggings", () -> new JArmorItem(armorMaterial, EquipmentSlot.LEGS, armorAbility));
        registerArmorItem(name.toLowerCase() + "_boots", name + " Boots", () -> new JArmorItem(armorMaterial, EquipmentSlot.FEET, armorAbility));
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

    private static void registerHandheldRodItem(String name, String enName, Supplier<Item> toolItemSupplier) {
        REGISTER.register(name, toolItemSupplier)
                .model(StandardItemModelParents.HANDHELD_ROD)
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
