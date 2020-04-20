package net.journey.blocks.crop;

import net.journey.init.common.JourneyCrops;
import net.journey.init.items.JourneyConsumables;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.Item;
import net.slayer.api.block.BlockModCrop;

public class BlockCorveggieCrop extends BlockModCrop {

    public BlockCorveggieCrop(String name) {
        super(name);
    }

    @Override
    public PropertyInteger getAge() {
        return PropertyInteger.create("age", 0, 2);
    }

    @Override
    public Item getSeed() {
        return JourneyCrops.corveggieSeeds;
    }

    @Override
    public Item getCrop() {
        return JourneyConsumables.corveggies;
    }

    @Override
    public int getStages() {
        return 2;
    }
}