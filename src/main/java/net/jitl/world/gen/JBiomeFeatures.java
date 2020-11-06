package net.jitl.world.gen;

import net.jitl.JITL;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JBiomeFeatures {

	@SubscribeEvent
	public static void addOverworldOres(FMLLoadCompleteEvent event) {
		//BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(); //this isn't probably right
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome.getBiomeCategory() == Biome.Category.NETHER) {
				//Do Nether ores here
			}
			if (biome.getBiomeCategory() == Biome.Category.THEEND) {
				//Do End ores here
			} else {
				//Do Overworld ores here
				//builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, JOreGen.ORE_SAPPHIRE).build();
			}
		}
	}
}
