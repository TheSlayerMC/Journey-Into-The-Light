package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.common.entity.projectile.*;
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
import net.jitl.common.item.interactive.MinersPearlItem;
import net.jitl.common.item.throwable.KnifeItem;
import net.jitl.common.item.throwable.PiercerItem;
import net.jitl.common.item.throwable.ThrowableItem;
import net.jitl.init.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.loot.LootTables;
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

    @AutoRegistrable.InitMethod
    private static void register() {

        //OVERWORLD ITEMS
        registerItem("dreadiron_ingot", "Dreadiron Ingot");
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
        registerItem("peridot_gemstone", "Peridot Gemstone");
        registerItem("rimestone", "Rimestone");
        registerItem("ice_amulet", "Ice Amulet", () -> new IceAmuletItem(itemGrouped().stacksTo(1)));

        registerItem("mud_ball", "Mud Ball", () -> new ThrowableItem(itemGrouped(),
                (world, thrower) -> new FloroMudEntity(JEntities.FLORO_MUD_TYPE, world, thrower, 0.0F)).setSound(() -> SoundEvents.SNOWBALL_THROW));

        registerItem("bradberry", "Bradberry", () -> new Item(itemGrouped().food(JFoods.BRADBERRY)));

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

        registerItem("lunium_powder", "Lunium Powder");

        registerHandheldItem("staff_of_conjuring", "Staff of Conjuring", () -> new StaffItem(rangedWeaponsGrouped().stacksTo(1),
                (world, thrower) -> new ConjuringProjectileEntity(JEntities.CONJURING_PROJECTILE_TYPE, world, thrower, 0.0F)));

        registerHandheldItem("staff_of_essencia", "Staff of Essencia", () -> new StaffItem(rangedWeaponsGrouped().stacksTo(1),
                (world, thrower) -> new EssenciaProjectileEntity(JEntities.ESSENCIA_PROJECTILE_TYPE, world, thrower, 0.0F)));

        //todo: change loot pouches to use JITL loot tables
        registerItem("loot_pouch_basic", "Loot Pouch", () -> new LootItem(JLootTables.LOOT_POUCH_BASIC, false));
        registerItem("loot_pouch_gold", "Gold Loot Pouch", () -> new LootItem(LootTables.DESERT_PYRAMID, true));
        registerItem("loot_pouch_diamond", "Diamond Loot Pouch", () -> new LootItem(LootTables.END_CITY_TREASURE, true));

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

        //SAPPHIRE
        registerHandheldItem("sapphire_sword", "Sapphire Sword", () -> new JSwordItem(JToolTiers.SAPPHIRE, BASIC));
        registerHandheldItem("sapphire_pickaxe", "Sapphire Pickaxe", () -> new JPickaxeItem(JToolTiers.SAPPHIRE, BASIC));
        registerHandheldItem("sapphire_axe", "Sapphire Axe", () -> new JAxeItem(JToolTiers.SAPPHIRE, BASIC));
        registerHandheldItem("sapphire_shovel", "Sapphire Shovel", () -> new JShovelItem(JToolTiers.SAPPHIRE, BASIC));
        registerHandheldItem("sapphire_hoe", "Sapphire Hoe", () -> new JHoeItem(JToolTiers.SAPPHIRE, BASIC));
        registerHandheldItem("sapphire_multitool", "Sapphire Multitool", () -> new MultitoolItem(JToolTiers.SAPPHIRE, BASIC));
        registerArmorItem("sapphire_helmet", "Sapphire Helmet", () -> new JArmorItem(JArmorMaterial.SAPPHIRE, EquipmentSlotType.HEAD, BASIC));
        registerArmorItem("sapphire_chestplate", "Sapphire Chestplate", () -> new JArmorItem(JArmorMaterial.SAPPHIRE, EquipmentSlotType.CHEST, BASIC));
        registerArmorItem("sapphire_leggings", "Sapphire Leggings", () -> new JArmorItem(JArmorMaterial.SAPPHIRE, EquipmentSlotType.LEGS, BASIC));
        registerArmorItem("sapphire_boots", "Sapphire Boots", () -> new JArmorItem(JArmorMaterial.SAPPHIRE, EquipmentSlotType.FEET, BASIC));
        
        //LUNIUM
        registerHandheldItem("lunium_sword", "Lunium Sword", () -> new JSwordItem(JToolTiers.LUNIUM, LUNIUM));
        registerHandheldItem("lunium_pickaxe", "Lunium Pickaxe", () -> new JPickaxeItem(JToolTiers.LUNIUM, LUNIUM));
        registerHandheldItem("lunium_axe", "Lunium Axe", () -> new JAxeItem(JToolTiers.LUNIUM, LUNIUM));
        registerHandheldItem("lunium_shovel", "Lunium Shovel", () -> new JShovelItem(JToolTiers.LUNIUM, LUNIUM));
        registerHandheldItem("lunium_hoe", "Lunium Hoe", () -> new JHoeItem(JToolTiers.LUNIUM, LUNIUM));
        registerHandheldItem("lunium_multitool", "Lunium Multitool", () -> new MultitoolItem(JToolTiers.LUNIUM, LUNIUM));
        registerArmorItem("lunium_helmet", "Lunium Helmet", () -> new JArmorItem(JArmorMaterial.LUNIUM, EquipmentSlotType.HEAD, LUNIUM));
        registerArmorItem("lunium_chestplate", "Lunium Chestplate", () -> new JArmorItem(JArmorMaterial.LUNIUM, EquipmentSlotType.CHEST, LUNIUM));
        registerArmorItem("lunium_leggings", "Lunium Leggings", () -> new JArmorItem(JArmorMaterial.LUNIUM, EquipmentSlotType.LEGS, LUNIUM));
        registerArmorItem("lunium_boots", "Lunium Boots", () -> new JArmorItem(JArmorMaterial.LUNIUM, EquipmentSlotType.FEET, LUNIUM));
        
        //SHADIUM
        registerHandheldItem("shadium_sword", "Shadium Sword", () -> new JSwordItem(JToolTiers.SHADIUM, SHADIUM_SWORD));
        registerHandheldItem("shadium_pickaxe", "Shadium Pickaxe", () -> new JPickaxeItem(JToolTiers.SHADIUM, SHADIUM_TOOL));
        registerHandheldItem("shadium_axe", "Shadium Axe", () -> new JAxeItem(JToolTiers.SHADIUM, SHADIUM_TOOL));
        registerHandheldItem("shadium_shovel", "Shadium Shovel", () -> new JShovelItem(JToolTiers.SHADIUM, SHADIUM_TOOL));
        registerHandheldItem("shadium_hoe", "Shadium Hoe", () -> new JHoeItem(JToolTiers.SHADIUM, SHADIUM_TOOL));
        registerHandheldItem("shadium_multitool", "Shadium Multitool", () -> new MultitoolItem(JToolTiers.SHADIUM, SHADIUM_TOOL));
        registerArmorItem("shadium_helmet", "Shadium Helmet", () -> new JArmorItem(JArmorMaterial.SHADIUM, EquipmentSlotType.HEAD, SHADIUM_ARMOR));
        registerArmorItem("shadium_chestplate", "Shadium Chestplate", () -> new JArmorItem(JArmorMaterial.SHADIUM, EquipmentSlotType.CHEST, SHADIUM_ARMOR));
        registerArmorItem("shadium_leggings", "Shadium Leggings", () -> new JArmorItem(JArmorMaterial.SHADIUM, EquipmentSlotType.LEGS, SHADIUM_ARMOR));
        registerArmorItem("shadium_boots", "Shadium Boots", () -> new JArmorItem(JArmorMaterial.SHADIUM, EquipmentSlotType.FEET, SHADIUM_ARMOR));

        //BLOODCRUST
        registerHandheldItem("bloodcrust_sword", "Bloodcrust Sword", () -> new JSwordItem(JToolTiers.BLOODCRUST, BLOODCRUST_SWORD));
        registerHandheldItem("bloodcrust_pickaxe", "Bloodcrust Pickaxe", () -> new JPickaxeItem(JToolTiers.BLOODCRUST, BLOODCRUST_TOOL));
        registerHandheldItem("bloodcrust_axe", "Bloodcrust Axe", () -> new JAxeItem(JToolTiers.BLOODCRUST, BLOODCRUST_TOOL));
        registerHandheldItem("bloodcrust_shovel", "Bloodcrust Shovel", () -> new JShovelItem(JToolTiers.BLOODCRUST, BLOODCRUST_TOOL));
        registerHandheldItem("bloodcrust_hoe", "Bloodcrust Hoe", () -> new JHoeItem(JToolTiers.BLOODCRUST, BLOODCRUST_TOOL));
        registerHandheldItem("bloodcrust_multitool", "Bloodcrust Multitool", () -> new MultitoolItem(JToolTiers.BLOODCRUST, BLOODCRUST_TOOL));
        registerArmorItem("bloodcrust_helmet", "Bloodcrust Helmet", () -> new JArmorItem(JArmorMaterial.BLOODCRUST, EquipmentSlotType.HEAD, BLOODCRUST_ARMOR));
        registerArmorItem("bloodcrust_chestplate", "Bloodcrust Chestplate", () -> new JArmorItem(JArmorMaterial.BLOODCRUST, EquipmentSlotType.CHEST, BLOODCRUST_ARMOR));
        registerArmorItem("bloodcrust_leggings", "Bloodcrust Leggings", () -> new JArmorItem(JArmorMaterial.BLOODCRUST, EquipmentSlotType.LEGS, BLOODCRUST_ARMOR));
        registerArmorItem("bloodcrust_boots", "Bloodcrust Boots", () -> new JArmorItem(JArmorMaterial.BLOODCRUST, EquipmentSlotType.FEET, BLOODCRUST_ARMOR));

        //CELESTIUM
        registerHandheldItem("celestium_sword", "Celestium Sword", () -> new JSwordItem(JToolTiers.CELESTIUM, BASIC));
        registerHandheldItem("celestium_pickaxe", "Celestium Pickaxe", () -> new JPickaxeItem(JToolTiers.CELESTIUM, BASIC));
        registerHandheldItem("celestium_axe", "Celestium Axe", () -> new JAxeItem(JToolTiers.CELESTIUM, BASIC));
        registerHandheldItem("celestium_shovel", "Celestium Shovel", () -> new JShovelItem(JToolTiers.CELESTIUM, BASIC));
        registerHandheldItem("celestium_hoe", "Celestium Hoe", () -> new JHoeItem(JToolTiers.CELESTIUM, BASIC));
        registerHandheldItem("celestium_multitool", "Celestium Multitool", () -> new MultitoolItem(JToolTiers.CELESTIUM, BASIC));
        registerArmorItem("celestium_helmet", "Celestium Helmet", () -> new JArmorItem(JArmorMaterial.CELESTIUM, EquipmentSlotType.HEAD, CELESTIUM_ARMOR));
        registerArmorItem("celestium_chestplate", "Celestium Chestplate", () -> new JArmorItem(JArmorMaterial.CELESTIUM, EquipmentSlotType.CHEST, CELESTIUM_ARMOR));
        registerArmorItem("celestium_leggings", "Celestium Leggings", () -> new JArmorItem(JArmorMaterial.CELESTIUM, EquipmentSlotType.LEGS, CELESTIUM_ARMOR));
        registerArmorItem("celestium_boots", "Celestium Boots", () -> new JArmorItem(JArmorMaterial.CELESTIUM, EquipmentSlotType.FEET, CELESTIUM_ARMOR));
        
        //KORITE
        registerHandheldItem("korite_sword", "Korite Sword", () -> new JSwordItem(JToolTiers.KORITE, KORITE_SWORD));
        registerHandheldItem("korite_pickaxe", "Korite Pickaxe", () -> new JPickaxeItem(JToolTiers.KORITE, BASIC));
        registerHandheldItem("korite_axe", "Korite Axe", () -> new JAxeItem(JToolTiers.KORITE, BASIC));
        registerHandheldItem("korite_shovel", "Korite Shovel", () -> new JShovelItem(JToolTiers.KORITE, BASIC));
        registerHandheldItem("korite_hoe", "Korite Hoe", () -> new JHoeItem(JToolTiers.KORITE, BASIC));
        registerHandheldItem("korite_multitool", "Korite Multitool", () -> new MultitoolItem(JToolTiers.KORITE, BASIC));

        //MEKYUM
        registerHandheldItem("mekyum_sword", "Mekyum Sword", () -> new JSwordItem(JToolTiers.MEKYUM, MEKYUM_SWORD));
        registerHandheldItem("mekyum_pickaxe", "Mekyum Pickaxe", () -> new JPickaxeItem(JToolTiers.MEKYUM, BASIC));
        registerHandheldItem("mekyum_axe", "Mekyum Axe", () -> new JAxeItem(JToolTiers.MEKYUM, BASIC));
        registerHandheldItem("mekyum_shovel", "Mekyum Shovel", () -> new JShovelItem(JToolTiers.MEKYUM, BASIC));
        registerHandheldItem("mekyum_hoe", "Mekyum Hoe", () -> new JHoeItem(JToolTiers.MEKYUM, BASIC));
        registerHandheldItem("mekyum_multitool", "Mekyum Multitool", () -> new MultitoolItem(JToolTiers.MEKYUM, BASIC));

        //STORON
        registerHandheldItem("storon_sword", "Storon Sword", () -> new JSwordItem(JToolTiers.STORON, BASIC));
        registerHandheldItem("storon_pickaxe", "Storon Pickaxe", () -> new JPickaxeItem(JToolTiers.STORON, BASIC));
        registerHandheldItem("storon_axe", "Storon Axe", () -> new JAxeItem(JToolTiers.STORON, BASIC));
        registerHandheldItem("storon_shovel", "Storon Shovel", () -> new JShovelItem(JToolTiers.STORON, BASIC));
        registerHandheldItem("storon_hoe", "Storon Hoe", () -> new JHoeItem(JToolTiers.STORON, BASIC));
        registerHandheldItem("storon_multitool", "Storon Multitool", () -> new MultitoolItem(JToolTiers.STORON, BASIC));

        //DREADIRON
        registerArmorItem("dreadiron_helmet", "Dreadiron Helmet", () -> new JArmorItem(JArmorMaterial.DREADIRON, EquipmentSlotType.HEAD, BASIC));
        registerArmorItem("dreadiron_chestplate", "Dreadiron Chestplate", () -> new JArmorItem(JArmorMaterial.DREADIRON, EquipmentSlotType.CHEST, BASIC));
        registerArmorItem("dreadiron_leggings", "Dreadiron Leggings", () -> new JArmorItem(JArmorMaterial.DREADIRON, EquipmentSlotType.LEGS, BASIC));
        registerArmorItem("dreadiron_boots", "Dreadiron Boots", () -> new JArmorItem(JArmorMaterial.DREADIRON, EquipmentSlotType.FEET, BASIC));

        registerHandheldItem("creative_sword", "Creative Sword", () -> new JSwordItem(JToolTiers.CREATIVE, BASIC));

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

        registerItem("skull_of_decay", "Skull Of Decay", () -> new JCurioItem(itemGrouped().stacksTo(1)).ability(true).drawback(true).overview(true));

        registerItem("miners_pearl", "Miners Pearl", () -> new MinersPearlItem(itemGrouped().stacksTo(1)));

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

        registerItem("dynaster_amulet", "Amulet of the Dynaster", () -> new DynasterAmuletItem(itemGrouped().stacksTo(1)));
        registerItem("cloudwalker_amulet", "Cloudwalker's Amulet", () -> new CloudwalkingAmuletItem(itemGrouped().stacksTo(1)));

        registerItem("very_weak_essence_crystal", "Very Weak Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(2F));
        registerItem("weak_essence_crystal", "Weak Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(5F));
        registerItem("strong_essence_crystal", "Strong Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(10F));
        registerItem("very_strong_essence_crystal", "Very Strong Essence Crystal", () -> new EssenceCatalystItem(itemGrouped().stacksTo(1)).essence(15F));

        registerItem("breathing_stone", "Breathing Stone", () -> new RegenCatalystItem(itemGrouped().stacksTo(1)).speed(0.0112F));

        registerItem("eye_of_the_blizzard", "Eye Of The Blizzard", () -> new JCurioItem(itemGrouped().stacksTo(1)).overview(true));

        registerItem("sulphur_powder", "Sulphur Powder");
        registerItem("blazium_ingot", "Blazium Ingot");

        registerItem("music_disc_haunt_muskie_2", "Music Disc", () -> new MusicDiscItem(1, JSounds.HAUNT_MUSKIE_2::get, itemGrouped().stacksTo(1)));
        registerItem("music_disc_jitl_theme", "Music Disc", () -> new MusicDiscItem(1, JSounds.MENU_MUSIC::get, itemGrouped().stacksTo(1)));
        registerItem("music_disc_snowflakesss", "Music Disc", () -> new MusicDiscItem(1, JSounds.SNOWFLAKESSS::get, itemGrouped().stacksTo(1)));
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
