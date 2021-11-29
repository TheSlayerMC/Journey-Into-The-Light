package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.api.block.GroundPredicate;
import net.jitl.client.render.JBlockModels;
import net.jitl.client.render.JBlockStateResources;
import net.jitl.common.block.*;
import net.jitl.common.block.base.*;
import net.jitl.common.block.crop.FloroCropBlock;
import net.jitl.common.block.crop.TomatoCropBlock;
import net.jitl.common.block.portal.JBasePortalBlock;
import net.jitl.common.dimension.Dimensions;
import net.jitl.common.helper.EnumHarvestLevel;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JTabs;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;
import ru.timeconqueror.timecore.api.client.resource.BlockModel;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.BlockRegister.BlockRegisterChain;
import ru.timeconqueror.timecore.api.registry.BlockRegister.RenderTypeWrapper;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.function.Supplier;

public class BlockRegistrator {

    @AutoRegistrable
    static final BlockRegister REGISTER = new BlockRegister(JITL.MODID);

    @AutoRegistrable.InitMethod
    private static void register() {
        registerOreBlock("sapphire_ore", "Sapphire Ore", EnumHarvestLevel.DIAMOND, 3);
        registerSpeciallyRenderedBlock("lunium_ore", "Lunium Ore", () -> new JOreBlock(JBlockProperties.LUNIUM_ORE_PROPS.create()
                .lightLevel((state) -> 3)
                .harvestLevel(EnumHarvestLevel.DIAMOND.getInt()))
                .setExpDrop(0));
        registerDefaultBlock("shadium_ore", "Shadium Ore", () -> new JOreBlock(JBlockProperties.SHADIUM_ORE_PROPS.create()
                .harvestLevel(EnumHarvestLevel.DIAMOND.getInt()))
                .setExpDrop(0));
        registerOreBlock("iridium_ore", "Iridium Ore", EnumHarvestLevel.IRON, 3);
        registerOreBlock("bloodcrust_ore", "Bloodcrust Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("ashual_ore", "Ashual Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("blazium_ore", "Blazium Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("mekyum_ore", "Mekyum Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("celestium_ore", "Celestium Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("storon_ore", "Storon Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("flairium_ore", "Flairium Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("des_ore", "Des Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("enderillium_ore", "Enderillium Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("gorbite_ore", "Gorbite Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("orbadite_ore", "Orbadite Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("lunite_ore", "Lunite Ore", EnumHarvestLevel.NETHERITE, 6);
        registerSpeciallyRenderedBlock("firestone_ore", "Firestone Ore", () -> new JOreBlock(JBlockProperties.NETHER_BASALT_ORE_PROPS.create()
                .harvestLevel(EnumHarvestLevel.IRON.getInt()))
                .setExpDrop(1));
        registerDefaultBlock("warped_quartz_ore", "Warped Quartz Ore", () -> new JOreBlock(JBlockProperties.NETHER_NETHERRACK_ORE_PROPS.create()
                .harvestLevel(EnumHarvestLevel.IRON.getInt()))
                .setExpDrop(2));
        registerDefaultBlock("crimson_quartz_ore", "Crimson Quartz Ore", () -> new JOreBlock(JBlockProperties.NETHER_NETHERRACK_ORE_PROPS.create()
                .harvestLevel(EnumHarvestLevel.IRON.getInt()))
                .setExpDrop(2));

        registerBerryBushBlock("bradberry_bush", "Bradberry Bush", () -> JItems.BRADBERRY);

        registerDefaultBlock("blood_rock", "Blood Rock", () -> new Block(JBlockProperties.NETHER_BASALT_ORE_PROPS.create()));
        registerDefaultBlock("blood_rock_bricks", "Blood Rock Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("smooth_blood_rock", "Smooth Blood Rock", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("carved_blood_rock", "Carved Blood Rock", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("empty_blood_rune", "Empty Blood Rune", () -> new BloodRuneBlock(JBlockProperties.BRICK_PROPS.create()));
        registerColumnRenderedBlock("blood_rock_pillar", "Block Rock Pillar", () -> new RotatedPillarBlock(JBlockProperties.BRICK_PROPS.create()),
                "smooth_blood_rock",
                "blood_rock_pillar_side");

        BlockModel emptyBloodRuneModel = BlockModels.cubeTopModel(JITL.tl("block/empty_blood_rune"), JITL.tl("block/empty_blood_rune"));
        registerEmissiveRenderedBlock("blood_rune_soul", "Blood Rune Of Soul", () -> new Block(JBlockProperties.BRICK_PROPS.create()),
                emptyBloodRuneModel,
                BlockModels.cubeTopModel(JITL.tl("block/blood_rune_soul_front"), JITL.tl("block/blank")));
        registerEmissiveRenderedBlock("blood_rune_flesh", "Blood Rune Of Flesh", () -> new Block(JBlockProperties.BRICK_PROPS.create()),
                emptyBloodRuneModel,
                BlockModels.cubeTopModel(JITL.tl("block/blood_rune_flesh_front"), JITL.tl("block/blank")));
        registerEmissiveRenderedBlock("blood_rune_life", "Blood Rune Of Life", () -> new Block(JBlockProperties.BRICK_PROPS.create()),
                emptyBloodRuneModel,
                BlockModels.cubeTopModel(JITL.tl("block/blood_rune_life_front"), JITL.tl("block/blank")));
        registerEmissiveRenderedBlock("blood_rune_death", "Blood Rune Of Death", () -> new Block(JBlockProperties.BRICK_PROPS.create()),
                emptyBloodRuneModel,
                BlockModels.cubeTopModel(JITL.tl("block/blood_rune_death_front"), JITL.tl("block/blank")));

        registerSpeciallyRenderedBlock("essencia_altar", "Essencia Altar", () -> new EssenciaAltarBlock(JBlockProperties.BRICK_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/essencia_altar_top"), JITL.tl("block/essencia_altar_side"), JITL.tl("block/essencia_altar_bottom")));

        registerDefaultBlock("corrupted_blood_rock", "Corrupted Blood Rock", () -> new Block(JBlockProperties.NETHER_BASALT_ORE_PROPS.create()));
        registerDefaultBlock("smooth_corrupted_blood_rock", "Smooth Corrupted Blood Rock", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

        registerEmissiveRenderedBlock("charged_runic_connector", "Charged Runic Connector", () -> new HorizonSideFacedBlock(JBlockProperties.BRICK_PROPS.create()),
                JBlockStateResources.orientableState(JITL.bml("block/charged_runic_connector")),
                BlockModels.cubeTopModel(JITL.tl("block/runic_connector"), JITL.tl("block/smooth_corrupted_blood_rock")),
                BlockModels.cubeTopModel(JITL.tl("block/runic_connector_charged"), JITL.tl("block/blank")));

        registerDefaultBlock("sapphire_block", "Sapphire Block");
        registerSpeciallyRenderedBlock("lunium_block", "Lunium Block", () -> new Block(JBlockProperties.LUNIUM_BLOCK_PROPS.create().lightLevel((state) -> 5)));
        registerDefaultBlock("shadium_block", "Shadium Block", () -> new Block(JBlockProperties.SHADIUM_BLOCK_PROPS.create()));
        registerDefaultBlock("iridium_block", "Iridium Block");
        registerDefaultBlock("bloodcrust_block", "Bloodcrust Block");
        registerDefaultBlock("ashual_block", "Ashual Block");
        registerDefaultBlock("blazium_block", "Blazium Block");
        registerDefaultBlock("mekyum_block", "Mekyum Block");
        registerDefaultBlock("celestium_block", "Celestium Block");
        registerDefaultBlock("storon_block", "Storon Block");
        registerDefaultBlock("flairium_block", "Flairium Block");
        registerDefaultBlock("des_block", "Des Block");
        registerDefaultBlock("enderillium_block", "Enderillium Block");
        registerDefaultBlock("gorbite_block", "Gorbite Block");
        registerDefaultBlock("orbadite_block", "Orbadite Block");
        registerDefaultBlock("lunite_block", "Lunite Block");
        registerDefaultBlock("nethic_gemstone_block", "Nethic Gemstone Block");
        registerDefaultBlock("frost_gem_block", "Frost Gem Block");
        registerDefaultBlock("green_gem_block", "Green Gem Block");
        registerDefaultBlock("purple_gem_block", "Purple Gem Block");
        registerDefaultBlock("blue_gem_block", "Blue Gem Block");
        registerDefaultBlock("yellow_gem_block", "Yellow Gem Block");
        registerDefaultBlock("dreadiron_block", "Dreadiron Block", () -> new Block(JBlockProperties.DREADIRON_BLOCK_PROPS.create()));

        registerDefaultBlock("common_gems", "Common Gems");
        registerDefaultBlock("rare_gems", "Rare Gems");

        registerOrientableRenderedBlock("iron_crate", "Iron Crate", () -> new HorizonSideFacedBlock(JBlockProperties.WOOD_PROPS.create()),
                "iron_crate_top",
                "iron_crate_side",
                "iron_crate_front");

        registerDefaultBlock("block_of_mud", "Block O' Mud", () -> new Block(JBlockProperties.MUD_PROPS.create()));

        registerDefaultBlock("euca_brick", "Euca Brick");
        RegistryObject<Block> eucaGoldPlank = registerBlock("euca_gold_plank", "Euca Golden Plank", () -> new Block(JBlockProperties.WOOD_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("euca_golden_stairs", "Euca Golden Stairs", eucaGoldPlank, JBlockProperties.WOOD_PROPS.create());
        RegistryObject<Block> eucaDungeonBricks = registerBlock("euca_dungeon_bricks", "Euca Dungeon Brick", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("euca_dungeon_stairs", "Euca Dungeon Stairs", eucaDungeonBricks, JBlockProperties.STONE_PROPS.create());
        registerDefaultBlock("euca_dungeon_tile", "Euca Dungeon Brick", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerDefaultBlock("euca_gold_stone", "Euca Gold Stone", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerDefaultBlock("euca_runic_bricks", "Euca Dungeon Brick", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerDefaultBlock("euca_runic_lamp", "Euca Dungeon Lamp", () -> new Block(JBlockProperties.STONE_PROPS.create().lightLevel((state29) -> {
            return 6;
        })));
        registerDefaultBlock("euca_square_dungeon_bricks", "Euca Dungeon Brick", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerDefaultBlock("euca_square_runic_bricks", "Euca Dungeon Brick", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerDefaultBlock("euca_tile", "Euca Tile");

        registerDefaultBlock("laser_emitter", "Laser Emitter", () -> new LaserEmitterBlock(JBlockProperties.STONE_PROPS.create().noOcclusion()));
        registerCustomRenderLayerBlock("test_spawner", "Test Spawner", () -> new JSpawnerBlock(JEntities.WITHERSPINE_TYPE), JTabs.SPAWNERS, () -> RenderTypeWrappers.CUTOUT);

        registerTallCrossRenderedBlock("tall_green_glowshroom", "Tall Green Glowshroom", () -> new TallGlowshroomBlock(JBlockProperties.GLOWSHROOM_PROPS.create()));
        registerTallCrossRenderedBlock("tall_blue_glowshroom", "Tall Blue Glowshroom", () -> new TallGlowshroomBlock(JBlockProperties.GLOWSHROOM_PROPS.create()));
        registerTallCrossRenderedBlock("tall_red_glowshroom", "Tall Red Glowshroom", () -> new TallGlowshroomBlock(JBlockProperties.GLOWSHROOM_PROPS.create()));

        registerSpeciallyRenderedBlock("green_glowshroom", "Green Glowshroom", () -> new JPlantBlock(JBlockProperties.GLOWSHROOM_PROPS.create())
                        .setGrownPlant(() -> JBlocks.TALL_GREEN_GLOWSHROOM)
                        .setGroundPredicate(GroundPredicate.UNDERGROUND),
                () -> BlockModels.crossModel(JITL.tl("block/tall_green_glowshroom_top")));

        registerSpeciallyRenderedBlock("blue_glowshroom", "Blue Glowshroom", () -> new JPlantBlock(JBlockProperties.GLOWSHROOM_PROPS.create())
                        .setGrownPlant(() -> JBlocks.TALL_BLUE_GLOWSHROOM)
                        .setGroundPredicate(GroundPredicate.UNDERGROUND),
                () -> BlockModels.crossModel(JITL.tl("block/tall_blue_glowshroom_top")));

        registerSpeciallyRenderedBlock("red_glowshroom", "Red Glowshroom", () -> new JPlantBlock(JBlockProperties.GLOWSHROOM_PROPS.create())
                        .setGrownPlant(() -> JBlocks.TALL_RED_GLOWSHROOM)
                        .setGroundPredicate(GroundPredicate.UNDERGROUND),
                () -> BlockModels.crossModel(JITL.tl("block/tall_red_glowshroom_top")));

        registerSpeciallyRenderedBlock("cave_vines", "Cave Vines", () -> new CaveVinesTopBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> JBlockModels.emissive(BlockModels.crossModel(JITL.tl("block/cave_vines_back")), BlockModels.crossModel(JITL.tl("block/cave_vines_front"))));

        registerSpeciallyRenderedBlock("cave_vines_plant", "Cave Vines", () -> new CaveVinesBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> JBlockModels.emissive(BlockModels.crossModel(JITL.tl("block/cave_vines_plant_back")), BlockModels.crossModel(JITL.tl("block/cave_vines_plant_front"))));

        registerDefaultBlock("boil_portal_frame", "Boiling Point Portal Frame");
        registerDefaultBlock("euca_portal_frame", "Euca Portal Frame");
        registerDefaultBlock("frozen_portal_frame", "Frozen Lands Portal Frame");
		/*
		we'll keep these separated for a while
		 */
        //registerDefaultBlock("depths_portal_frame", "The Depths Portal Frame");//different style
        //registerDefaultBlock("corba_portal_frame", "Corba Portal Frame");//different style
        //registerDefaultBlock("terrania_portal_frame", "Terrania Portal Frame");
        //registerDefaultBlock("cloudia_portal_frame", "Cloudia Portal Frame");
        //registerDefaultBlock("senterian_portal_frame", "Senterian Portal Frame");//different style

        registerDefaultBlock("frozen_portal", "Frozen Portal", () -> new JBasePortalBlock(JBlockProperties.PORTAL.create(), Dimensions.FROZEN_LANDS, JBlocks.FROZEN_PORTAL_FRAME));

        registerSpeciallyRenderedBlock("grassy_permafrost", "Grassy Permafrost", () -> new Block(JBlockProperties.GRASSY_PERMAFROST_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/grassy_permafrost_top"), JITL.tl("block/grassy_permafrost_side"), JITL.tl("block/crumbled_permafrost")));

        registerDefaultBlock("crumbled_permafrost", "Crumbled Permafrost", () -> new Block(JBlockProperties.CRUMBLED_PERMAFROST_PROPS.create()));
        registerDefaultBlock("permafrost", "Permafrost", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));

        registerDefaultBlock("euca_portal", "Euca Portal", () -> new JBasePortalBlock(JBlockProperties.PORTAL.create(), Dimensions.EUCA, JBlocks.EUCA_PORTAL_FRAME));
        registerSpeciallyRenderedBlock("euca_gold_grass_block", "Euca Gold Grass", () -> new Block(JBlockProperties.GRASS_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/euca_gold_grass_block_top"), JITL.tl("block/euca_gold_grass_block_side"), JITL.tl("block/goldite_dirt")));
        registerSpeciallyRenderedBlock("goldite_grass_block", "Goldite Grass", () -> new Block(JBlockProperties.GRASS_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/goldite_grass_block_top"), JITL.tl("block/goldite_grass_block_side"), JITL.tl("block/goldite_dirt")));

        registerDefaultBlock("goldite_dirt", "Goldite Dirt", () -> new Block(JBlockProperties.DIRT_PROPS.create()));
        registerDefaultBlock("goldite_stone", "Goldite Stone", () -> new Block(JBlockProperties.STONE_PROPS.create()));

        registerTallCrossRenderedBlock("goldite_tall_grass", "Tall Goldite Grass", () -> new JDoublePlantBlock(JBlockProperties.PLANT_PROPS.create()).setPredicate(GroundPredicate.EUCA_GRASS_BLOCKS));

        registerSpeciallyRenderedBlock("goldite_bulb", "Goldite Bulb", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/goldite_bulb")));

        registerSpeciallyRenderedBlock("goldite_flower", "Goldite Flower", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/goldite_flower")));

        registerSpeciallyRenderedBlock("goldite_stalks", "Goldite Stalks", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/goldite_stalks")));

        registerSpeciallyRenderedBlock("euca_tall_grass", "Euca Tall Grass", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_tall_grass")));

        registerSpeciallyRenderedBlock("euca_tall_flowers", "Euca Tall Flowers", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_tall_flowers")));

        registerSpeciallyRenderedBlock("euca_blue_flower", "Euca Blue Flowers", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_blue_flower")));

        registerSpeciallyRenderedBlock("euca_silver_gold_flower", "Euca Silver Gold Flower", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_silver_gold_flower")));

        registerSpeciallyRenderedBlock("euca_silver_short_grass", "Euca Silver Short Grass", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_silver_short_grass")));

        registerSpeciallyRenderedBlock("euca_silver_tall_grass", "Euca Silver Tall Grass", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_silver_tall_grass")));

        registerSpeciallyRenderedBlock("euca_silver_sprouts", "Euca Silver Sprouts", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.EUCA_GRASS_BLOCKS),
                () -> BlockModels.crossModel(JITL.tl("block/euca_silver_sprouts")));

        registerSpeciallyRenderedBlock("euca_silver_grass_block", "Euca Silver Grass", () -> new Block(JBlockProperties.GRASS_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/euca_silver_grass_block_top"), JITL.tl("block/euca_silver_grass_block_side"), JITL.tl("block/euca_silver_dirt")));
        registerDefaultBlock("euca_silver_dirt", "Euca Silver Dirt", () -> new Block(JBlockProperties.DIRT_PROPS.create()));

        registerLogBlock("euca_gold_log", "Gold Euca Log");
        registerLogBlock("euca_silver_log", "Silver Euca Log");
        registerLogBlock("euca_brown_log", "Brown Euca Log");
        registerLogBlock("frozen_log", "Frosty Log");

        registerCustomRenderLayerBlock("euca_gold_leaves", "Euca Gold Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);
        registerCustomRenderLayerBlock("euca_silver_leaves", "Euca Silver Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);
        registerCustomRenderLayerBlock("euca_green_leaves", "Euca Green Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);

        registerCustomRenderLayerBlock("frozen_leaves", "Frozen Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);
        registerCustomRenderLayerBlock("frosty_ice", "Frosty Ice", () -> new Block(JBlockProperties.ICE_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.TRANSLUCENT);

        registerAttachedRenderedBlock("frost_crystal_large", "Large Frost Crystal", () -> new AttachedBlock(JBlockProperties.ICE_PROPS.create()),
                "frost_crystal_large");

        RegistryObject<Block> packedSnowBricks = registerBlock("packed_snow_bricks", "Packed Snow Bricks", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("packed_snow_brick_stairs", "Packed Snow Brick Stairs", packedSnowBricks, JBlockProperties.PERMAFROST_PROPS.create());
        RegistryObject<Block> packedIceBricks = registerBlock("packed_ice_bricks", "Packed Ice Bricks", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("packed_ice_brick_stairs", "Packed Ice Brick Stairs", packedIceBricks, JBlockProperties.PERMAFROST_PROPS.create());

        registerDefaultBlock("peridot_ore", "Peridot Ore", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create().harvestLevel(EnumHarvestLevel.IRON.getInt())));
        registerDefaultBlock("peridot_block", "Peridot Block", () -> new Block(JBlockProperties.METAL_PROPS.create()));

        registerCustomRenderLayerBlock("silver_bot_spawner", "Silverbot Spawner", () -> new JSpawnerBlock(JEntities.TOWER_GUARDIAN_TYPE), JTabs.SPAWNERS, () -> RenderTypeWrappers.CUTOUT);
        registerCustomRenderLayerBlock("gold_bot_spawner", "Goldbot Spawner", () -> new JSpawnerBlock(JEntities.FLORO_TYPE), JTabs.SPAWNERS, () -> RenderTypeWrappers.CUTOUT);

        registerCropBlock("tomato_crop", "Tomato Crop", new TomatoCropBlock());
        registerCropBlock("floro_crop", "Floro Crop", new FloroCropBlock());

        registerDefaultBlock("fumice", "Fumice", () -> new FumiceBlock(JBlockProperties.FUMICE_PROPS.create()));
        registerDefaultBlock("filled_fumice", "Filled Fumice", () -> new MagmaBlock(JBlockProperties.FUMICE_PROPS.create().lightLevel((intf) -> 3)));

        registerColumnRenderedBlock("stone_pillar", "Stone Pillar", () -> new RotatedPillarBlock(JBlockProperties.BRICK_PROPS.create()),
                "stone_pillar_top",
                "stone_pillar_side");

        registerDefaultBlock("small_stone_bricks", "Small Stone Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

        registerCustomRenderLayerBlock("ice_crystal_block", "Ice Crystal Block", () -> new Block(JBlockProperties.ICE_CRYSTAL_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.TRANSLUCENT);
        registerCustomRenderLayerBlock("cracked_ice_crystal_block", "Cracked Ice Crystal Block", () -> new Block(JBlockProperties.ICE_CRYSTAL_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.TRANSLUCENT);

        registerSpeciallyRenderedBlock("frostberry_thorn", "Frostberry Thorn", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK),
                () -> BlockModels.crossModel(JITL.tl("block/frostberry_thorn")));

        registerSpeciallyRenderedBlock("frozen_bloom", "Frozen Bloom", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK),
                () -> BlockModels.crossModel(JITL.tl("block/frozen_bloom")));

        registerSpeciallyRenderedBlock("frozen_flower", "Frozen Flower", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK),
                () -> BlockModels.crossModel(JITL.tl("block/frozen_flower")));

        registerSpeciallyRenderedBlock("ice_bud", "Ice Bud", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK),
                () -> BlockModels.crossModel(JITL.tl("block/ice_bud")));

        registerSpeciallyRenderedBlock("ice_bush", "Ice Bush", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                        .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK),
                () -> BlockModels.crossModel(JITL.tl("block/ice_bush")));
    }

    private static <B extends Block> BlockRegisterChain<B> register(String name, String enName, Supplier<B> block) {
        return REGISTER.register(name, block).name(enName);
    }

    /**
     * Registers a block with a default model and blockstate
     */
    private static void registerDefaultBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVarStateAndCubeAllModel();
    }

    /**
     * Registers block as usual with a creative tab
     */
    private static RegistryObject<Block> registerBlock(String name, String enName, Supplier<Block> blockSupplier, ItemGroup cTab) {
        return REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(cTab)
                .oneVarStateAndCubeAllModel()
                .asRegistryObject();
    }

    /**
     * Registers a log style block
     */
    private static void registerLogBlock(String name, String enName) {
        REGISTER.register(name, () -> new RotatedPillarBlock(JBlockProperties.LOG_PROPS.create()))
                .name(enName)
                .defaultBlockItem(JTabs.DECORATION)
                .state(JBlockStateResources.rotatablePillarState(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.cubeBottomTopModel(JITL.tl("block/" + name + "_top"), JITL.tl("block/" + name + "_side"), JITL.tl("block/" + name + "_top")));
    }

    /**
     * Registers a block with a default model, blockstate, and stone properties as a registry object
     */
    private static RegistryObject<Block> registerDefaultBlock(String name, String enName) {
        return REGISTER.register(name, () -> new Block
                (JBlockProperties.STONE_PROPS.create()))
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVarStateAndCubeAllModel()
                .asRegistryObject();
    }

    /**
     * Registers a block with a default model and blockstate as a registry object
     */
    private static RegistryObject<Block> registerBlock(String name, String enName, Supplier<Block> blockSupplier) {
        return REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVarStateAndCubeAllModel()
                .asRegistryObject();
    }

    /**
     * Registers a 'JOreBlock' block, with ore properties
     */
    private static void registerOreBlock(String name, String enName, EnumHarvestLevel harvestLevel, int minExp) {
        REGISTER.register(name, () -> new JOreBlock
                (JBlockProperties.ORE_PROPS.create()
                        .harvestLevel(harvestLevel.getInt()))
                .setExpDrop(minExp))
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVarStateAndCubeAllModel();
    }

    /**
     * For blocks with special rendering that isn't already provided by the available BlockModels or BlockStateResources that are available
     * For available BlockStates and BlockModels, see {@link JBlockStateResources}, {@link JBlockModels}, and {@link BlockModels}
     */
    private static void registerSpeciallyRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVariantState(new BlockModelLocation(JITL.MODID, "block/" + name));
    }

    private static void registerCustomRenderLayerBlock(String name, String enName, Supplier<Block> blockSupplier, ItemGroup cTab, Supplier<RenderTypeWrapper> renderType) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(renderType)
                .defaultBlockItem(cTab)
                .oneVarStateAndCubeAllModel();
    }

    /**
     * Registers a block with a BlockModel supplier
     */
    private static void registerSpeciallyRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, Supplier<BlockModel> blockModelSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVariantState(new BlockModelLocation(JITL.MODID, "block/" + name))
                .model(JITL.bml("block/" + name), blockModelSupplier);
    }

    /**
     * Registers a block with a "block/cube_column" model type
     */
    private static void registerColumnRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String topTexture, String sideTexture) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.rotatablePillarState(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.cubeColumnModel(JITL.tl("block/" + topTexture), JITL.tl("block/" + sideTexture)));
    }

    /**
     * Registers a block that's double tall and cross rendered
     */
    private static void registerTallCrossRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.DECORATION)
                .state(JBlockStateResources.doublePlantState(JITL.bml("block/" + name + "_bottom"), JITL.bml("block/" + name + "_top")))
                .model(JITL.bml("block/" + name + "_top"),
                        () -> BlockModels.crossModel(JITL.tl("block/" + name + "_top")))
                .model(JITL.bml("block/" + name + "_bottom"),
                        () -> BlockModels.crossModel(JITL.tl("block/" + name + "_bottom")));
    }

    /**
     * Registers a block with emissive rendering
     */
    private static void registerEmissiveRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, BlockModel normal, BlockModel emissive) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVariantState(JITL.bml("block/" + name))
                .model(JITL.bml("block/" + name),
                        () -> JBlockModels.emissive(normal, emissive));
    }

    /**
     * Registers a block with emissive rendering, with {@link BlockStateResource} for custom BlockState properties
     */
    private static void registerEmissiveRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, BlockStateResource blockStateResource, BlockModel normal, BlockModel emissive) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(blockStateResource)
                .model(JITL.bml("block/" + name),
                        () -> JBlockModels.emissive(normal, emissive));
    }

    /**
     * Registers a block with an orientable BlockState, for rotatable blocks.
     */
    private static void registerOrientableRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String topTexture, String sideTexture, String frontTexture) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.orientableState(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.cubeOrientableModel(
                                JITL.tl("block/" + topTexture),
                                JITL.tl("block/" + sideTexture),
                                JITL.tl("block/" + frontTexture)));
    }

    private static void registerAttachedRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String texture) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.orientableStateAllSides(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.crossModel(JITL.tl("block/" + texture)));
    }

    /**
     * Registers a berry bush block
     */
    //TODO remove itemblock, bind berry bush to 'berries' item
    private static void registerBerryBushBlock(String name, String enName, Supplier<IItemProvider> itemProviderSupplier) {
        REGISTER.register(name, () -> new JBerryBushBlock(
                (JBlockProperties.BERRY_BUSH_PROPS.create()), itemProviderSupplier))
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.BLOCKS).also((chain) -> {
            String model0 = "block/" + name + "_0";
            String model1 = "block/" + name + "_1";
            String model2 = "block/" + name + "_2";
            String model3 = "block/" + name + "_3";

            chain.model(JITL.bml(model0), () -> BlockModels.crossModel(JITL.tl(model0)))
                    .model(JITL.bml(model1), () -> BlockModels.crossModel(JITL.tl(model1)))
                    .model(JITL.bml(model2), () -> BlockModels.crossModel(JITL.tl(model2)))
                    .model(JITL.bml(model3), () -> BlockModels.crossModel(JITL.tl(model3)))
                    .state(() -> BlockStateResource.fromBuilder(BlockStateResource.Builder.create()
                            .addVariant(new BlockStateResource.Variant("age=0", JITL.bml(model0)))
                            .addVariant(new BlockStateResource.Variant("age=1", JITL.bml(model1)))
                            .addVariant(new BlockStateResource.Variant("age=2", JITL.bml(model2)))
                            .addVariant(new BlockStateResource.Variant("age=3", JITL.bml(model3)))));
        });
    }

    private static void registerCropBlock(String name, String enName, Block block) {
        REGISTER.register(name, () -> block)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .also((chain) -> {
            String model0 = "block/" + name + "_0";
            String model1 = "block/" + name + "_1";
            String model2 = "block/" + name + "_2";
            String model3 = "block/" + name + "_3";
            String model4 = "block/" + name + "_4";
            String model5 = "block/" + name + "_5";
            String model6 = "block/" + name + "_6";
            String model7 = "block/" + name + "_7";

            chain.model(JITL.bml(model0), () -> BlockModels.crossModel(JITL.tl(model0)))
                    .model(JITL.bml(model1), () -> BlockModels.crossModel(JITL.tl(model1)))
                    .model(JITL.bml(model2), () -> BlockModels.crossModel(JITL.tl(model2)))
                    .model(JITL.bml(model3), () -> BlockModels.crossModel(JITL.tl(model3)))
                    .model(JITL.bml(model4), () -> BlockModels.crossModel(JITL.tl(model4)))
                    .model(JITL.bml(model5), () -> BlockModels.crossModel(JITL.tl(model5)))
                    .model(JITL.bml(model6), () -> BlockModels.crossModel(JITL.tl(model6)))
                    .model(JITL.bml(model7), () -> BlockModels.crossModel(JITL.tl(model7)))
                    .state(() -> BlockStateResource.fromBuilder(BlockStateResource.Builder.create()
                            .addVariant(new BlockStateResource.Variant("age=0", JITL.bml(model0)))
                            .addVariant(new BlockStateResource.Variant("age=1", JITL.bml(model1)))
                            .addVariant(new BlockStateResource.Variant("age=2", JITL.bml(model2)))
                            .addVariant(new BlockStateResource.Variant("age=3", JITL.bml(model3)))
                            .addVariant(new BlockStateResource.Variant("age=4", JITL.bml(model4)))
                            .addVariant(new BlockStateResource.Variant("age=5", JITL.bml(model5)))
                            .addVariant(new BlockStateResource.Variant("age=6", JITL.bml(model6)))
                            .addVariant(new BlockStateResource.Variant("age=7", JITL.bml(model7)))));
        });
    }

    public static class RenderTypeWrappers {
        public static final RenderTypeWrapper CUTOUT = new RenderTypeWrapper(RenderType.cutout());
        public static final RenderTypeWrapper CUTOUT_MIPPED = new RenderTypeWrapper(RenderType.cutoutMipped());
        public static final RenderTypeWrapper TRANSLUCENT = new RenderTypeWrapper(RenderType.translucent());
        public static final RenderTypeWrapper TRANSLUCENT_NO_CRUMBLING = new RenderTypeWrapper(RenderType.translucentNoCrumbling());
    }
}
