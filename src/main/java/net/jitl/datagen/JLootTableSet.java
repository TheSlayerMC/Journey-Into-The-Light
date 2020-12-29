package net.jitl.datagen;

import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;
import ru.timeconqueror.timecore.api.devtools.gen.loottable.BlockLootTableSet;

public class JLootTableSet extends BlockLootTableSet {

	@Override
	public void register() {
		initOres();
		initBuildingBlocks();
	}

	private void initOres() {
		registerSpecialOre(JBlocks.SAPPHIRE_ORE, JItems.SAPPHIRE);
		registerSpecialOre(JBlocks.IRIDIUM_ORE, JItems.IRIDIUM_NUGGET);
		registerSpecialOreWithExtraCount(JBlocks.LUNIUM_ORE, JItems.LUNIUM_POWDER, 1, 2);
		registerDefaultOre(JBlocks.SHADIUM_ORE);
		registerDefaultOre(JBlocks.BLOODCRUST_ORE);
		registerSpecialOreWithExtraCount(JBlocks.FIRESTONE_ORE, JItems.FIRESTONE_SHARD, 1, 4);
		registerSpecialOre(JBlocks.WARPED_QUARTZ_ORE, JItems.WARPED_QUARTZ);
		registerSpecialOre(JBlocks.CRIMSON_QUARTZ_ORE, JItems.CRIMSON_QUARTZ);
	}

	private void initBuildingBlocks() {
		registerDropsSelf(JBlocks.DUNGEON_BRICKS);
		registerDropsSelf(JBlocks.DUNGEON_LAMP);
		registerDropsSelf(JBlocks.DUNGEON_BRICKS_CARVED);
		registerDropsSelf(JBlocks.DUNGEON_BRICKS_CHISELED);
		registerDropsSelf(JBlocks.DUNGEON_BRICKS_CRACKED);
		registerDropsSelf(JBlocks.DUNGEON_FLOOR);

		registerDropsSelf(JBlocks.BLOOD_ROCK);
		registerDropsSelf(JBlocks.BLOOD_ROCK_BRICKS);
		registerDropsSelf(JBlocks.EMPTY_BLOOD_RUNE);
		registerDropsSelf(JBlocks.CARVED_BLOOD_ROCK);
		registerDropsSelf(JBlocks.SMOOTH_BLOOD_ROCK);
		registerDropsSelf(JBlocks.BLOOD_ROCK_PILLAR);
		registerDropsSelf(JBlocks.BLOOD_RUNE_DEATH);
		registerDropsSelf(JBlocks.BLOOD_RUNE_FLESH);
		registerDropsSelf(JBlocks.BLOOD_RUNE_LIFE);
		registerDropsSelf(JBlocks.BLOOD_RUNE_SOUL);

		registerSpecialOreWithExtraCount(JBlocks.BLOCK_OF_MUD, JItems.MUD_BALL, 3, 4);
	}

	/**
	 * Registers a loot table that drops a different item with luck when mined with a normal tool.
	 * When mined with a silk-touch tool, the block will drop itself.
	 * Behaves just like diamond or emerald ore.
	 *
	 * @param block The block being registered, and also the block being dropped when mined with a silktouch tool.
	 * @param drop  The item dropped by the block when mined with a normal tool.
	 */
	public void registerSpecialOre(Block block, Item drop) {
		registerLootTable(block, createSilkTouchWithLuckDispatchTable(block, drop));
	}

	/**
	 * Registers a loot table that drops a different item with luck when mined with a normal tool.
	 * When mined with a silk-touch tool, the block will drop itself.
	 * Behaves just like diamond or emerald ore.
	 *
	 * @param block The block being registered, and also the block being dropped when mined with a silktouch tool.
	 * @param drop  The item dropped by the block when mined with a normal tool.
	 */
	public void registerSpecialOreWithExtraCount(Block block, Item drop, int minimumQuantity, int maximumQuantity) {
		registerLootTable(block, createSilkTouchWithExtraLuckDispatchTable(block, drop, minimumQuantity, maximumQuantity));
	}

	/**
	 * Registers a loot table that drops itself in every condition, even in the case of explosions.
	 *
	 * @param block The block being registered, and also the block being dropped.
	 */
	public void registerDefaultOre(Block block) {
		registerWithExplosionDecay(block, block);
	}

	/**
	 * Registers a loot table that can drop in the case of explosions.
	 *
	 * @param block The block being registered.
	 * @param drop  The item or block being dropped.
	 */
	public void registerWithExplosionDecay(Block block, IItemProvider drop) {
		registerLootTable(block, createSingleItemWithExplosionDecayTable(drop));
	}

	/**
	 * Creates a table where {@code silkTouchDrop} is dropped when the player is using a tool with silktouch.
	 * If the player is not using a tool with silktouch, the table will drop the {@code dropWithoutSilkTouch} item.
	 * Depending if the player has a tool with the fortune enchantment, and the level of the enchantment, it will drop extra of the {@code dropWithoutSilkTouch} drop.
	 *
	 * @param silkTouchDrop        The block dropped when the user's tool has silktouch applied.
	 * @param dropWithoutSilkTouch The item dropped when the tool doesn't have silktouch applied.
	 */
	protected static LootTable.Builder createSilkTouchWithLuckDispatchTable(Block silkTouchDrop, IItemProvider dropWithoutSilkTouch) {
		return createSilkTouchDispatchTable(silkTouchDrop, applyExplosionDecay(ItemLootEntry.lootTableItem(dropWithoutSilkTouch).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
	}

	/**
	 * Creates a table where {@code silkTouchDrop} is dropped when the player is using a tool with silktouch.
	 * If the player is not using a tool with silktouch, the table will drop the {@code dropWithoutSilkTouch} item.
	 * Depending if the player has a tool with the fortune enchantment, and the level of the enchantment, it will drop extra of the {@code dropWithoutSilkTouch} drop.
	 * This specific table drops multiple of the item, regardless of luck applied.
	 *
	 * @param silkTouchDrop        The block dropped when the user's tool has silktouch applied.
	 * @param dropWithoutSilkTouch The item dropped when the tool doesn't have silktouch applied.
	 */
	protected static LootTable.Builder createSilkTouchWithExtraLuckDispatchTable(Block silkTouchDrop, IItemProvider dropWithoutSilkTouch, int minimumQuantity, int maximumQuantity) {
		return createSilkTouchDispatchTable(silkTouchDrop, applyExplosionDecay(ItemLootEntry.lootTableItem(dropWithoutSilkTouch).apply(SetCount.setCount(RandomValueRange.between(minimumQuantity, maximumQuantity))).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
	}

	/**
	 * Creates a table where the {@code drop} is always dropped, even in the case of explosions.
	 *
	 * @param drop The block or item being dropped.
	 */
	protected static LootTable.Builder createSingleItemWithExplosionDecayTable(IItemProvider drop) {
		return LootTable.lootTable()
				.withPool(applyExplosionDecay(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(drop))));
	}
}
