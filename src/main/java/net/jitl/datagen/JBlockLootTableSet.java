package net.jitl.datagen;

import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import ru.timeconqueror.timecore.api.devtools.gen.loottable.BlockLootTableSet;

public class JBlockLootTableSet extends BlockLootTableSet {

	@Override
	public void register() {
		initOres();
		initBuildingBlocks();
		initFoliageBlocks();
		initUtilityBlocks();
	}

	private void initOres() {
		registerSpecialOre(JBlocks.SAPPHIRE_ORE, JItems.SAPPHIRE);
		registerSpecialOre(JBlocks.PERIDOT_ORE, JItems.PERIDOT_GEMSTONE);
		registerSpecialOre(JBlocks.RIMESTONE_ORE, JItems.RIMESTONE);
		registerSpecialOre(JBlocks.IRIDIUM_ORE, JItems.IRIDIUM_NUGGET);
		registerSpecialOreWithExtraCount(JBlocks.LUNIUM_ORE, JItems.LUNIUM_POWDER, 1, 2);
		registerSpecialOre(JBlocks.SHADIUM_ORE, JItems.RAW_SHADIUM);
		registerSpecialOre(JBlocks.DEEPSLATE_SHADIUM_ORE, JItems.RAW_SHADIUM);
		registerSpecialOre(JBlocks.BLOODCRUST_ORE, JItems.RAW_BLOODCRUST);
		registerSpecialOreWithExtraCount(JBlocks.FIRESTONE_ORE, JItems.FIRESTONE_SHARD, 1, 4);
		registerSpecialOre(JBlocks.WARPED_QUARTZ_ORE, JItems.WARPED_QUARTZ);
		registerSpecialOre(JBlocks.CRIMSON_QUARTZ_ORE, JItems.CRIMSON_QUARTZ);
		registerDefaultOre(JBlocks.SULPHUR_ROCK);
		registerSpecialOreWithExtraCount(JBlocks.SULPHUR_CRYSTAL, JItems.SULPHUR_POWDER, 1, 4);
		registerSpecialOre(JBlocks.MEKYUM_ORE, JItems.RAW_MEKYUM);
		registerSpecialOre(JBlocks.BLAZIUM_ORE, JItems.RAW_BLAZIUM);
		registerSpecialOre(JBlocks.CELESTIUM_ORE, JItems.RAW_CELESTIUM);
		registerSpecialOre(JBlocks.KORITE_ORE, JItems.RAW_KORITE);
		registerSpecialOre(JBlocks.STORON_ORE, JItems.RAW_STORON);
		registerSpecialOre(JBlocks.ENDERILLIUM_ORE, JItems.ENDERILLIUM_SHARD);
	}

	private void initBuildingBlocks() {
		registerDropsSelf(JBlocks.DUNGEON_BRICKS);
		registerDropsSelf(JBlocks.DUNGEON_LAMP);
		registerDropsSelf(JBlocks.CARVED_DUNGEON_BRICKS);
		registerDropsSelf(JBlocks.CHISELED_DUNGEON_BRICKS);
		registerDropsSelf(JBlocks.CRACKED_DUNGEON_BRICKS);
		registerDropsSelf(JBlocks.DUNGEON_FLOOR);

		registerDropsSelf(JBlocks.BLOOD_ROCK);
		registerDropsSelf(JBlocks.BLOOD_ROCK_BRICKS);
		registerDropsSelf(JBlocks.EMPTY_BLOOD_RUNE);
		registerDropsSelf(JBlocks.CARVED_BLOOD_ROCK);
		registerDropsSelf(JBlocks.SMOOTH_BLOOD_ROCK);
		registerDropsSelf(JBlocks.BLOOD_ROCK_PILLAR);
		registerDropsOther(JBlocks.BLOOD_RUNE_DEATH, JBlocks.EMPTY_BLOOD_RUNE);
		registerDropsOther(JBlocks.BLOOD_RUNE_FLESH, JBlocks.EMPTY_BLOOD_RUNE);
		registerDropsOther(JBlocks.BLOOD_RUNE_LIFE, JBlocks.EMPTY_BLOOD_RUNE);
		registerDropsOther(JBlocks.BLOOD_RUNE_SOUL, JBlocks.EMPTY_BLOOD_RUNE);
		registerDropsOther(JBlocks.CHARGED_RUNIC_CONNECTOR, JBlocks.RUNIC_CONNECTOR);

		registerDropsOtherWithoutSilkTouch(JBlocks.GRASSY_PERMAFROST, JBlocks.CRUMBLED_PERMAFROST);
		registerDropsSelf(JBlocks.PERMAFROST);
		registerDropsSelf(JBlocks.CRUMBLED_PERMAFROST);

		registerDropsSelf(JBlocks.PACKED_SNOW_BRICKS);
		registerDropsSelf(JBlocks.PACKED_ICE_BRICKS);
		registerDropsSelf(JBlocks.BITTERWOOD_CAMPFIRE);

		registerDropsSelf(JBlocks.SCORCHED_STALAGMITE_LARGE);
		registerDropsSelf(JBlocks.SCORCHED_STALAGMITE_MED);
		registerDropsSelf(JBlocks.SCORCHED_STALAGMITE_SMALL);
		registerDropsSelf(JBlocks.SCORCHED_STALAGMITE_TINY);

		registerSpecialOreWithExtraCount(JBlocks.BLOCK_OF_MUD, JItems.MUD_BALL, 3, 4);
	}

	private void initFoliageBlocks() {
		registerDropsSelf(JBlocks.SCORCHED_CACTUS);

		registerShearsOnlyDropsSelf(JBlocks.ICY_IVY_PLANT);
		registerShearsOnlyDropsOther(JBlocks.ICY_IVY, JBlocks.ICY_IVY_PLANT);
		registerShearsOnlyDropsSelf(JBlocks.GLIMMER_ROOT_PLANT);
		registerShearsOnlyDropsOther(JBlocks.GLIMMER_ROOT, JBlocks.GLIMMER_ROOT_PLANT);
		registerShearsOnlyDropsSelf(JBlocks.CAVE_VINES_PLANT);
		registerShearsOnlyDropsOther(JBlocks.CAVE_VINES, JBlocks.CAVE_VINES_PLANT);

		registerShearsOnlyDropsSelf(JBlocks.ICE_BUSH);
		registerShearsOnlyDropsSelf(JBlocks.FROSTBERRY_THORN);
		registerShearsOnlyDropsSelf(JBlocks.ICY_BRUSH);
		registerDropsSelf(JBlocks.ICE_BUD);
		registerDropsSelf(JBlocks.FROZEN_BLOOM);
		registerDropsSelf(JBlocks.CICLEBLOOM);
		registerDropsSelf(JBlocks.ICE_SHROOM_SHELF);

		registerDropsSelf(JBlocks.CRYSTAL_FRUIT);
	}

	private void initUtilityBlocks() {
		registerSpecialOreWithExtraCount(JBlocks.ANCIENT_POTTERY, JItems.POTTERY_SHARD, 2, 5);
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
	public void registerWithExplosionDecay(Block block, ItemLike drop) {
		registerLootTable(block, createSingleItemWithExplosionDecayTable(drop));
	}

	public void registerShearsOnlyDropsSelf(Block block) {
		registerLootTable(block, createShearsOnlyTable(block));
	}

	public void registerShearsOnlyDropsOther(Block block, ItemLike drop) {
		registerLootTable(block, createShearsOnlyTable(drop));
	}

	public void registerDropsOtherWithoutSilkTouch(Block block, ItemLike otherDrop) {
		registerLootTable(block, createSilkTouchDispatchTable(block, otherDrop));
	}

	/**
	 * Creates a table where {@code silkTouchDrop} is dropped when the player is using a tool with silktouch.
	 * If the player is not using a tool with silktouch, the table will drop the {@code dropWithoutSilkTouch} item.
	 * Depending if the player has a tool with the fortune enchantment, and the level of the enchantment, it will drop extra of the {@code dropWithoutSilkTouch} drop.
	 *
	 * @param silkTouchDrop        The block dropped when the user's tool has silktouch applied.
	 * @param dropWithoutSilkTouch The item dropped when the tool doesn't have silktouch applied.
	 */
	protected static LootTable.Builder createSilkTouchWithLuckDispatchTable(Block silkTouchDrop, ItemLike dropWithoutSilkTouch) {
		return createSilkTouchDispatchTable(silkTouchDrop, applyExplosionDecay(LootItem.lootTableItem(dropWithoutSilkTouch).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
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
	protected static LootTable.Builder createSilkTouchWithExtraLuckDispatchTable(Block silkTouchDrop, ItemLike dropWithoutSilkTouch, int minimumQuantity, int maximumQuantity) {
		return createSilkTouchDispatchTable(silkTouchDrop, applyExplosionDecay(LootItem.lootTableItem(dropWithoutSilkTouch).apply(SetItemCountFunction.setCount(UniformGenerator.between(minimumQuantity, maximumQuantity))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
	}

	/**
	 * Creates a table where the {@code drop} is always dropped, even in the case of explosions.
	 *
	 * @param drop The block or item being dropped.
	 */
	protected static LootTable.Builder createSingleItemWithExplosionDecayTable(ItemLike drop) {
		return LootTable.lootTable()
				.withPool(applyExplosionDecay(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(drop))));
	}
}
