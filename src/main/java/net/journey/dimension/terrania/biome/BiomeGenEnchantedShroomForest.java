package net.journey.dimension.terrania.biome;

import java.awt.Color;
import java.util.Random;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.dimension.terrania.gen.shroom.WorldGenTerrashroom;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenEnchantedShroomForest extends JDimensionBiome {
	
	public BiomeGenEnchantedShroomForest() {
		super(new BiomeProperties("Enchanted Shroom Forest"));
		this.topBlock = JourneyBlocks.terranianGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.terranianDirt.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xC40600;
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0xC40600;
	}

	@Override
	public int getWaterColorMultiplier() {
		return 0xC40600;
	}

	@Override
	public boolean canRain() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.getHSBColor(0.955F, 0.316F, 0.5F).getRGB();
	}
}