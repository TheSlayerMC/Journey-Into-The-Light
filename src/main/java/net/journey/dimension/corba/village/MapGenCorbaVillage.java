package net.journey.dimension.corba.village;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class MapGenCorbaVillage extends MapGenStructure {
	public static final List<Biome> VILLAGE_SPAWN_BIOMES = Collections.singletonList(DimensionHelper.CORBA_PLAINS_BIOME);
	private int size;
	private int distance;
	private final int minTownSeparation;

	public MapGenCorbaVillage() {
		this.distance = 32;
		this.minTownSeparation = 8;
	}

	public MapGenCorbaVillage(Map<String, String> map) {
		this();

		for (Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("size")) {
				this.size = MathHelper.getInt(entry.getValue(), this.size, 0);
			} else if (entry.getKey().equals("distance")) {
				this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
			}
		}
	}

	@Override
	public String getStructureName() {
		return "CorbaVillage";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int i = chunkX;
		int j = chunkZ;

		if (chunkX < 0) {
			chunkX -= this.distance - 1;
		}

		if (chunkZ < 0) {
			chunkZ -= this.distance - 1;
		}

		int k = chunkX / this.distance;
		int l = chunkZ / this.distance;
		Random random = this.world.setRandomSeed(k, l, 10387312);
		k = k * this.distance;
		l = l * this.distance;
		k = k + random.nextInt(this.distance - 8);
		l = l + random.nextInt(this.distance - 8);

		if (i == k && j == l) {
			return this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
		}
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		this.world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, 10387312, false, 100, findUnexplored);
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenCorbaVillage.Start(this.world, this.rand, chunkX, chunkZ, this.size);
	}

	public static class Start extends StructureStart {
		private boolean hasMoreThanTwoComponents;

		@Deprecated // is called via reflection, not for direct use
		public Start() {
		}

		public Start(World worldIn, Random rand, int x, int z, int size) {
			super(x, z);
			List<StructureCorbaVillagePieces.PieceWeight> list = StructureCorbaVillagePieces.getStructureVillageWeightedPieceList(rand, size);
			StructureCorbaVillagePieces.Start startPiece = new StructureCorbaVillagePieces.Start(worldIn.getBiomeProvider(), rand, (x << 4) + 2, (z << 4) + 2, list, size);
			this.components.add(startPiece);
			startPiece.buildComponent(startPiece, this.components, rand);
			List<StructureComponent> pendingRoads = startPiece.pendingRoads;
			List<StructureComponent> pendingHouses = startPiece.pendingHouses;

			while (!pendingRoads.isEmpty() || !pendingHouses.isEmpty()) {
				if (pendingRoads.isEmpty()) {
					int i = rand.nextInt(pendingHouses.size());
					StructureComponent house = pendingHouses.remove(i);
					house.buildComponent(startPiece, this.components, rand);
				} else {
					int j = rand.nextInt(pendingRoads.size());
					StructureComponent road = pendingRoads.remove(j);
					road.buildComponent(startPiece, this.components, rand);
				}
			}

			this.updateBoundingBox();
			int k = 0;

			for (StructureComponent component : this.components) {
				if (!(component instanceof StructureCorbaVillagePieces.Road)) {
					k++;
				}
			}
			this.hasMoreThanTwoComponents = k > 2;

			System.out.println("hasMoreThanTwoComponents = " + hasMoreThanTwoComponents);
			System.out.println("in Village.Start: " + new ChunkPos(x, z).getBlock(0, 0, 0));
		}

		@Override
		public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
			System.out.println("Start.generateStructure");
			super.generateStructure(worldIn, rand, structurebb);

		}

		@Override
		public boolean isSizeableStructure() {
			return this.hasMoreThanTwoComponents;
		}

		@Override
		public void writeToNBT(NBTTagCompound tagCompound) {
			super.writeToNBT(tagCompound);
			tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
		}

		@Override
		public void readFromNBT(NBTTagCompound tagCompound) {
			super.readFromNBT(tagCompound);
			this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
		}
	}
}