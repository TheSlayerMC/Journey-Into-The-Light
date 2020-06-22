package net.journey.eventhandler.migration;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import ru.timeconqueror.timecore.api.util.Pair;

import java.util.Map;

public class ItemMigrator extends Migrator<Item> {
	@Override
	public void regRemappers() {

	}

	@Override
	public Map<ResourceLocation, Item> getRemappers() {
		super.getRemappers();

		new BlockMigrator().getRemappers().entrySet().stream()
				.map(entry -> Pair.of(entry.getKey(), Item.getItemFromBlock(entry.getValue())))
				.filter(pair -> pair.getB() != Items.AIR) //removing blocks without items
				.forEach(pair -> remappers.put(pair.getA(), pair.getB()));

		return remappers;
	}
}
