package net.jitl.datagen;

import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import ru.timeconqueror.timecore.devtools.gen.loottable.BlockLootTableSet;

public class JLootTableSet extends BlockLootTableSet {

	private final ILootCondition.IBuilder FLIP_COIN_CHANCE = RandomChance.randomChance(.5F);

	@Override
	public void register() {
		//test
		registerLootTable(JBlocks.ENDERILLIUM_ORE, block -> createSelfDropDispatchTable(block, FLIP_COIN_CHANCE, ItemLootEntry.lootTableItem(JItems.LUNIUM_INGOT.get())));

		registerDropsSelf(JBlocks.LUNIUM_ORE);
		registerDropsSelf(JBlocks.SHADIUM_ORE);
		registerDropsSelf(JBlocks.HELLSTONE_ORE);
	}
}
