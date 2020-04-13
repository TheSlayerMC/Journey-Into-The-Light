package net.journey.dimension.cloudia;

import java.awt.Color;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCloudia extends Biome {

	public BiomeGenCloudia() {
		super(new BiomeProperties("Cloudia"));
		this.topBlock = JourneyBlocks.cloudiaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.cloudiaDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(1.0F, 0.255F, 0.208F).getRGB();
    }
}