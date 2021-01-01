package net.jitl.init.internal;

import net.jitl.JITL;
import net.jitl.client.render.JBlockModels;
import net.jitl.common.block.JOreBlock;
import net.jitl.common.block.LaserEmitterBlock;
import net.jitl.common.block.base.BloodRuneBlock;
import net.jitl.common.block.base.JBerryBushBlock;
import net.jitl.common.block.base.JBlock;
import net.jitl.common.block.base.JOrientableBlock;
import net.jitl.common.helper.EnumHarvestLevel;
import net.jitl.init.JItems;
import net.jitl.init.JTabs;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.*;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.client.resource.BlockStateResource;
import ru.timeconqueror.timecore.api.client.resource.BlockStateResources;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;
import ru.timeconqueror.timecore.api.client.resource.location.TextureLocation;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import java.util.function.Supplier;

public class BlockRegistrator {

    @AutoRegistrable
    private static final BlockRegister REGISTER = new BlockRegister(JITL.MODID);

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

		registerDefaultBlock("blood_rock", "Blood Rock", () -> new JBlock(JBlockProperties.NETHER_BASALT_ORE_PROPS.create()));
		registerDefaultBlock("blood_rock_bricks", "Blood Rock Bricks", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()));
		registerDefaultBlock("smooth_blood_rock", "Smooth Blood Rock", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()));
		registerDefaultBlock("carved_blood_rock", "Carved Blood Rock", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()));
		registerDefaultBlock("empty_blood_rune", "Empty Blood Rune", () -> new BloodRuneBlock(JBlockProperties.BRICK_PROPS.create()));
		registerColumnRenderedBlock("blood_rock_pillar", "Block Rock Pillar", () -> new RotatedPillarBlock(JBlockProperties.BRICK_PROPS.create()), "smooth_blood_rock", "blood_rock_pillar_side");
		registerEmissiveAllRenderedBlock("blood_rune_soul", "Blood Rune Of Soul", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()), "blood_rune_soul_back", "blood_rune_soul_front");
		registerEmissiveAllRenderedBlock("blood_rune_flesh", "Blood Rune Of Flesh", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()), "blood_rune_flesh_back", "blood_rune_flesh_front");
		registerEmissiveAllRenderedBlock("blood_rune_life", "Blood Rune Of Life", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()), "blood_rune_life_back", "blood_rune_life_front");
		registerEmissiveAllRenderedBlock("blood_rune_death", "Blood Rune Of Death", () -> new JBlock(JBlockProperties.BRICK_PROPS.create()), "blood_rune_death_back", "blood_rune_death_front");

		registerDefaultBlock("sapphire_block", "Sapphire Block");
		registerSpeciallyRenderedBlock("lunium_block", "Lunium Block", () -> new JBlock(JBlockProperties.LUNIUM_BLOCK_PROPS.create().lightLevel((state) -> 5)));
		registerDefaultBlock("shadium_block", "Shadium Block", () -> new JBlock(JBlockProperties.SHADIUM_BLOCK_PROPS.create()));
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

		registerOrientableRenderedBlock("iron_crate", "Iron Crate", () -> new JOrientableBlock(JBlockProperties.WOOD_PROPS.create()),
				"iron_crate_top",
				"iron_crate_side",
				"iron_crate_front");

		registerDefaultBlock("block_of_mud", "Block O' Mud", () -> new Block(JBlockProperties.MUD_PROPS.create()));

		registerDefaultBlock("euca_brick", "Euca Brick");

		registerDefaultBlock("laser_emitter", "Laser Emitter", () -> new LaserEmitterBlock(JBlockProperties.STONE_PROPS.create().noOcclusion()));
	}

    private static void registerDefaultBlock(String name, String enName, Supplier<Block> blockSupplier) {
        REGISTER.register(name, blockSupplier)
                .genLangEntry(enName)
                .regDefaultBlockItem(JTabs.BLOCKS)
                .genDefaultStateAndModel();
    }

    private static RegistryObject<Block> registerDefaultBlock(String name, String enName) {
        return REGISTER.register(name, () -> new Block
                (JBlockProperties.STONE_PROPS.create()))
                .genLangEntry(enName)
                .regDefaultBlockItem(JTabs.BLOCKS)
                .genDefaultStateAndModel()
                .asRegistryObject();
    }

    private static RegistryObject<Block> registerBlock(String name, String enName, Supplier<Block> blockSupplier) {
        return REGISTER.register(name, blockSupplier)
                .genLangEntry(enName)
                .regDefaultBlockItem(JTabs.BLOCKS)
                .genDefaultStateAndModel()
                .asRegistryObject();
    }

    private static void registerStairs(String name, String enName, RegistryObject<? extends Block> sourceBlock, AbstractBlock.Properties properties) {
        REGISTER.register(name, () -> new StairsBlock(Blocks.AIR::defaultBlockState, properties))
				.also(chain -> {
					BlockModelLocation stairs = new BlockModelLocation(chain.getModId(), name);
					BlockModelLocation innerStairs = new BlockModelLocation(chain.getModId(), name + "/inner");
					BlockModelLocation outerStairs = new BlockModelLocation(chain.getModId(), name + "/outer");
					TextureLocation sourceBlockTexture = new TextureLocation(chain.getModId(), "block/" + sourceBlock.getId().getPath());

					chain.genModel(stairs, BlockModels.stairsModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture));
					chain.genModel(innerStairs, BlockModels.stairsInnerModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture));
					chain.genModel(outerStairs, BlockModels.stairsOuterModel(sourceBlockTexture, sourceBlockTexture, sourceBlockTexture));

					BlockStateResource state = BlockStateResources.stairs(stairs, innerStairs, outerStairs);
					chain.genState(state);
				})
                .regDefaultBlockItem(JTabs.BLOCKS)
                .genLangEntry(enName);
    }

	private static void registerOreBlock(String name, String enName, EnumHarvestLevel harvestLevel, int minExp) {
		REGISTER.register(name, () -> new JOreBlock
				(JBlockProperties.ORE_PROPS.create()
						.harvestLevel(harvestLevel.getInt()))
				.setExpDrop(minExp))
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS)
				.genDefaultStateAndModel();
	}

	private static void registerSpeciallyRenderedBlock(String name, String enName, Supplier<Block> blockSupplier) {
		REGISTER.register(name, blockSupplier)
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS)
				.genDefaultState(new BlockModelLocation(JITL.MODID, "block/" + name));
	}

	private static void registerColumnRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String topTexture, String sideTexture) {
		REGISTER.register(name, blockSupplier)
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS)
				.genDefaultState(JITL.bml("block/" + name))
				.genModel(JITL.bml("block/" + name),
						() -> BlockModels.cubeColumnModel(JITL.tl("block/" + topTexture), JITL.tl("block/" + sideTexture)));
	}

	private static void registerEmissiveAllRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String backTexture, String frontTexture) {
		REGISTER.register(name, blockSupplier)
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS)
				.genDefaultState(JITL.bml("block/" + name))
				.genModel(JITL.bml("block/" + name),
						() -> JBlockModels.emissiveCubeAll(JITL.tl("block/" + backTexture), JITL.tl("block/" + frontTexture)));
	}

	private static void registerOrientableRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String topTexture, String sideTexture, String frontTexture) {
		REGISTER.register(name, blockSupplier)
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS)
				.genDefaultState(JITL.bml("block/" + name))
				.genModel(JITL.bml("block/" + name),
						() -> JBlockModels.cubeOrientableModel(
								JITL.tl("block/" + topTexture),
								JITL.tl("block/" + sideTexture),
								JITL.tl("block/" + frontTexture)));
	}

	private static void registerPillarRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String topTexture, String sideTexture) {
		REGISTER.register(name, blockSupplier)
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS)
				.genDefaultState(JITL.bml("block/" + name))
				.genModel(JITL.bml("block/" + name),
						() -> BlockModels.cubeColumnModel(
								JITL.tl("block/" + topTexture),
								JITL.tl("block/" + sideTexture)));
	}

	//TODO remove itemblock, bind berry bush to 'berries' item
	private static void registerBerryBushBlock(String name, String enName, Supplier<IItemProvider> itemProviderSupplier) {
		REGISTER.register(name, () -> new JBerryBushBlock(
				(JBlockProperties.BERRY_BUSH_PROPS.create()), itemProviderSupplier))
				.genLangEntry(enName)
				.regDefaultBlockItem(JTabs.BLOCKS).also((chain) -> {
			String model0 = "block/" + name + "_0";
			String model1 = "block/" + name + "_1";
			String model2 = "block/" + name + "_2";
			String model3 = "block/" + name + "_3";

			chain.genModel(JITL.bml(model0), () -> BlockModels.crossModel(JITL.tl(model0)))
					.genModel(JITL.bml(model1), () -> BlockModels.crossModel(JITL.tl(model1)))
					.genModel(JITL.bml(model2), () -> BlockModels.crossModel(JITL.tl(model2)))
					.genModel(JITL.bml(model3), () -> BlockModels.crossModel(JITL.tl(model3)))
					.genState(() -> BlockStateResource.fromBuilder(BlockStateResource.Builder.create()
							.addVariant(new BlockStateResource.Variant("age=0", JITL.bml(model0)))
							.addVariant(new BlockStateResource.Variant("age=1", JITL.bml(model1)))
							.addVariant(new BlockStateResource.Variant("age=2", JITL.bml(model2)))
							.addVariant(new BlockStateResource.Variant("age=3", JITL.bml(model3)))));
		});
	}
}
