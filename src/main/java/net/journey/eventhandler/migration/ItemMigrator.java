package net.journey.eventhandler.migration;

import net.journey.init.items.JourneyWeapons;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class ItemMigrator extends Migrator<Item> {
    @Override
    public void regRemappers() {
        remap("hammerCreative", JourneyWeapons.creativeHammer);
        remap("earthenHammer", JourneyWeapons.earthenHammer);
        remap("flamingHammer", JourneyWeapons.flamingHammer);
        remap("nethicHammer", JourneyWeapons.nethicHammer);
        remap("withicHammer", JourneyWeapons.withicHammer);
        remap("royalHammer", JourneyWeapons.royalHammer);
        remap("overgrownHammer", JourneyWeapons.overgrownHammer);
        remap("rockyHammer", JourneyWeapons.rockyHammer);
        remap("crystalizedHammer", JourneyWeapons.crystallizedHammer);

        ignore("opgoldenchicken");
        ignore("opgoldenpork");
        ignore("opgoldensteak");
        ignore("opgoldenmutton");
        ignore("normalgoldenchicken");
        ignore("opgoldenfish");
        ignore("normalgoldenfish");
        ignore("normalgoldenpork");
        ignore("normalgoldenrabbit");
        ignore("normalgoldenmutton");
        ignore("normalgoldensteak");
        ignore("opgoldenrabbit");
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
