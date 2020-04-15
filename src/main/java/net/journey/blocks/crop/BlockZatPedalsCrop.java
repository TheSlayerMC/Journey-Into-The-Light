package net.journey.blocks.crop;

import net.journey.init.blocks.JourneyCrops;
import net.journey.init.items.JourneyConsumables;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.Item;
import net.slayer.api.block.BlockModCrop;

public class BlockZatPedalsCrop extends BlockModCrop {

    public BlockZatPedalsCrop(String name) {
        super(name);
    }

    @Override
    public PropertyInteger getAge() {
        return PropertyInteger.create("age", 0, 6);
    }

    @Override
    public Item getSeed() {
        return JourneyCrops.zatSeeds;
    }

    @Override
    public Item getCrop() {
        return JourneyConsumables.zatPedal;
    }

    @Override
    public int getStages() {
        return 6;
    }
}