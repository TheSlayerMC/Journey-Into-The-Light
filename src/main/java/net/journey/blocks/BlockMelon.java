package net.journey.blocks;

import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyConsumables;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockMelon extends BlockMod {

    public BlockMelon(String name, String finalName, Item drop) {
        super(EnumMaterialTypes.GOURD, name, finalName, JourneyTabs.crops);
    }

    @Override
    public int quantityDropped(Random random) {
        return 3 + random.nextInt(5);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return JourneyConsumables.airMelon;
    }
}
