package net.journey.dimension;

import net.minecraft.init.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import scala.tools.nsc.doc.model.Public;

public class BiomeTypeHelper {

	net.minecraft.world.biome.Biome b;
	
	boolean FROZEN = BiomeDictionary.hasType(b, BiomeDictionary.Type.SNOWY) || BiomeDictionary.hasType(b, BiomeDictionary.Type.COLD);
	boolean DESERT = BiomeDictionary.hasType(b, BiomeDictionary.Type.SANDY) || BiomeDictionary.hasType(b, BiomeDictionary.Type.DRY);
	boolean DESERT_HELL = BiomeDictionary.hasType(b, BiomeDictionary.Type.SANDY) || BiomeDictionary.hasType(b, BiomeDictionary.Type.HOT);
	boolean GRASSY = BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST) || BiomeDictionary.hasType(b, BiomeDictionary.Type.LUSH) || BiomeDictionary.hasType(b, BiomeDictionary.Type.PLAINS);
}
