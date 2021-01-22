package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.api.block.GroundPredicate;
import net.jitl.client.render.JBlockModels;
import net.jitl.client.render.JBlockStateResources;
import net.jitl.common.block.*;
import net.jitl.common.block.base.*;
import net.jitl.common.block.portal.JBasePortalBlock;
import net.jitl.common.dimension.Dimensions;
import net.jitl.common.helper.EnumHarvestLevel;
import net.jitl.common.tile.EssenciaAltarTile;
import net.jitl.init.JBlocks;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JTabs;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;
import ru.timeconqueror.timecore.api.client.resource.BlockModel;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource;
import ru.timeconqueror.timecore.api.client.resource.BlockStateResources;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.BlockRegister.BlockRegisterChain;
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

        registerSpeciallyRenderedBlock("essencia_altar", "Essencia Altar", () -> new JTileContainerBlock(JBlockProperties.BRICK_PROPS.create(), (blockState, iBlockReader) -> new EssenciaAltarTile()),
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


        registerDefaultBlock("dungeon_bricks", "Dungeon Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("dungeon_bricks_carved", "Carved Dungeon Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("dungeon_bricks_chiseled", "Chiseled Dungeon Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("dungeon_bricks_cracked", "Cracked Dungeon Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerDefaultBlock("dungeon_lamp", "Dungeon Lamp", () -> new Block(JBlockProperties.BRICK_PROPS.create().lightLevel((state) -> 14)));
        RegistryObject<Block> gildedDungeonBricks = registerBlock("gilded_dungeon_bricks", "Gilded Dungeon Bricks", () -> new Block(JBlockProperties.BRICK_PROPS.create()));
        registerStairs("gilded_dungeon_stairs", "Gilded Dungeon Stairs", gildedDungeonBricks, JBlockProperties.BRICK_PROPS.create());
        registerDefaultBlock("dungeon_floor", "Dungeon Floor", () -> new Block(JBlockProperties.BRICK_PROPS.create()));

        registerDefaultBlock("common_gems", "Common Gems");
        registerDefaultBlock("rare_gems", "Rare Gems");

        registerOrientableRenderedBlock("iron_crate", "Iron Crate", () -> new HorizonSideFacedBlock(JBlockProperties.WOOD_PROPS.create()),
                "iron_crate_top",
                "iron_crate_side",
                "iron_crate_front");

        registerDefaultBlock("block_of_mud", "Block O' Mud", () -> new Block(JBlockProperties.MUD_PROPS.create()));

        registerDefaultBlock("euca_brick", "Euca Brick");

        registerDefaultBlock("laser_emitter", "Laser Emitter", () -> new LaserEmitterBlock(JBlockProperties.STONE_PROPS.create().noOcclusion()));
        registerCutoutMippedRenderedBlock("test_spawner", "Test Spawner", () -> new JSpawnerBlock(JEntities.WITHERSPINE_TYPE), JTabs.SPAWNERS);

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

        registerDefaultBlock("euca_portal", "Euca Portal", () -> new JBasePortalBlock(JBlockProperties.PORTAL.create(), Dimensions.EUCA, JBlocks.EUCA_PORTAL_FRAME));

        registerSpeciallyRenderedBlock("goldite_grass_block", "Goldite Grass", () -> new Block(JBlockProperties.GRASS_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/goldite_grass_block_top"), JITL.tl("block/goldite_grass_block_side"), JITL.tl("block/goldite_dirt")));
        registerDefaultBlock("goldite_dirt", "Goldite Dirt", () -> new Block(JBlockProperties.DIRT_PROPS.create()));
        registerDefaultBlock("goldite_stone", "Goldite Stone", () -> new Block(JBlockProperties.STONE_PROPS.create()));

        registerSpeciallyRenderedBlock("euca_silver_grass_block", "Euca Silver Grass", () -> new Block(JBlockProperties.GRASS_PROPS.create()),
                () -> BlockModels.cubeBottomTopModel(JITL.tl("block/euca_silver_grass_block_top"), JITL.tl("block/euca_silver_grass_block_side"), JITL.tl("block/euca_silver_dirt")));
        registerDefaultBlock("euca_silver_dirt", "Euca Silver Dirt", () -> new Block(JBlockProperties.DIRT_PROPS.create()));
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
     * Registers a 'stairs' block
     */
    private static void registerStairs(String name, String enName, RegistryObject<? extends Block> sourceBlock, AbstractBlock.Properties properties) {
        REGISTER.register(name, () -> new StairsBlock(Blocks.AIR::defaultBlockState, properties))
                .also(chain -> {
                    BlockModelLocation stairs = new BlockModelLocation(chain.getModId(), name);
                    BlockModelLocation innerStairs = new BlockModelLocation(chain.getModId(), name + "/inner");
                    BlockModelLocation outerStairs = new BlockModelLocation(chain.getModId(), name + "/outer");
                    TextureLocation sourceBlockTexture = new TextureLocation(chain.getModId(), "block/" + sourceBlock.getId().getPath());

                    chain.model(stairs, BlockModels.stairsModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture));
                    chain.model(innerStairs, BlockModels.stairsInnerModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture));
                    chain.model(outerStairs, BlockModels.stairsOuterModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture));

                    BlockStateResource state = BlockStateResources.stairs(stairs, innerStairs, outerStairs);
                    chain.state(state);
                })
                .defaultBlockItem(JTabs.BLOCKS)
                .name(enName);
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

    private static void registerCutoutMippedRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, ItemGroup cTab) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
                .renderLayer(RenderType::cutoutMipped)
                .defaultBlockItem(cTab)
                .oneVarStateAndCubeAllModel();
    }

    /**
     * Registers a block with a BlockModel supplier
     */
    private static void registerSpeciallyRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, Supplier<BlockModel> blockModelSupplier) {
        REGISTER.register(name, blockSupplier)
                .name(enName)
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
                .renderLayer(RenderType::cutoutMipped)
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
                .renderLayer(RenderType::cutout)
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
                .renderLayer(RenderType::cutout)
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

    /**
     * Registers a berry bush block
     */
    //TODO remove itemblock, bind berry bush to 'berries' item
    private static void registerBerryBushBlock(String name, String enName, Supplier<IItemProvider> itemProviderSupplier) {
        REGISTER.register(name, () -> new JBerryBushBlock(
                (JBlockProperties.BERRY_BUSH_PROPS.create()), itemProviderSupplier))
                .name(enName)
                .renderLayer(RenderType::cutout)
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
}
