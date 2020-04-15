package net.journey.blocks.crop;

import net.journey.JourneyConsumables;
import net.journey.JourneyCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.Item;
import net.slayer.api.block.BlockModCrop;

public class BlockTomatoCrop extends BlockModCrop {

    public BlockTomatoCrop(String name) {
        super(name);
    }

    @Override
    public PropertyInteger getAge() {
        return PropertyInteger.create("age", 0, 7);
    }

    @Override
    public Item getSeed() {
        return JourneyCrops.tomatoSeeds;
    }

    @Override
    public Item getCrop() {
        return JourneyConsumables.tomato;
    }

    @Override
    public int getStages() {
        return 7;
    }
}