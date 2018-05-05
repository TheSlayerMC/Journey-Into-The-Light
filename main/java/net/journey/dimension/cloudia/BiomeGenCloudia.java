package net.journey.dimension.cloudia;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCloudia extends Biome {

	public BiomeGenCloudia(String par1) {
		super(new BiomeProperties(par1));
		this.setRegistryName(par1);
		this.topBlock = JourneyBlocks.cloudiaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.cloudiaDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        getSkyColorByTemp(0x00FFD0);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0xFFB3DC;
    }
}