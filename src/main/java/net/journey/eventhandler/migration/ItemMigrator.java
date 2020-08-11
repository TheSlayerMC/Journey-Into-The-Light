package net.journey.eventhandler.migration;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class ItemMigrator extends Migrator<Item> {
    @Override
    public void regRemappers() {

    }

    @Override
    public Map<ResourceLocation, MappingEntryResolver<Item>> getRemappers() {
        super.getRemappers();

        new BlockMigrator().getRemappers().forEach((missingKey, value) -> {
            Block newEntry = value.getNewEntry();
            if (newEntry != null) {
                Item itemBlock = Item.getItemFromBlock(newEntry);

                if (itemBlock != Items.AIR) {
                    remap(missingKey.getPath(), itemBlock);
                }
            } else {
                // noinspection rawtypes
                remappers.put(missingKey, ((MappingEntryResolver) value));
            }
        });

        return remappers;
    }
}
