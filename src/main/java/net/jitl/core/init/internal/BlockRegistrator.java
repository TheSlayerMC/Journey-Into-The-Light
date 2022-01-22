package net.jitl.core.init.internal;

import net.jitl.client.render.JBlockModels;
import net.jitl.client.render.JBlockStateResources;
import net.jitl.common.block.CaveVinesBlock;
import net.jitl.common.block.*;
import net.jitl.common.block.base.*;
import net.jitl.common.block.crop.FloroCropBlock;
import net.jitl.common.block.crop.GolditeFarmlandBlock;
import net.jitl.common.block.crop.TomatoCropBlock;
import net.jitl.common.block.portal.DepthsPortalBlock;
import net.jitl.common.block.portal.DepthsPortalFrameBlock;
import net.jitl.common.block.portal.JBasePortalBlock;
import net.jitl.common.block.trees.BitterwoodTreeGrower;
import net.jitl.common.block.trees.DyingFrozenTree;
import net.jitl.common.block.trees.EucaGoldTreeGrower;
import net.jitl.common.block.trees.EucaGreenTreeGrower;
import net.jitl.common.helper.EnumHarvestLevel;
import net.jitl.common.tile.ObeliskTile;
import net.jitl.core.JITL;
import net.jitl.core.api.block.GroundPredicate;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JItems;
import net.jitl.core.init.JTabs;
import net.jitl.core.init.world.Dimensions;
import net.jitl.core.util.JBlockProperties;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.client.resource.BlockModel;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.BlockRegister.BlockRegisterChain;
import ru.timeconqueror.timecore.api.registry.BlockRegister.RenderTypeWrapper;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.function.Supplier;

public class BlockRegistrator {

    @AutoRegistrable
    static final BlockRegister REGISTER = new BlockRegister(JITL.MODID);

    @AutoRegistrable.Init
    private static void register() {
        registerOreBlock("sapphire_ore", "Sapphire Ore", EnumHarvestLevel.DIAMOND, 3);
        registerOreBlock("deepslate_sapphire_ore", "Deepslate Sapphire Ore", EnumHarvestLevel.DIAMOND, 3);
        registerSpeciallyRenderedBlock("lunium_ore", "Lunium Ore", () -> new JOreBlock(JBlockProperties.LUNIUM_ORE_PROPS.create()
                .lightLevel((state) -> 3))
                .setExpDrop(0));
        registerSpeciallyRenderedBlock("deepslate_lunium_ore", "Deepslate Lunium Ore", () -> new JOreBlock(JBlockProperties.LUNIUM_ORE_PROPS.create()
                .lightLevel((state) -> 3))
                .setExpDrop(0));
        registerDefaultBlock("shadium_ore", "Shadium Ore", () -> new JOreBlock(JBlockProperties.SHADIUM_ORE_PROPS.create())
                .setExpDrop(0));
        registerDefaultBlock("deepslate_shadium_ore", "Deepslate Shadium Ore", () -> new JOreBlock(JBlockProperties.SHADIUM_ORE_PROPS.create())
                .setExpDrop(0));
        registerOreBlock("iridium_ore", "Iridium Ore", EnumHarvestLevel.IRON, 3);
        registerOreBlock("deepslate_iridium_ore", "Deepslate Iridium Ore", EnumHarvestLevel.IRON, 3);
        registerOreBlock("bloodcrust_ore", "Bloodcrust Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("ashual_ore", "Ashual Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("blazium_ore", "Blazium Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("mekyum_ore", "Mekyum Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("celestium_ore", "Celestium Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("storon_ore", "Storon Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("korite_ore", "Korite Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("flairium_ore", "Flairium Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("des_ore", "Des Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("enderillium_ore", "Enderillium Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("gorbite_ore", "Gorbite Ore", EnumHarvestLevel.NETHERITE, 4);
        registerOreBlock("orbadite_ore", "Orbadite Ore", EnumHarvestLevel.NETHERITE, 0);
        registerOreBlock("lunite_ore", "Lunite Ore", EnumHarvestLevel.NETHERITE, 6);
        registerSpeciallyRenderedBlock("firestone_ore", "Firestone Ore", () -> new JOreBlock(JBlockProperties.NETHER_BASALT_ORE_PROPS.create())
                .setExpDrop(1));
        registerDefaultBlock("warped_quartz_ore", "Warped Quartz Ore", () -> new JOreBlock(JBlockProperties.NETHER_NETHERRACK_ORE_PROPS.create())
                .setExpDrop(2));
        registerDefaultBlock("crimson_quartz_ore", "Crimson Quartz Ore", () -> new JOreBlock(JBlockProperties.NETHER_NETHERRACK_ORE_PROPS.create())
                .setExpDrop(2));

        registerBerryBushBlock("tartberry_bush", "Tartberry Bush", () -> JItems.TARTBERRY);
        registerBerryBushBlock("redcurrant_bush", "Redcurrant Bush", () -> JItems.REDCURRANT_BERRY);

        registerDefaultBlock("mossy_essence_stone", "Mossy Essence Stone", () -> new Block(JBlockProperties.DUNGEON_BLOCK_PROPS.create()));
        registerDefaultBlock("ancient_catalyst", "Ancient Catalyst", () -> new AncientCatalystBlock(JBlockProperties.DUNGEON_BLOCK_PROPS.create()));
        registerCustomRenderedBlock("ancient_socket", "Ancient Socket", AncientSocketBlock::new);

        registerColumnRenderedBlock("ancient_stone", "Ancient Stone", () -> new RotatedPillarBlock(JBlockProperties.DUNGEON_BLOCK_PROPS.create()),
                "ancient_stone",
                "ancient_stone_side");
        registerSpeciallyRenderedBlock("ancient_obelisk", "Ancient Obelisk",
                () -> new JTileContainerBlock(JBlockProperties.DUNGEON_BLOCK_PROPS.create().noOcclusion(), (pos, state) -> new ObeliskTile(pos, state)));

        registerEmissiveRenderedBlock("ancient_stone_runic_0", "Ancient Runic Stone", () -> new Block(JBlockProperties.DUNGEON_BLOCK_PROPS.create()),
                BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_0")), BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_0_overlay")));

        registerEmissiveRenderedBlock("ancient_stone_runic_1", "Ancient Runic Stone", () -> new Block(JBlockProperties.DUNGEON_BLOCK_PROPS.create()),
                BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_1")), BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_1_overlay")));

        registerEmissiveRenderedBlock("ancient_stone_runic_2", "Ancient Runic Stone", () -> new Block(JBlockProperties.DUNGEON_BLOCK_PROPS.create()),
                BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_2")), BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_2_overlay")));

        registerEmissiveRenderedBlock("ancient_stone_runic_3", "Ancient Runic Stone", () -> new Block(JBlockProperties.DUNGEON_BLOCK_PROPS.create()),
                BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_3")), BlockModels.cubeAllModel(JITL.blockTl("ancient_stone_runic_3_overlay")));

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

        registerDefaultBlock("large_nether_bricks", "Large Nether Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

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
        registerDefaultBlock("korite_block", "Korite Block");
        registerDefaultBlock("flairium_block", "Flairium Block");
        registerDefaultBlock("des_block", "Des Block");
        registerDefaultBlock("enderillium_block", "Enderillium Block");
        registerDefaultBlock("gorbite_block", "Gorbite Block");
        registerDefaultBlock("orbadite_block", "Orbadite Block");
        registerDefaultBlock("crimson_quartz_block", "Crimson Quartz Block");
        registerDefaultBlock("warped_quartz_block", "Warped Quartz Block");
        registerDefaultBlock("firestone_block", "Firestone Block");
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

        registerGrassBlock("deep_mycelium", "Deep Mycelium", () -> new Block(JBlockProperties.DEEPSLATE_PROPS.create()), new TextureLocation("minecraft", "block/deepslate"));

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
        registerSlabBlock("euca_gold_plank_slab", "Euca Gold Planks Slab", "euca_gold_plank", () -> new SlabBlock(JBlockProperties.WOOD_PROPS.create()));
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

        registerSpeciallyRenderedBlock("deepvine", "Deepvine", () -> new CaveVinesTopBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> JBlockModels.emissive(BlockModels.crossModel(JITL.tl("block/deepvine_back")), BlockModels.crossModel(JITL.tl("block/deepvine_front"))));

        registerSpeciallyRenderedBlock("deepvine_plant", "Deepvine", () -> new CaveVinesBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> JBlockModels.emissive(BlockModels.crossModel(JITL.tl("block/deepvine_plant_back")), BlockModels.crossModel(JITL.tl("block/deepvine_plant_front"))));

        registerSpeciallyRenderedBlock("glimmer_root", "Glimmer Root", () -> new CaveVinesTopBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/glimmer_root")));

        registerSpeciallyRenderedBlock("glimmer_root_plant", "Glimmer Root", () -> new CaveVinesBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/glimmer_root_plant")));

        registerSpeciallyRenderedBlock("icy_ivy", "Icy Ivy", () -> new IcyIvyTopBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/icy_ivy")));
        registerSpeciallyRenderedBlock("icy_ivy_plant", "Icy Ivy", () -> new IcyIvyBlock(JBlockProperties.CAVE_VINE_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/icy_ivy")));

        registerSpeciallyRenderedBlock("flame_bulb", "Flame Bulb", () -> new FlameBulbTopBlock(JBlockProperties.GLOW_PLANT_PROPS.create().noCollission()),
                () -> BlockModels.crossModel(JITL.tl("block/flame_bulb_top")));
        registerSpeciallyRenderedBlock("flame_bulb_plant", "Flame Bulb", () -> new FlameBulbBlock(JBlockProperties.PLANT_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/flame_bulb_stem")));

        registerSpeciallyRenderedBlockWithRenderType("crystal_fruit", "Crystal Fruit", () -> new CrystalFruitBlock(JBlockProperties.GLOW_PLANT_PROPS.create()), () -> RenderTypeWrappers.CUTOUT);

        registerDefaultBlock("boil_portal_frame", "Boiling Point Portal Frame");
        registerDefaultBlock("euca_portal_frame", "Euca Portal Frame");
        registerDefaultBlock("frozen_portal_frame", "Frozen Lands Portal Frame");
		/*
		we'll keep these separated for a while
		 */
        //registerDefaultBlock("corba_portal_frame", "Corba Portal Frame");//different style
        //registerDefaultBlock("terrania_portal_frame", "Terrania Portal Frame");
        //registerDefaultBlock("cloudia_portal_frame", "Cloudia Portal Frame");
        //registerDefaultBlock("senterian_portal_frame", "Senterian Portal Frame");//different style

        registerPortalBlock("frozen_portal", "Frozen Portal", () -> new JBasePortalBlock(JBlockProperties.PORTAL.create(), Dimensions.FROZEN_LANDS, () -> JBlocks.FROZEN_PORTAL_FRAME));

        registerDefaultBlock("crumbled_permafrost", "Crumbled Permafrost", () -> new Block(JBlockProperties.CRUMBLED_PERMAFROST_PROPS.create()));

        registerGrassBlock("grassy_permafrost", "Grassy Permafrost", () -> new JGrassBlock(JBlockProperties.GRASSY_PERMAFROST_PROPS.create(), JBlocks.CRUMBLED_PERMAFROST), JITL.tl("block/crumbled_permafrost"));

        registerDefaultBlock("permafrost", "Permafrost", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));

        registerPortalBlock("euca_portal", "Euca Portal", () -> new JBasePortalBlock(JBlockProperties.PORTAL.create(), Dimensions.EUCA, () -> JBlocks.EUCA_PORTAL_FRAME));
        registerGrassBlock("euca_gold_grass_block", "Euca Gold Grass", () -> new JGrassBlock(JBlockProperties.GRASS_PROPS.create(), JBlocks.GOLDITE_DIRT), JITL.tl("block/goldite_dirt"));

        registerGrassBlock("goldite_grass_block", "Goldite Grass", () -> new JGrassBlock(JBlockProperties.GRASS_PROPS.create(), JBlocks.GOLDITE_DIRT), JITL.tl("block/goldite_dirt"));

        registerDefaultBlock("goldite_dirt", "Goldite Dirt", () -> new Block(JBlockProperties.DIRT_PROPS.create()));
        registerRandomizedRotatedBlock("goldite_stone", "Goldite Stone", () -> new Block(JBlockProperties.STONE_PROPS.create()));

        registerSpeciallyRenderedBlock("goldite_farmland", "Goldite Farmland", GolditeFarmlandBlock::new);

        registerTallCrossRenderedBlock("goldite_tall_grass", "Tall Goldite Grass", () -> new JDoublePlantBlock(JBlockProperties.PLANT_PROPS.create()).setPredicate(GroundPredicate.EUCA_GRASS_BLOCKS));

        registerSpeciallyRenderedBlock("frostwood_sapling", "Frostwood Sapling", () -> new JSaplingBlock(new DyingFrozenTree(), JBlockProperties.PLANT_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/frostwood_sapling")));

        registerSpeciallyRenderedBlock("bitterwood_sapling", "Bitterwood Sapling", () -> new JSaplingBlock(new BitterwoodTreeGrower(), JBlockProperties.PLANT_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/bitterwood_sapling")));

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

        registerLogBlock("euca_gold_log", "Gold Euca Log");
        registerLogBlock("euca_brown_log", "Brown Euca Log");
        registerLogBlock("frozen_log", "Frosty Log");

        registerSpeciallyRenderedBlock("euca_gold_sapling", "Euca Gold Sapling", () -> new JSaplingBlock(new EucaGoldTreeGrower(), JBlockProperties.PLANT_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/euca_gold_sapling")));

        registerSpeciallyRenderedBlock("euca_green_sapling", "Euca Green Sapling", () -> new JSaplingBlock(new EucaGreenTreeGrower(), JBlockProperties.PLANT_PROPS.create()),
                () -> BlockModels.crossModel(JITL.tl("block/euca_green_sapling")));

        registerCampfireBlock("bitterwood_campfire", "Bitterwood Campfire", () -> new CampfireBlock(true, 1, JBlockProperties.WOOD_PROPS.create().noOcclusion()));

        registerCustomRenderLayerBlock("euca_gold_leaves", "Euca Gold Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);
        registerRandomizedTextureBlock("euca_green_leaves", "Euca Green Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);

        registerCustomRenderLayerBlock("frozen_leaves", "Frozen Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);
        registerCustomRenderLayerBlock("frosty_ice", "Frosty Ice", () -> new Block(JBlockProperties.ICE_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.TRANSLUCENT);

        registerAttachedRenderedBlock("frost_crystal_large", "Large Frost Crystal", () -> new AttachedBlock(JBlockProperties.ICE_PROPS.create().lightLevel((intf) -> 4)),
                "frost_crystal_large");
        registerAttachedRenderedBlock("frost_crystal_medium", "Medium Frost Crystal", () -> new AttachedBlock(JBlockProperties.ICE_PROPS.create().lightLevel((intf) -> 3)),
                "frost_crystal_medium");
        registerAttachedRenderedBlock("frost_crystal_small", "Small Frost Crystal", () -> new AttachedBlock(JBlockProperties.ICE_PROPS.create().lightLevel((intf) -> 2)),
                "frost_crystal_small");
        registerAttachedRenderedBlock("frost_crystal_tiny", "Tiny Frost Crystal", () -> new AttachedBlock(JBlockProperties.ICE_PROPS.create().lightLevel((intf) -> 1)),
                "frost_crystal_tiny");

        registerCustomAttachedRenderedBlock("fluorescent_fungi", "Fluorescent Fungi", () -> new AttachedBlock(JBlockProperties.GLOWSHROOM_PROPS.create().lightLevel((intf) -> 3)));

        registerCustomAttachedRenderedBlock("ice_shroom_shelf", "Ice Shroom Shelf", () -> new AttachedBlock(JBlockProperties.SHROOM_SHELF.create().lightLevel((intf) -> 4)));

        registerCustomRenderedBlock("icy_brush", "Icy Brush", () -> new VineBlock(JBlockProperties.VINES_PROPS.create()));
        registerCustomRenderedBlock("charred_brush", "Charred Brush", () -> new VineBlock(JBlockProperties.VINES_PROPS.create()));

        RegistryObject<Block> packedSnowBricks = registerBlock("packed_snow_bricks", "Packed Snow Bricks", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("packed_snow_brick_stairs", "Packed Snow Brick Stairs", packedSnowBricks, JBlockProperties.PERMAFROST_PROPS.create());
        RegistryObject<Block> packedIceBricks = registerBlock("packed_ice_bricks", "Packed Ice Bricks", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("packed_ice_brick_stairs", "Packed Ice Brick Stairs", packedIceBricks, JBlockProperties.PERMAFROST_PROPS.create());

        registerDefaultBlock("peridot_ore", "Peridot Ore", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));
        registerDefaultBlock("peridot_block", "Peridot Block", () -> new Block(JBlockProperties.METAL_PROPS.create()));

        registerDefaultBlock("rimestone_ore", "Rimestone Ore", () -> new Block(JBlockProperties.PERMAFROST_PROPS.create()));
        registerDefaultBlock("rimestone_block", "Rimestone Block", () -> new Block(JBlockProperties.METAL_PROPS.create()));

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

        RegistryObject<Block> ashBlockBricks = registerBlock("ash_block_bricks", "Ash Block Bricks", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("ash_block_brick_stairs", "Rubble Brick Stairs", ashBlockBricks, JBlockProperties.STONE_PROPS.create());
        registerDefaultBlock("smooth_ash_block", "Smooth Ash Block", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerSpeciallyRenderedBlock("runic_ash_block", "Runic Ash Block", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

        RegistryObject<Block> rubbleBricks = registerBlock("rubble_bricks", "Rubble Bricks", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("rubble_brick_stairs", "Rubble Brick Stairs", rubbleBricks, JBlockProperties.STONE_PROPS.create());
        registerDefaultBlock("smooth_rubble", "Smooth Rubble", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerSpeciallyRenderedBlock("runic_rubble", "Runic Rubble", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

        RegistryObject<Block> scorchedRubbleBricks = registerBlock("scorched_rubble_bricks", "Scorched Rubble Bricks", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        KBlockRegistrator.INSTANCE.registerStairs("scorched_rubble_brick_stairs", "Scorched Rubble Brick Stairs", scorchedRubbleBricks, JBlockProperties.STONE_PROPS.create());
        registerDefaultBlock("smooth_scorched_rubble", "Smooth Scorched Rubble", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerSpeciallyRenderedBlock("runic_scorched_rubble", "Runic Scorched Rubble", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

        registerCustomRenderLayerBlock("ice_crystal_block", "Ice Crystal Block", () -> new Block(JBlockProperties.ICE_CRYSTAL_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.TRANSLUCENT);
        registerCustomRenderLayerBlock("cracked_ice_crystal_block", "Cracked Ice Crystal Block", () -> new Block(JBlockProperties.ICE_CRYSTAL_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.TRANSLUCENT);

        registerCropRenderedBlock("frostberry_thorn", "Frostberry Thorn", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK));

        registerCropRenderedBlock("frozen_bloom", "Frozen Bloom", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK));

        registerCrossRenderedBlock("ice_bud", "Ice Bud", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK));

        registerCrossRenderedBlock("ice_bush", "Ice Bush", () -> new JPlantBlock(JBlockProperties.PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK));

        registerSpeciallyRenderedBlockWithRenderType("ciclebloom", "Ciclebloom", () ->
                new JSingleDoublePlantBlock(JBlockProperties.PLANT_PROPS.create().lightLevel((light) -> 4)).setGroundPredicate(GroundPredicate.FROZEN_GRASS_BLOCK), () -> RenderTypeWrappers.CUTOUT);

        registerDefaultBlock("depths_stone", "Depths Stone", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerDefaultBlock("depths_lamp", "Depths Lamp", () -> new Block(JBlockProperties.GLOW_BLOCK.create()));
        registerCustomRenderedBlock("depths_portal_frame", "Depths Portal Frame", () -> new DepthsPortalFrameBlock(JBlockProperties.BRICK_PROPS.create()));
        registerCustomRenderedBlock("depths_portal", "Depths Portal", () -> new DepthsPortalBlock(JBlockProperties.PORTAL.create()));

        RegistryObject<Block> depthsDirt = registerBlock("depths_dirt", "Depths Dirt", () -> new Block(JBlockProperties.DIRT_PROPS.create()));
        registerGrassBlock("depths_grass_block", "Depths Grass", () -> new JSpreadableSnowyDirtBlock(JBlockProperties.GRASS_PROPS.create(), depthsDirt.get()), JITL.tl("block/depths_dirt"));

        registerRandomizedRotatedBlock("rubble", "Rubble", () -> new JBlock(JBlockProperties.HOLD_FIRE));
        registerRandomizedRotatedBlock("volcanic_sand", "Volcanic Sand", () -> new JBlock(JBlockProperties.HOLD_FIRE_SAND));
        registerDefaultBlock("volcanic_soil", "Volcanic Soil", () -> new JBlock(JBlockProperties.HOLD_FIRE_SAND));
        registerRandomizedRotatedBlock("hot_ground", "Hot Ground", () -> new JBlock(JBlockProperties.HOLD_FIRE));
        registerRandomizedRotatedBlock("scorched_rubble", "Scorched Rubble", () -> new JBlock(JBlockProperties.HOLD_FIRE));
        registerRandomizedRotatedBlock("ash_block", "Ash", () -> new Block(JBlockProperties.STONE_PROPS.create()));
        registerPortalBlock("boil_portal", "Boiling Portal", () -> new JBasePortalBlock(JBlockProperties.PORTAL.create(), Dimensions.BOIL, () -> JBlocks.BOIL_PORTAL_FRAME));
        registerGrassBlock("charred_grass", "Charred Grass", () -> new JGrassBlock(JBlockProperties.GRASS_PROPS.create(), JBlocks.RUBBLE), JITL.tl("block/rubble"));

        registerDefaultBlock("sulphur_rock", "Sulphur Rock", () -> new Block(JBlockProperties.BASALT_PROPS.create()));
        registerAttachedRenderedBlock("sulphur_crystal", "Sulphur Crystal", () -> new AttachedBlock(JBlockProperties.ICE_PROPS.create().lightLevel((intf) -> 4)),
                "sulphur_crystal");
        registerDefaultBlock("torrid_crystal", "Torrid Crystal", () -> new TorridCrystalBlock(JBlockProperties.BASALT_PROPS.create()));

        registerSpeciallyRenderedBlock("scorched_stalagmite_tiny", "Scorched Stalagmite", JBlockStalagmite::new);
        registerSpeciallyRenderedBlock("scorched_stalagmite_small", "Scorched Stalagmite", JBlockStalagmite::new);
        registerSpeciallyRenderedBlock("scorched_stalagmite_med", "Scorched Stalagmite", JBlockStalagmite::new);
        registerSpeciallyRenderedBlock("scorched_stalagmite_large", "Scorched Stalagmite", JBlockStalagmite::new);

        registerSpeciallyRenderedBlockWithRenderType("scorched_cactus", "Scorched Cactus", JBlockCactus::new, () -> RenderTypeWrappers.CUTOUT);

        registerLogBlock("burned_bark", "Burned Bark");
        registerCustomRenderLayerBlock("charred_leaves", "Charred Leaves", () -> new JLeavesBlock(JBlockProperties.LEAVES_PROPS.create()), JTabs.DECORATION, () -> RenderTypeWrappers.CUTOUT);

        registerCrossRenderedBlock("inferno_bush", "Inferno Bush", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("crumbling_pine", "Crumbling Pine", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("crisp_grass", "Crisp Grass", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("flame_pod", "Flame Pod", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("hell_bell", "Hell Bell", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("lava_bloom", "Lava Bloom", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("charred_weeds", "Charred Weeds", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerCrossRenderedBlock("charred_short_grass", "Charred Short Grass", () -> new JPlantBlock(JBlockProperties.FIRE_PLANT_PROPS.create())
                .setGroundPredicate(GroundPredicate.BOILING_LAND));

        registerTallCrossRenderedBlock("tall_molten_plant", "Tall Molten Plant", () -> new JDoublePlantBlock(JBlockProperties.PLANT_PROPS.create()).setPredicate(GroundPredicate.BOILING_LAND));
        registerTallCrossRenderedBlock("tall_crumbling_pine", "Tall Crumbling Pine", () -> new JDoublePlantBlock(JBlockProperties.PLANT_PROPS.create()).setPredicate(GroundPredicate.BOILING_LAND));
        registerTallCropRenderedBlock("charred_tall_grass", "Charred Tall Grass", () -> new JDoublePlantBlock(JBlockProperties.PLANT_PROPS.create()).setPredicate(GroundPredicate.BOILING_LAND));
        registerTallCrossRenderedBlock("tall_sizzleshroom", "Tall Sizzleshroom", () -> new TallGlowshroomBlock(JBlockProperties.GLOWSHROOM_PROPS.create()));
        registerSpeciallyRenderedBlock("sizzleshroom", "Sizzleshroom", () -> new JPlantBlock(JBlockProperties.GLOWSHROOM_PROPS.create())
                        .setGroundPredicate(GroundPredicate.UNDERGROUND),
                () -> BlockModels.crossModel(JITL.tl("block/tall_sizzleshroom_top")));

        registerCustomRenderedBlock("tall_fungi", "Fluorescent Fungi", () -> new JPlantBlock(JBlockProperties.GLOWSHROOM_PROPS.create(), false)
                .setGroundPredicate(GroundPredicate.UNDERGROUND));

        registerSpeciallyRenderedBlock("frozen_pedestal", "Frozen Pedestal", JBlockPedestal::new);

        registerSpeciallyRenderedBlock("ancient_pottery", "Ancient Pottery", AncientPotteryBlock::new);

        registerOrientableRenderedBlock("boil_lock", "Boiling Lock", LockBlock::new, "boil_lock_top", "boil_lock_side", "boil_lock_front");
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
    private static RegistryObject<Block> registerBlock(String name, String enName, Supplier<Block> blockSupplier, CreativeModeTab cTab) {
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
                        (JBlockProperties.ORE_PROPS.create())
                        .setExpDrop(minExp))
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVarStateAndCubeAllModel();
    }

    private static void registerGrassBlock(String name, String enName, Supplier<Block> blockSupplier, TextureLocation bottomTexture) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.randomizedRotatedTop(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.cubeBottomTopModel(
                                JITL.tl("block/" + name + "_top"),
                                JITL.tl("block/" + name + "_side"),
                                bottomTexture));
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

    private static void registerSpeciallyRenderedBlockWithRenderType(String name, String enName, Supplier<Block> blockSupplier, Supplier<RenderTypeWrapper> renderType) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(renderType)
                .defaultBlockItem(JTabs.BLOCKS)
                .oneVariantState(new BlockModelLocation(JITL.MODID, "block/" + name));
    }

    private static void registerCustomRenderLayerBlock(String name, String enName, Supplier<Block> blockSupplier, CreativeModeTab cTab, Supplier<RenderTypeWrapper> renderType) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(renderType)
                .defaultBlockItem(cTab)
                .oneVarStateAndCubeAllModel();
    }

    private static void registerSpeciallyRenderedRandomlyRotatedBlock(String name, String enName, Supplier<Block> blockSupplier, Supplier<BlockModel> blockModelSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.randomizedRotatedTop(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name), blockModelSupplier);
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

    private static void registerCrossRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name));
                })
                .oneVariantState(new BlockModelLocation(JITL.MODID, "block/" + name))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.crossModel(JITL.tl("block/" + name)));
    }

    private static void registerCropRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name));
                })
                .oneVariantState(new BlockModelLocation(JITL.MODID, "block/" + name))
                .model(JITL.bml("block/" + name),
                        () -> JBlockModels.crop(JITL.tl("block/" + name)));
    }

    /**
     * Registers a block with no pre-generated blockstate, block or item model
     */
    private static void registerCustomRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.BLOCKS);
    }

    private static void registerPortalBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.TRANSLUCENT)
                .defaultBlockItem(JTabs.BLOCKS, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name));
                })
                .state(JBlockStateResources.basicPortalState(JITL.bml("block/" + name + "_ew"), JITL.bml("block/" + name + "_ns")));
    }

    private static void registerCampfireBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.campfireState(JITL.bml("block/" + name), JITL.bml("block/" + name + "_off")))
                .model(JITL.bml("block/" + name), () -> JBlockModels.campfireOn(JITL.tl("block/" + name + "_log_lit"), JITL.tl("block/" + name + "_fire")))
                .model(JITL.bml("block/" + name + "_off"), () -> JBlockModels.campfireOff(JITL.tl("block/" + name + "_log")));
    }

    private static void registerSlabBlock(String name, String enName, String textureName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.slabState(JITL.bml("block/" + name), JITL.bml("block/" + textureName), JITL.bml("block/" + name + "_top")))
                .model(JITL.bml("block/" + name), () -> JBlockModels.slab(JITL.tl("block/" + textureName)))
                .model(JITL.bml("block/" + name + "_top"), () -> JBlockModels.slab(JITL.tl("block/" + textureName)));
    }

    private static void registerRandomizedTextureBlock(String name, String enName, Supplier<Block> blockSupplier, CreativeModeTab creativeModeTab, Supplier<RenderTypeWrapper> renderType) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(renderType)
                .defaultBlockItem(creativeModeTab)
                .state(JBlockStateResources.randomizedTexture(JITL.bml("block/" + name), JITL.bml("block/" + name + "_0")))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.cubeAllModel(JITL.tl("block/" + name)))
                .model(JITL.bml("block/" + name + "_0"),
                        () -> BlockModels.cubeAllModel(JITL.tl("block/" + name + "_0")));
    }

    private static void registerRandomizedRotatedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.randomizedRotatedAll(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.cubeAllModel(JITL.tl("block/" + name)));
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
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name + "_top"));
                })
                .state(JBlockStateResources.doublePlantState(JITL.bml("block/" + name + "_bottom"), JITL.bml("block/" + name + "_top")))
                .model(JITL.bml("block/" + name + "_top"),
                        () -> BlockModels.crossModel(JITL.tl("block/" + name + "_top")))
                .model(JITL.bml("block/" + name + "_bottom"),
                        () -> BlockModels.crossModel(JITL.tl("block/" + name + "_bottom")));
    }

    private static void registerTallCropRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT_MIPPED)
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name + "_top"));
                })
                .state(JBlockStateResources.doublePlantState(JITL.bml("block/" + name + "_bottom"), JITL.bml("block/" + name + "_top")))
                .model(JITL.bml("block/" + name + "_top"),
                        () -> JBlockModels.crop(JITL.tl("block/" + name + "_top")))
                .model(JITL.bml("block/" + name + "_bottom"),
                        () -> JBlockModels.crop(JITL.tl("block/" + name + "_bottom")));
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
                                JITL.tl("block/" + frontTexture),
                                JITL.tl("block/" + sideTexture)));
    }

    private static void registerAttachedRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String texture) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name));
                })
                .state(JBlockStateResources.orientableStateAllSides(JITL.bml("block/" + name)))
                .model(JITL.bml("block/" + name),
                        () -> BlockModels.crossModel(JITL.tl("block/" + texture)));
    }

    private static void registerCustomAttachedRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.BLOCKS)
                .state(JBlockStateResources.orientableStateAllSides(JITL.bml("block/" + name)));
    }

    /**
     * Registers a berry bush block
     */
    //TODO remove itemblock, bind berry bush to 'berries' item
    private static void registerBerryBushBlock(String name, String enName, Supplier<ItemLike> itemProviderSupplier) {
        REGISTER.register(name, () -> new JBerryBushBlock(
                        (JBlockProperties.BERRY_BUSH_PROPS.create()), itemProviderSupplier))
                .name(enName)
                .renderLayer(() -> RenderTypeWrappers.CUTOUT)
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name + "_0"));
                })
                .also((chain) -> {
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
                .defaultBlockItem(JTabs.DECORATION, (itemChain) -> {
                    itemChain.defaultModel(JITL.tl("block/" + name + "_0"));
                })
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
