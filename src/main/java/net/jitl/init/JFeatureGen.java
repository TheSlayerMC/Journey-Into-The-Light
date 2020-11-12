package net.jitl.init;

import net.jitl.JITL;
import net.jitl.util.JRuleTests;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class JFeatureGen {

	/**
	 * Used for registering ore features based on dimension ArrayLists.
	 */
	private static final ArrayList<ConfiguredFeature<?, ?>> OVERWORLD_ORES = new ArrayList<>();
	private static final ArrayList<ConfiguredFeature<?, ?>> NETHER_ORES = new ArrayList<>();
	private static final ArrayList<ConfiguredFeature<?, ?>> END_ORES = new ArrayList<>();

	/**
	 * Helper used to condense all feature registries into a single method.
	 * Called during preInit stage.
	 */
	public static void init(FMLCommonSetupEvent event) {
		// because of it's called from mod thread, we need to send this task to the main thread
		// otherwise 1 per 100 times you'll see a strange behaviour because of data races
		event.enqueueWork(JFeatureGen::registerOres);
	}

	/**
	 * Registers ore generation with an ArrayList based on dimension.
	 */
	public static void registerOres() {
		/**
		 * Size: Max vein size
		 * Range: Max generation height
		 * Count: Quantity that can spawn per chunk
		 */
		OVERWORLD_ORES.add(
				register("sapphire_ore", defaultOreFeature(
						JBlocks.SAPPHIRE_ORE.defaultBlockState(),
						JRuleTests.STONE_DEFAULT,
						7,
						24,
						2)));
		OVERWORLD_ORES.add(
				register("lunium_ore", defaultOreFeature(
						JBlocks.LUNIUM_ORE.defaultBlockState(),
						JRuleTests.STONE_DEFAULT,
						5,
						16,
						1)));
		OVERWORLD_ORES.add(
				register("shadium_ore", defaultOreFeature(
						JBlocks.SHADIUM_ORE.defaultBlockState(),
						JRuleTests.STONE_DEFAULT,
						3,
						10,
						1)));
		OVERWORLD_ORES.add(
				register("iridium_ore", defaultOreFeature(
						JBlocks.IRIDIUM_ORE.defaultBlockState(),
						JRuleTests.STONE_DEFAULT,
						7,
						10,
						16)));
		NETHER_ORES.add(
				register("hellcrust_ore", netherOreFeature(
						JBlocks.BLOODCRUST_ORE.defaultBlockState(),
						JRuleTests.STONE_NETHERRACK,
						10,
						10)));
		NETHER_ORES.add(
				register("firestone_ore", netherOreFeature(
						JBlocks.FIRESTONE_ORE.defaultBlockState(),
						JRuleTests.STONE_NETHERRACK,
						10,
						24)));
		END_ORES.add(
				register("enderillium_ore", defaultOreFeature(
						JBlocks.ENDERILLIUM_ORE.defaultBlockState(),
						JRuleTests.STONE_END,
						12,
						128,
						20)));
	}

	/**
	 * Creates an ore feature with basic parameters.
	 *
	 * @param ore        The ore being generated
	 * @param spawnBlock The RuleTest block that the ore can generate in
	 * @param size       The maximum size of the ore vein
	 * @param range      The maximum height the ore can generate within
	 * @param count      (unsure) Possible count per chunk, or possibly extra vein size.
	 * @return Returns a new Configured Ore Feature based on the params filled in the method
	 */
	private static ConfiguredFeature<?, ?> defaultOreFeature(BlockState ore, RuleTest spawnBlock, int size, int range, int count) {
		return Feature.ORE.configured(new OreFeatureConfig(spawnBlock, ore, size)).range(range).squared().count(count);
	}

	private static ConfiguredFeature<?, ?> netherOreFeature(BlockState ore, RuleTest spawnBlock, int size, int count) {
		return Feature.ORE.configured(new OreFeatureConfig(spawnBlock, ore, size)).decorated(Features.Placements.RANGE_10_20_ROOFED).squared().count(count);
	}

	/**
	 * Registers an ore feature into the default registry
	 *
	 * @param name              The unlocalized name of the feature entry
	 * @param configuredFeature The feature being registered
	 * @return Returns the default registry and registers the Configured Feature
	 */
	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
		//ForgeRegistries SUCK and make this harder than it needs to be, so I'm using the vanilla registry
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(JITL.MODID, name), configuredFeature);
	}

	/**
	 * Adds JITL biome features to default biomes during biome initialization
	 * Necessary event for adding modded features to the default generation stages
	 *
	 * @param event The event used upon biome initialization
	 */
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void addFeatures(BiomeLoadingEvent event) {
		genOres(event);
	}

	/**
	 * Generates JITL ores during the decoration stage of the biome loading event
	 * Ores are generated based on the parameters of the ore registries in the {@link #registerOres()} method
	 *
	 * @param event The event used upon biome initialization. Is linked to the {@link #addFeatures(BiomeLoadingEvent)} parameter
	 */
	private static void genOres(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder builder = event.getGeneration();

		if (event.getCategory().equals(Biome.Category.NETHER)) {
			for (ConfiguredFeature<?, ?> ore : NETHER_ORES) {
				if (ore != null) {
					builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
				}
			}
		}
		if (event.getCategory().equals(Biome.Category.THEEND)) {
			for (ConfiguredFeature<?, ?> ore : END_ORES) {
				if (ore != null) {
					builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
				}
			}
		} else {
			for (ConfiguredFeature<?, ?> ore : OVERWORLD_ORES) {
				if (ore != null) {
					builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
				}
			}
		}
	}
}