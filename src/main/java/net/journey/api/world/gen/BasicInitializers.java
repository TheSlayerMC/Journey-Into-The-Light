package net.journey.api.world.gen;

import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Random;

public class BasicInitializers {
	public static class Spawner extends TECompatibleChunkPrimer.TileEntityInitializer<TileEntityMobSpawner> {
		private ResourceLocation entityId;

		public Spawner(ResourceLocation entityId) {
			super(TileEntityMobSpawner.class);
			this.entityId = entityId;
		}

		@Override
		protected void init(World world, TileEntityMobSpawner tileEntity, Random rand) {
			tileEntity.getSpawnerBaseLogic().setEntityId(entityId);
		}
	}

	public static class VanillaChest extends TECompatibleChunkPrimer.TileEntityInitializer<TileEntityChest> {
		private ResourceLocation lootTable;

		public VanillaChest(ResourceLocation lootTable) {
			super(TileEntityChest.class);
			this.lootTable = lootTable;
		}

		@Override
		protected void init(World world, TileEntityChest tileEntity, Random rand) {
			tileEntity.setLootTable(lootTable, rand.nextLong());
		}
	}

	public static class JourneyChest extends TECompatibleChunkPrimer.TileEntityInitializer<TileEntityJourneyChest> {
		private ResourceLocation lootTable;

		public JourneyChest(ResourceLocation lootTable) {
			super(TileEntityJourneyChest.class);
			this.lootTable = lootTable;
		}

		@Override
		protected void init(World world, TileEntityJourneyChest tileEntity, Random rand) {
			tileEntity.setLootTable(lootTable, rand.nextLong());
		}
	}
}
