package net.journey.dimension.nether.gen;

import java.util.List;

import com.google.common.collect.Lists;

import net.journey.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;

public class WorldGenFortressChestContent extends StructureNetherBridgePieces {

	protected static final List chestLoot = Lists.newArrayList(new WeightedRandomChestContent[] {
			new WeightedRandomChestContent(JourneyItems.boilKey, 0, 1, 1, 10)});
	
    static { net.minecraftforge.common.ChestGenHooks.init(net.minecraftforge.common.ChestGenHooks.NETHER_FORTRESS, chestLoot, 2, 5);  
    }
}

