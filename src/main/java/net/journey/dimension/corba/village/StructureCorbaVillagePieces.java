package net.journey.dimension.corba.village;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.journey.entity.mob.corba.npc.EntityOvergrownMerchant;
import net.journey.entity.mob.corba.npc.EntityRedTordo;
import net.journey.entity.mob.corba.npc.EntityTordo;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;
import net.slayer.api.worldgen.WorldGenAPI;

public class StructureCorbaVillagePieces {

	public static void registerVillagePieces() {
		SlayerAPI.addMapGen(MapGenCorbaVillage.class, "CorbaVillage"); //done
		SlayerAPI.addMapGen(MapGenCorbaVillage.Start.class, "CorbaVillageStart"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.House1.class, "CorbaVillageHouse1"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Torch.class, "CorbaVillageTorch"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Hall.class, "CorbaVillageHall"); // done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Garden.class, "CorbaVillageGarden"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.WoodHut.class, "CorbaVillageWoodHut"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Church.class, "CorbaVillageChurch");
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.House2.class, "CorbaVillageHouse2"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Start.class, "CorbaVillagePieceStart"); //done
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Path.class, "CorbaVillagePath"); //done
		//SlayerAPI.addMapGen(StructureCorbaVillagePieces.House3.class, "CorbaVillageHouse3");
		SlayerAPI.addMapGen(StructureCorbaVillagePieces.Well.class, "CorbaVillageWell"); //done
	}

	public static List<StructureCorbaVillagePieces.PieceWeight> getStructureVillageWeightedPieceList(Random random,
			int size) {
		List<StructureCorbaVillagePieces.PieceWeight> list = Lists.<StructureCorbaVillagePieces.PieceWeight>newArrayList();
		list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.Garden.class, 4,
				MathHelper.getInt(random, 2 + size, 4 + size * 2)));
		list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.Church.class, 20,
				MathHelper.getInt(random, 0 + size, 1 + size)));
		list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.House1.class, 20,
				MathHelper.getInt(random, 0 + size, 2 + size)));
		list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.WoodHut.class, 3,
				MathHelper.getInt(random, 2 + size, 5 + size * 3)));
		list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.Hall.class, 15,
				MathHelper.getInt(random, 0 + size, 2 + size)));
		list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.House2.class, 15,
				MathHelper.getInt(random, 0, 1 + size)));
		//list.add(new StructureCorbaVillagePieces.PieceWeight(StructureCorbaVillagePieces.House3.class, 8, MathHelper.getInt(random, 0 + size, 3 + size * 2)));
		Iterator<StructureCorbaVillagePieces.PieceWeight> iterator = list.iterator();

		while (iterator.hasNext()) {
			if ((iterator.next()).villagePiecesLimit == 0) {
				iterator.remove();
			}
		}
		return list;
	}

	private static int updatePieceWeight(List<StructureCorbaVillagePieces.PieceWeight> p_75079_0_) {
		boolean flag = false;
		int i = 0;

		for (StructureCorbaVillagePieces.PieceWeight structurevillagepieces$pieceweight : p_75079_0_) {
			if (structurevillagepieces$pieceweight.villagePiecesLimit > 0
					&& structurevillagepieces$pieceweight.villagePiecesSpawned < structurevillagepieces$pieceweight.villagePiecesLimit) {
				flag = true;
			}
			i += structurevillagepieces$pieceweight.villagePieceWeight;
		}

		return flag ? i : -1;
	}

	private static StructureCorbaVillagePieces.Village findAndCreateComponentFactory(
			StructureCorbaVillagePieces.Start start, StructureCorbaVillagePieces.PieceWeight weight,
			List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY,
			int structureMinZ, EnumFacing facing, int componentType) {
		Class<? extends StructureCorbaVillagePieces.Village> oclass = weight.villagePieceClass;
		StructureCorbaVillagePieces.Village structurevillagepieces$village = null;

		if (oclass == StructureCorbaVillagePieces.Garden.class) {
			structurevillagepieces$village = StructureCorbaVillagePieces.Garden.createPiece(start,
					structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		} else if (oclass == StructureCorbaVillagePieces.Church.class) {
			structurevillagepieces$village = StructureCorbaVillagePieces.Church.createPiece(start, structureComponents,
					rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		} else if (oclass == StructureCorbaVillagePieces.House1.class) {
			structurevillagepieces$village = StructureCorbaVillagePieces.House1.createPiece(start, structureComponents,
					rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		} else if (oclass == StructureCorbaVillagePieces.WoodHut.class) {
			structurevillagepieces$village = StructureCorbaVillagePieces.WoodHut.createPiece(start, structureComponents,
					rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		} else if (oclass == StructureCorbaVillagePieces.Hall.class) {
			structurevillagepieces$village = StructureCorbaVillagePieces.Hall.createPiece(start, structureComponents,
					rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		} else if (oclass == StructureCorbaVillagePieces.House2.class) {
			structurevillagepieces$village = StructureCorbaVillagePieces.House2.createPiece(start, structureComponents,
					rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		//} else if (oclass == StructureCorbaVillagePieces.House3.class) {
			//structurevillagepieces$village = StructureCorbaVillagePieces.House3.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		} else {
			// structurevillagepieces$village =
			// net.minecraftforge.fml.common.registry.VillagerRegistry.getVillageComponent(weight,
			// start , structureComponents, rand, structureMinX, structureMinY,
			// structureMinZ, facing, componentType);
			structurevillagepieces$village = StructureCorbaVillagePieces.Garden.createPiece(start,
					structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
		}
		return structurevillagepieces$village;
	}

	private static StructureCorbaVillagePieces.Village generateComponent(StructureCorbaVillagePieces.Start start,
			List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY,
			int structureMinZ, EnumFacing facing, int componentType) {
		int i = updatePieceWeight(start.structureVillageWeightedPieceList);

		if (i <= 0) {
			return null;
		} else {
			int j = 0;

			while (j < 5) {
				++j;
				int k = rand.nextInt(i);

				for (StructureCorbaVillagePieces.PieceWeight structurevillagepieces$pieceweight : start.structureVillageWeightedPieceList) {
					k -= structurevillagepieces$pieceweight.villagePieceWeight;

					if (k < 0) {
						if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePiecesOfType(componentType)
								|| structurevillagepieces$pieceweight == start.lastPlaced
										&& start.structureVillageWeightedPieceList.size() > 1) {
							break;
						}

						StructureCorbaVillagePieces.Village structurevillagepieces$village = findAndCreateComponentFactory(
								start, structurevillagepieces$pieceweight, structureComponents, rand, structureMinX,
								structureMinY, structureMinZ, facing, componentType);

						if (structurevillagepieces$village != null) {
							++structurevillagepieces$pieceweight.villagePiecesSpawned;
							start.lastPlaced = structurevillagepieces$pieceweight;

							if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePieces()) {
								start.structureVillageWeightedPieceList.remove(structurevillagepieces$pieceweight);
							}

							return structurevillagepieces$village;
						}
					}
				}
			}

			StructureBoundingBox structureboundingbox = StructureCorbaVillagePieces.Torch.findPieceBox(start,
					structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing);

			if (structureboundingbox != null) {
				return new StructureCorbaVillagePieces.Torch(start, componentType, rand, structureboundingbox, facing);
			} else {
				return null;
			}
		}
	}

	private static StructureComponent generateAndAddComponent(StructureCorbaVillagePieces.Start start,
			List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY,
			int structureMinZ, EnumFacing facing, int componentType) {
		if (componentType > 50) {
			return null;
		} else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112
				&& Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112) {
			StructureComponent structurecomponent = generateComponent(start, structureComponents, rand, structureMinX,
					structureMinY, structureMinZ, facing, componentType + 1);

			if (structurecomponent != null) {
				structureComponents.add(structurecomponent);
				start.pendingHouses.add(structurecomponent);
				return structurecomponent;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private static StructureComponent generateAndAddRoadPiece(StructureCorbaVillagePieces.Start start,
			List<StructureComponent> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_,
			EnumFacing facing, int p_176069_7_) {
		if (p_176069_7_ > 3 + start.terrainType) {
			return null;
		} else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112
				&& Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112) {
			StructureBoundingBox structureboundingbox = StructureCorbaVillagePieces.Path.findPieceBox(start,
					p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

			if (structureboundingbox != null && structureboundingbox.minY > 10) {
				StructureComponent structurecomponent = new StructureCorbaVillagePieces.Path(start, p_176069_7_, rand,
						structureboundingbox, facing);
				p_176069_1_.add(structurecomponent);
				start.pendingRoads.add(structurecomponent);
				return structurecomponent;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static class Church extends StructureCorbaVillagePieces.Village {
		public Church() {
		}

		public Church(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45564_4_,
				EnumFacing facing) {
			super(start, type);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45564_4_;
		}

		public static StructureCorbaVillagePieces.Church createPiece(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_,
				EnumFacing facing, int p_175854_7_) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_,
					p_175854_4_, p_175854_5_, 0, 0, 0, 12, 12, 12, facing);
			return canVillageGoDeeper(structureboundingbox)
					&& StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null
							? new StructureCorbaVillagePieces.Church(start, p_175854_7_, rand, structureboundingbox,
									facing)
							: null;
		}

		public void setBlockState(World worldIn, StructureBoundingBox boundingboxIn, int x, int y, int z, IBlockState blockstateIn) {
			this.setBlockState(worldIn, blockstateIn, x, y, z, boundingboxIn);
		}
		
		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox boundingBoxIn) {
			int i = 0;
			int j = 0;
			int k = 0;
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, boundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
			}
			this.spawnVillagers(worldIn, boundingBoxIn, 2, 1, 2, 1);
			return true;
		}

		protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
			return 2;
		}
	}

	public static class Hall extends StructureCorbaVillagePieces.Village {
		public Hall() {
		}

		public Hall(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45567_4_,
				EnumFacing facing) {
			super(start, type);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45567_4_;
		}

		public static StructureCorbaVillagePieces.Hall createPiece(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175857_1_, Random rand, int p_175857_3_, int p_175857_4_, int p_175857_5_,
				EnumFacing facing, int p_175857_7_) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175857_3_,
					p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, facing);
			return canVillageGoDeeper(structureboundingbox)
					&& StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null
							? new StructureCorbaVillagePieces.Hall(start, p_175857_7_, rand, structureboundingbox,
									facing)
							: null;
		}
		
		public IBlockState chooseRandomBrick() {
			return RandHelper.chooseEqual(new Random(), JourneyBlocks.corbaCrackedBricks.getDefaultState(), JourneyBlocks.corbaBricks.getDefaultState(), JourneyBlocks.corbaDarkBricks.getDefaultState(), JourneyBlocks.corbaLightBricks.getDefaultState());
		}

		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			int i = 0;
			int j = 0;
			int k = 0;
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
			}
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 4, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 6, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 8, (IBlockState)Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 2, Blocks.FURNACE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 3, Blocks.FURNACE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 4, Blocks.FURNACE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 5, (IBlockState)Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 4, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 6, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 0, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 5, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 10, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 10, Blocks.AIR.getDefaultState());

			this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
			return true;
		}
		
		private void setBlockState(World worldIn, StructureBoundingBox structureBoundingBoxIn, int x, int y, int z, IBlockState defaultState) {
			this.setBlockState(worldIn, defaultState, x, y, z, structureBoundingBoxIn);
		}

		protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
			return villagersSpawnedIn == 0 ? 4 : super.chooseProfession(villagersSpawnedIn, currentVillagerProfession);
		}
	}

	public static class House1 extends StructureCorbaVillagePieces.Village {
		public House1() {
		}

		public House1(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45571_4_,
				EnumFacing facing) {
			super(start, type);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45571_4_;
		}

		public static StructureCorbaVillagePieces.House1 createPiece(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175850_1_, Random rand, int p_175850_3_, int p_175850_4_, int p_175850_5_,
				EnumFacing facing, int p_175850_7_) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175850_3_,
					p_175850_4_, p_175850_5_, 0, 0, 0, 16, 16, 16, facing);
			return canVillageGoDeeper(structureboundingbox)
					&& StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null
							? new StructureCorbaVillagePieces.House1(start, p_175850_7_, rand, structureboundingbox,
									facing)
							: null;
		}

		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 * @return 
		 */
		
		public IBlockState chooseRandomBrick() {
			return RandHelper.chooseEqual(new Random(), JourneyBlocks.corbaCrackedBricks.getDefaultState(), JourneyBlocks.corbaBricks.getDefaultState(), JourneyBlocks.corbaDarkBricks.getDefaultState(), JourneyBlocks.corbaLightBricks.getDefaultState());
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			int i = 0;
			int j = 0;
			int k = 0;
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 16 - 1, 0);
			}

			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));

			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));

			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 0, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 9, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 10, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 0, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 10, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 4, (IBlockState)Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 6, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 5, Blocks.TORCH.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 5, Blocks.BED.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 2, (IBlockState)Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 8, Blocks.CRAFTING_TABLE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 2, (IBlockState)Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 8, Blocks.FURNACE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 2, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 8, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 2, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 8, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 2, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 8, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 2, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 8, Blocks.BOOKSHELF.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 5, Blocks.TORCH.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 0, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 5, Blocks.OAK_DOOR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 1, k + 10, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 2, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 8, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 7, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 5, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 13, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 0, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 0, k + 10, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 0, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 9, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 1, k + 10, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 3, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 7, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 4, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 6, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 14, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 15, j + 9, k + 10, Blocks.AIR.getDefaultState());
			
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 1, 15, 16, 4, 16, JourneyBlocks.corbaCobblestone.getDefaultState(), JourneyBlocks.corbaCobblestone.getDefaultState(), false);
			
			this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
			return true;
		}

		private void setBlockState(World worldIn, StructureBoundingBox structureBoundingBoxIn, int x, int y, int z, IBlockState defaultState) {
			this.setBlockState(worldIn, defaultState, x, y, z, structureBoundingBoxIn);
		}

		protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
			return 1;
		}
	}

	public static class House2 extends StructureCorbaVillagePieces.Village {
		private boolean hasMadeChest;

		public House2() {
		}

		public House2(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45563_4_,
				EnumFacing facing) {
			super(start, type);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45563_4_;
		}

		public static StructureCorbaVillagePieces.House2 createPiece(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175855_1_, Random rand, int p_175855_3_, int p_175855_4_, int p_175855_5_,
				EnumFacing facing, int p_175855_7_) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175855_3_,
					p_175855_4_, p_175855_5_, 0, 0, 0, 10, 6, 7, facing);
			return canVillageGoDeeper(structureboundingbox)
					&& StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null
							? new StructureCorbaVillagePieces.House2(start, p_175855_7_, rand, structureboundingbox,
									facing)
							: null;
		}

		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			super.writeStructureToNBT(tagCompound);
			tagCompound.setBoolean("Chest", this.hasMadeChest);
		}

		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			super.readStructureFromNBT(tagCompound, p_143011_2_);
			this.hasMadeChest = tagCompound.getBoolean("Chest");
		}

		public IBlockState chooseRandomBrick() {
			return RandHelper.chooseEqual(new Random(), JourneyBlocks.corbaCrackedBricks.getDefaultState(), JourneyBlocks.corbaBricks.getDefaultState(), JourneyBlocks.corbaDarkBricks.getDefaultState(), JourneyBlocks.corbaLightBricks.getDefaultState());
		}
		
		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			int i = 0;
			int j = 0;
			int k = 0;
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
			}


			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 3, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 4, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 6, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 7, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 5, Blocks.TORCH.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 1, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 9, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 5, Blocks.TORCH.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 9, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 3, (IBlockState)Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 2, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 8, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 7, Blocks.NETHERRACK.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 3, Blocks.BED.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 9, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 2, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 8, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 1, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 9, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 9, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 5, Blocks.TORCH.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 2, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 8, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 5, Blocks.TORCH.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 1, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 3, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 4, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 6, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 7, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 3, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 4, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 6, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 7, (IBlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 3, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 4, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 6, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 7, Blocks.GLASS.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 2, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 8, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 6, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 7, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 8, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 9, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 10, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 13, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 9, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 10, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 11, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 12, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 13, k + 10, Blocks.AIR.getDefaultState());

			this.spawnVillagers(worldIn, structureBoundingBoxIn, 7, 1, 1, 1);
			return true;
		}

		private void setBlockState(World worldIn, StructureBoundingBox structureBoundingBoxIn, int x, int y, int z, IBlockState defaultState) {
			this.setBlockState(worldIn, defaultState, x, y, z, structureBoundingBoxIn);
		}
		
		protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
			return 3;
		}
	}

	public static class House3 extends StructureCorbaVillagePieces.Village {
		public House3() {
		}

		public House3(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45561_4_,
				EnumFacing facing) {
			super(start, type);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45561_4_;
		}

		public static StructureCorbaVillagePieces.House3 createPiece(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_,
				EnumFacing facing, int p_175849_7_) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_,
					p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, facing);
			return canVillageGoDeeper(structureboundingbox)
					&& StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null
							? new StructureCorbaVillagePieces.House3(start, p_175849_7_, rand, structureboundingbox,
									facing)
							: null;
		}

		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
			}

			IBlockState iblockstate = this.getBiomeSpecificBlockState(JourneyBlocks.corbaStone.getDefaultState());
			IBlockState iblockstate1 = this.getBiomeSpecificBlockState(
					Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			IBlockState iblockstate2 = this.getBiomeSpecificBlockState(
					Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			IBlockState iblockstate3 = this.getBiomeSpecificBlockState(
					Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			IBlockState iblockstate4 = this.getBiomeSpecificBlockState(
					Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			IBlockState iblockstate5 = this.getBiomeSpecificBlockState(JourneyBlocks.corbaPlank.getDefaultState());
			IBlockState iblockstate6 = this.getBiomeSpecificBlockState(JourneyBlocks.corbaLog.getDefaultState());
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(),
					Blocks.AIR.getDefaultState(), false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(),
					Blocks.AIR.getDefaultState(), false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 5, 8, 0, 10, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, iblockstate, iblockstate, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 3, 10, iblockstate, iblockstate, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 2, 0, iblockstate, iblockstate, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 2, 1, 5, iblockstate, iblockstate, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 2, 3, 10, iblockstate, iblockstate, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 10, 7, 3, 10, iblockstate, iblockstate, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 2, 3, 5, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 3, 4, 4, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, iblockstate5, iblockstate5, false);
			this.setBlockState(worldIn, iblockstate5, 0, 4, 2, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 0, 4, 3, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 8, 4, 2, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 8, 4, 3, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 8, 4, 4, structureBoundingBoxIn);
			IBlockState iblockstate7 = iblockstate1;
			IBlockState iblockstate8 = iblockstate2;
			IBlockState iblockstate9 = iblockstate4;
			IBlockState iblockstate10 = iblockstate3;

			for (int i = -1; i <= 2; ++i) {
				for (int j = 0; j <= 8; ++j) {
					this.setBlockState(worldIn, iblockstate7, j, 4 + i, i, structureBoundingBoxIn);

					if ((i > -1 || j <= 1) && (i > 0 || j <= 3) && (i > 1 || j <= 4 || j >= 6)) {
						this.setBlockState(worldIn, iblockstate8, j, 4 + i, 5 - i, structureBoundingBoxIn);
					}
				}
			}

			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 5, 3, 4, 10, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 2, 7, 4, 10, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 4, 4, 5, 10, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 5, 4, 6, 5, 10, iblockstate5, iblockstate5, false);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 6, 3, 5, 6, 10, iblockstate5, iblockstate5, false);

			for (int k = 4; k >= 1; --k) {
				this.setBlockState(worldIn, iblockstate5, k, 2 + k, 7 - k, structureBoundingBoxIn);

				for (int k1 = 8 - k; k1 <= 10; ++k1) {
					this.setBlockState(worldIn, iblockstate10, k, 2 + k, k1, structureBoundingBoxIn);
				}
			}

			this.setBlockState(worldIn, iblockstate5, 6, 6, 3, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 7, 5, 4, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate4, 6, 6, 4, structureBoundingBoxIn);

			for (int l = 6; l <= 8; ++l) {
				for (int l1 = 5; l1 <= 10; ++l1) {
					this.setBlockState(worldIn, iblockstate9, l, 12 - l, l1, structureBoundingBoxIn);
				}
			}

			this.setBlockState(worldIn, iblockstate6, 0, 2, 1, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 0, 2, 4, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 4, 2, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 6, 2, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 8, 2, 1, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 8, 2, 4, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 8, 2, 5, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 8, 2, 6, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 7, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 8, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 8, 2, 9, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 2, 2, 6, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 7, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 8, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 2, 2, 9, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 4, 4, 10, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 4, 10, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate6, 6, 4, 10, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate5, 5, 5, 10, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
			this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);
			this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH);
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, -1, 3, 2, -1, Blocks.AIR.getDefaultState(),
					Blocks.AIR.getDefaultState(), false);

			if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR
					&& this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn)
							.getMaterial() != Material.AIR) {
				this.setBlockState(worldIn, iblockstate7, 2, 0, -1, structureBoundingBoxIn);

				if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn)
						.getBlock() == JourneyBlocks.corbaGrassPath) {
					this.setBlockState(worldIn, JourneyBlocks.corbaGrass.getDefaultState(), 2, -1, -1,
							structureBoundingBoxIn);
				}
			}

			for (int i1 = 0; i1 < 5; ++i1) {
				for (int i2 = 0; i2 < 9; ++i2) {
					this.clearCurrentPositionBlocksUpwards(worldIn, i2, 7, i1, structureBoundingBoxIn);
					this.replaceAirAndLiquidDownwards(worldIn, iblockstate, i2, -1, i1, structureBoundingBoxIn);
				}
			}

			for (int j1 = 5; j1 < 11; ++j1) {
				for (int j2 = 2; j2 < 9; ++j2) {
					this.clearCurrentPositionBlocksUpwards(worldIn, j2, 7, j1, structureBoundingBoxIn);
					this.replaceAirAndLiquidDownwards(worldIn, iblockstate, j2, -1, j1, structureBoundingBoxIn);
				}
			}

			this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
			return true;
		}
	}

	public static class Garden extends StructureCorbaVillagePieces.Village {
		private boolean isRoofAccessible;

		public Garden() {}

		public Garden(StructureCorbaVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing) {
			super(start, p_i45566_2_);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45566_4_;
			this.isRoofAccessible = rand.nextBoolean();
		}

		@Override
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			super.writeStructureToNBT(tagCompound);
			tagCompound.setBoolean("Terrace", this.isRoofAccessible);
		}

		@Override
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			super.readStructureFromNBT(tagCompound, p_143011_2_);
			this.isRoofAccessible = tagCompound.getBoolean("Terrace");
		}

		public static StructureCorbaVillagePieces.Garden createPiece(StructureCorbaVillagePieces.Start start, List<StructureComponent> component, Random rand, int x, int y, int z, EnumFacing facing, int par1) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 9, 9, 9, facing);
			return StructureComponent.findIntersecting(component, structureboundingbox) != null ? null : new StructureCorbaVillagePieces.Garden(start, par1, rand, structureboundingbox, facing);
		}

		public IBlockState chooseRandomBrick() {
			return RandHelper.chooseEqual(new Random(), JourneyBlocks.corbaCrackedBricks.getDefaultState(), JourneyBlocks.corbaBricks.getDefaultState(), JourneyBlocks.corbaDarkBricks.getDefaultState(), JourneyBlocks.corbaLightBricks.getDefaultState());
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			int i = 0;
			int j = -3;
			int k = 0;
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 10 - 1, 0);
			}
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 7, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 5, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 6, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 7, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 5, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 6, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 2, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 3, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 4, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 5, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 6, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 7, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 8, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 2, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 3, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 5, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 7, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 8, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 2, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 3, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 5, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 7, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 8, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 2, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 3, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 4, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 5, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 6, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 7, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 8, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 2, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 3, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 5, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 7, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 8, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 2, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 3, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 5, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 7, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 8, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 2, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 3, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 4, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 5, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 6, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 7, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 8, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 5, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 7, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 8, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 2, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 3, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 4, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 5, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 6, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 7, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 8, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 2, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 3, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 5, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 7, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 8, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 2, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 3, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 5, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 7, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 8, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 2, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 3, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 4, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 5, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 6, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 7, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 8, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 5, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 7, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 8, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 7, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 2, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 3, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 4, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 5, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 6, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 7, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 8, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 2, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 3, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 5, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 7, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 8, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 2, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 3, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 5, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 7, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 8, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 8, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 2, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 3, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 4, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 5, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 6, JourneyBlocks.corbaGrass.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 7, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 8, JourneyBlocks.corbaStone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 9, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 2, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 3, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 4, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 5, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 6, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 7, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 8, Blocks.FARMLAND.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 1, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 2, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 3, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 4, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 5, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 6, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 7, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 8, Blocks.WHEAT.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 9, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 1, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 9, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 9, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 6, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 7, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 8, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 9, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 6, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 7, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 8, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 7, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 8, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 9, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 2, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 7, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 8, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 5, Blocks.GLOWSTONE.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 6, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 7, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 5, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 6, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 10, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 10, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 9, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 2, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 8, Blocks.WOODEN_SLAB.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 11, j + 8, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 0, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 1, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 2, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 3, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 4, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 5, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 6, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 7, k + 11, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 7, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 8, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 9, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 10, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 12, j + 8, k + 11, Blocks.AIR.getDefaultState());

			this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
			return true;
		}
		
		private void setBlockState(World worldIn, StructureBoundingBox structureBoundingBoxIn, int x, int y, int z, IBlockState defaultState) {
			this.setBlockState(worldIn, defaultState, x, y, z, structureBoundingBoxIn);
		}
	}

	public static class Path extends StructureCorbaVillagePieces.Road {
		private int length;

		public Path() {
		}

		public Path(StructureCorbaVillagePieces.Start start, int p_i45562_2_, Random rand,
				StructureBoundingBox p_i45562_4_, EnumFacing facing) {
			super(start, p_i45562_2_);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45562_4_;
			this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
		}

		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			super.writeStructureToNBT(tagCompound);
			tagCompound.setInteger("Length", this.length);
		}

		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			super.readStructureFromNBT(tagCompound, p_143011_2_);
			this.length = tagCompound.getInteger("Length");
		}

		/**
		 * Initiates construction of the Structure Component picked, at the
		 * current Location of StructGen
		 */
		public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
			boolean flag = false;

			for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5)) {
				StructureComponent structurecomponent = this
						.getNextComponentNN((StructureCorbaVillagePieces.Start) componentIn, listIn, rand, 0, i);

				if (structurecomponent != null) {
					i += Math.max(structurecomponent.getBoundingBox().getXSize(),
							structurecomponent.getBoundingBox().getZSize());
					flag = true;
				}
			}

			for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5)) {
				StructureComponent structurecomponent1 = this
						.getNextComponentPP((StructureCorbaVillagePieces.Start) componentIn, listIn, rand, 0, j);

				if (structurecomponent1 != null) {
					j += Math.max(structurecomponent1.getBoundingBox().getXSize(),
							structurecomponent1.getBoundingBox().getZSize());
					flag = true;
				}
			}

			EnumFacing enumfacing = this.getCoordBaseMode();

			if (flag && rand.nextInt(3) > 0 && enumfacing != null) {
				switch (enumfacing) {
				case NORTH:
				default:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ,
							EnumFacing.WEST, this.getComponentType());
					break;
				case SOUTH:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2,
							EnumFacing.WEST, this.getComponentType());
					break;
				case WEST:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1,
							EnumFacing.NORTH, this.getComponentType());
					break;
				case EAST:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1,
							EnumFacing.NORTH, this.getComponentType());
				}
			}

			if (flag && rand.nextInt(3) > 0 && enumfacing != null) {
				switch (enumfacing) {
				case NORTH:
				default:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ,
							EnumFacing.EAST, this.getComponentType());
					break;
				case SOUTH:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2,
							EnumFacing.EAST, this.getComponentType());
					break;
				case WEST:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1,
							EnumFacing.SOUTH, this.getComponentType());
					break;
				case EAST:
					StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn,
							listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1,
							EnumFacing.SOUTH, this.getComponentType());
				}
			}
		}

		public static StructureBoundingBox findPieceBox(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175848_1_, Random rand, int p_175848_3_, int p_175848_4_, int p_175848_5_,
				EnumFacing facing) {
			for (int i = 7 * MathHelper.getInt(rand, 3, 5); i >= 7; i -= 7) {
				StructureBoundingBox structureboundingbox = StructureBoundingBox
						.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, i, facing);

				if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null) {
					return structureboundingbox;
				}
			}

			return null;
		}

		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			IBlockState iblockstate = this.getBiomeSpecificBlockState(JourneyBlocks.corbaGrassPath.getDefaultState());
			IBlockState iblockstate1 = this.getBiomeSpecificBlockState(JourneyBlocks.corbaPlank.getDefaultState());
			IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.GRAVEL.getDefaultState());
			IBlockState iblockstate3 = this.getBiomeSpecificBlockState(JourneyBlocks.corbaStone.getDefaultState());

			for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
				for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
					BlockPos blockpos = new BlockPos(i, 64, j);

					if (structureBoundingBoxIn.isVecInside(blockpos)) {
						blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();

						if (blockpos.getY() < worldIn.getSeaLevel()) {
							blockpos = new BlockPos(blockpos.getX(), worldIn.getSeaLevel() - 1, blockpos.getZ());
						}

						while (blockpos.getY() >= worldIn.getSeaLevel() - 1) {
							IBlockState iblockstate4 = worldIn.getBlockState(blockpos);

							if (iblockstate4.getBlock() == JourneyBlocks.corbaGrass
									&& worldIn.isAirBlock(blockpos.up())) {
								worldIn.setBlockState(blockpos, iblockstate, 2);
								break;
							}

							if (iblockstate4.getMaterial().isLiquid()) {
								worldIn.setBlockState(blockpos, iblockstate1, 2);
								break;
							}

							if (iblockstate4.getBlock() == Blocks.SAND || iblockstate4.getBlock() == Blocks.SANDSTONE
									|| iblockstate4.getBlock() == Blocks.RED_SANDSTONE) {
								worldIn.setBlockState(blockpos, iblockstate2, 2);
								worldIn.setBlockState(blockpos.down(), iblockstate3, 2);
								break;
							}

							blockpos = blockpos.down();
						}
					}
				}
			}

			return true;
		}
	}

	public static class PieceWeight {
		public Class<? extends StructureCorbaVillagePieces.Village> villagePieceClass;
		public final int villagePieceWeight;
		public int villagePiecesSpawned;
		public int villagePiecesLimit;

		public PieceWeight(Class<? extends StructureCorbaVillagePieces.Village> p_i2098_1_, int p_i2098_2_,
				int p_i2098_3_) {
			this.villagePieceClass = p_i2098_1_;
			this.villagePieceWeight = p_i2098_2_;
			this.villagePiecesLimit = p_i2098_3_;
		}

		public boolean canSpawnMoreVillagePiecesOfType(int componentType) {
			return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
		}

		public boolean canSpawnMoreVillagePieces() {
			return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
		}
	}

	public abstract static class Road extends StructureCorbaVillagePieces.Village {
		public Road() {
		}

		protected Road(StructureCorbaVillagePieces.Start start, int type) {
			super(start, type);
		}
	}

	public static class Start extends StructureCorbaVillagePieces.Well {
		public BiomeProvider biomeProvider;
		/** World terrain type, 0 for normal, 1 for flap map */
		public int terrainType;
		public StructureCorbaVillagePieces.PieceWeight lastPlaced;
		/**
		 * Contains List of all spawnable Structure Piece Weights. If no more
		 * Pieces of a type can be spawned, they are removed from this list
		 */
		public List<StructureCorbaVillagePieces.PieceWeight> structureVillageWeightedPieceList;
		public List<StructureComponent> pendingHouses = Lists.<StructureComponent>newArrayList();
		public List<StructureComponent> pendingRoads = Lists.<StructureComponent>newArrayList();
		public Biome biome;

		public Start() {
		}

		public Start(BiomeProvider biomeProviderIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_,
				List<StructureCorbaVillagePieces.PieceWeight> p_i2104_6_, int p_i2104_7_) {
			super((StructureCorbaVillagePieces.Start) null, 0, rand, p_i2104_4_, p_i2104_5_);
			this.biomeProvider = biomeProviderIn;
			this.structureVillageWeightedPieceList = p_i2104_6_;
			this.terrainType = p_i2104_7_;
			Biome biome = biomeProviderIn.getBiome(new BlockPos(p_i2104_4_, 0, p_i2104_5_), Biomes.DEFAULT);
			this.biome = biome;
			this.startPiece = this;

			if (biome instanceof BiomeDesert) {
				this.structureType = 1;
			} else if (biome instanceof BiomeSavanna) {
				this.structureType = 2;
			} else if (biome instanceof BiomeTaiga) {
				this.structureType = 3;
			}

			this.setStructureType(this.structureType);
			this.isZombieInfested = rand.nextInt(50) == 0;
		}
	}

	public static class Torch extends StructureCorbaVillagePieces.Village {
		public Torch() {
		}

		public Torch(StructureCorbaVillagePieces.Start start, int p_i45568_2_, Random rand,
				StructureBoundingBox p_i45568_4_, EnumFacing facing) {
			super(start, p_i45568_2_);
			this.setCoordBaseMode(facing);
			this.boundingBox = p_i45568_4_;
		}

		public static StructureBoundingBox findPieceBox(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_,
				EnumFacing facing) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_,
					p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 2, facing);
			return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null
					: structureboundingbox;
		}

		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
			}

			IBlockState iblockstate = this.getBiomeSpecificBlockState(JourneyBlocks.corbaPost.getDefaultState());
			this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 3, 1, Blocks.AIR.getDefaultState(),
					Blocks.AIR.getDefaultState(), false);
			this.setBlockState(worldIn, iblockstate, 1, 0, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate, 1, 1, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, iblockstate, 1, 2, 0, structureBoundingBoxIn);
			this.setBlockState(worldIn, Blocks.WOOL.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0,
					structureBoundingBoxIn);
			this.placeTorch(worldIn, EnumFacing.EAST, 2, 3, 0, structureBoundingBoxIn);
			this.placeTorch(worldIn, EnumFacing.NORTH, 1, 3, 1, structureBoundingBoxIn);
			this.placeTorch(worldIn, EnumFacing.WEST, 0, 3, 0, structureBoundingBoxIn);
			this.placeTorch(worldIn, EnumFacing.SOUTH, 1, 3, -1, structureBoundingBoxIn);
			return true;
		}
	}

	public abstract static class Village extends StructureComponent {
		protected int averageGroundLvl = -1;
		/** The number of villagers that have been spawned in this component. */
		private int villagersSpawned;
		protected int structureType;
		protected boolean isZombieInfested;
		protected StructureCorbaVillagePieces.Start startPiece;

		public Village() {
		}

		protected Village(StructureCorbaVillagePieces.Start start, int type) {
			super(type);

			if (start != null) {
				this.structureType = start.structureType;
				this.isZombieInfested = start.isZombieInfested;
				startPiece = start;
			}
		}

		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			tagCompound.setInteger("HPos", this.averageGroundLvl);
			tagCompound.setInteger("VCount", this.villagersSpawned);
			tagCompound.setByte("Type", (byte) this.structureType);
			tagCompound.setBoolean("Zombie", this.isZombieInfested);
		}

		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			this.averageGroundLvl = tagCompound.getInteger("HPos");
			this.villagersSpawned = tagCompound.getInteger("VCount");
			this.structureType = tagCompound.getByte("Type");

			if (tagCompound.getBoolean("Desert")) {
				this.structureType = 1;
			}

			this.isZombieInfested = tagCompound.getBoolean("Zombie");
		}

		/**
		 * Gets the next village component, with the bounding box shifted -1 in
		 * the X and Z direction.
		 */
		@Nullable
		protected StructureComponent getNextComponentNN(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> structureComponents, Random rand, int p_74891_4_, int p_74891_5_) {
			EnumFacing enumfacing = this.getCoordBaseMode();

			if (enumfacing != null) {
				switch (enumfacing) {
				case NORTH:
				default:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_,
							this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
				case SOUTH:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_,
							this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
				case WEST:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_,
							this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
				case EAST:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_,
							this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
				}
			} else {
				return null;
			}
		}

		/**
		 * Gets the next village component, with the bounding box shifted +1 in
		 * the X and Z direction.
		 */
		@Nullable
		protected StructureComponent getNextComponentPP(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> structureComponents, Random rand, int p_74894_4_, int p_74894_5_) {
			EnumFacing enumfacing = this.getCoordBaseMode();

			if (enumfacing != null) {
				switch (enumfacing) {
				case NORTH:
				default:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_,
							this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
				case SOUTH:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_,
							this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
				case WEST:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_,
							this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
				case EAST:
					return StructureCorbaVillagePieces.generateAndAddComponent(start, structureComponents, rand,
							this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_,
							this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
				}
			} else {
				return null;
			}
		}

		/**
		 * Discover the y coordinate that will serve as the ground level of the
		 * supplied BoundingBox. (A median of all the levels in the BB's
		 * horizontal rectangle).
		 */
		protected int getAverageGroundLevel(World worldIn, StructureBoundingBox structurebb) {
			int i = 0;
			int j = 0;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

			for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k) {
				for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l) {
					blockpos$mutableblockpos.setPos(l, 64, k);

					if (structurebb.isVecInside(blockpos$mutableblockpos)) {
						i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(),
								worldIn.provider.getAverageGroundLevel() - 1);
						++j;
					}
				}
			}

			if (j == 0) {
				return -1;
			} else {
				return i / j;
			}
		}

		protected static boolean canVillageGoDeeper(StructureBoundingBox structurebb) {
			return structurebb != null && structurebb.minY > 10;
		}

		/**
		 * Spawns a number of villagers in this component. Parameters: world,
		 * component bounding box, x offset, y offset, z offset, number of
		 * villagers
		 */
		protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count) {
			if (this.villagersSpawned < count) {
				for (int i = this.villagersSpawned; i < count; ++i) {
					int j = this.getXWithOffset(x + i, z);
					int k = this.getYWithOffset(y);
					int l = this.getZWithOffset(x + i, z);

					if (!structurebb.isVecInside(new BlockPos(j, k, l))) {
						break;
					}

					++this.villagersSpawned;

					if (this.isZombieInfested) {

					} else {
						EntityModVillager villager = RandHelper.chooseEqual(new Random(),
								new EntityOvergrownMerchant(worldIn), new EntityRedTordo(worldIn),
								new EntityTordo(worldIn));
						villager.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
						villager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(villager)),
								(IEntityLivingData) null, false);
						worldIn.spawnEntity(villager);
					}
				}
			}
		}

		@Deprecated // Use Forge version below.
		protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
			return currentVillagerProfession;
		}

		protected net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession chooseForgeProfession(
				int count, net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof) {
			return net.minecraftforge.fml.common.registry.VillagerRegistry.getById(
					chooseProfession(count, net.minecraftforge.fml.common.registry.VillagerRegistry.getId(prof)));
		}

		protected IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn) {
			net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(
					startPiece == null ? null : startPiece.biome, blockstateIn);
			net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
			if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
				return event.getReplacement();
			if (this.structureType == 1) {
				if (blockstateIn.getBlock() == JourneyBlocks.corbaLog
						|| blockstateIn.getBlock() == JourneyBlocks.corbaLog) {
					return Blocks.SANDSTONE.getDefaultState();
				}

				if (blockstateIn.getBlock() == JourneyBlocks.corbaStone) {
					return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
				}

				if (blockstateIn.getBlock() == Blocks.PLANKS) {
					return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
				}

				if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
					return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
							blockstateIn.getValue(BlockStairs.FACING));
				}

				if (blockstateIn.getBlock() == Blocks.STONE_STAIRS) {
					return Blocks.SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
							blockstateIn.getValue(BlockStairs.FACING));
				}

				if (blockstateIn.getBlock() == Blocks.GRAVEL) {
					return Blocks.SANDSTONE.getDefaultState();
				}
			} else if (this.structureType == 3) {
				if (blockstateIn.getBlock() == JourneyBlocks.corbaLog
						|| blockstateIn.getBlock() == JourneyBlocks.corbaLog) {
					return JourneyBlocks.corbaLog.getDefaultState()
							.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE)
							.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
				}

				if (blockstateIn.getBlock() == Blocks.PLANKS) {
					return JourneyBlocks.corbaPlank.getDefaultState().withProperty(BlockPlanks.VARIANT,
							BlockPlanks.EnumType.SPRUCE);
				}

				if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
					return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
							blockstateIn.getValue(BlockStairs.FACING));
				}

				if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
					return Blocks.SPRUCE_FENCE.getDefaultState();
				}
			} else if (this.structureType == 2) {
				if (blockstateIn.getBlock() == JourneyBlocks.corbaLog
						|| blockstateIn.getBlock() == JourneyBlocks.corbaLog) {
					return JourneyBlocks.corbaLog.getDefaultState()
							.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA)
							.withProperty(BlockLog.LOG_AXIS, blockstateIn.getValue(BlockLog.LOG_AXIS));
				}

				if (blockstateIn.getBlock() == Blocks.PLANKS) {
					return JourneyBlocks.corbaPlank.getDefaultState().withProperty(BlockPlanks.VARIANT,
							BlockPlanks.EnumType.ACACIA);
				}

				if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
					return Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,
							blockstateIn.getValue(BlockStairs.FACING));
				}

				if (blockstateIn.getBlock() == JourneyBlocks.corbaStone) {
					return JourneyBlocks.corbaLog.getDefaultState()
							.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA)
							.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
				}

				if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
					return Blocks.ACACIA_FENCE.getDefaultState();
				}
			}

			return blockstateIn;
		}

		protected BlockDoor biomeDoor() {
			switch (this.structureType) {
			case 2:
				return Blocks.ACACIA_DOOR;
			case 3:
				return Blocks.SPRUCE_DOOR;
			default:
				return Blocks.OAK_DOOR;
			}
		}

		protected void createVillageDoor(World p_189927_1_, StructureBoundingBox p_189927_2_, Random p_189927_3_,
				int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_) {
			if (!this.isZombieInfested) {
				this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_,
						EnumFacing.NORTH, this.biomeDoor());
			}
		}

		protected void placeTorch(World p_189926_1_, EnumFacing p_189926_2_, int p_189926_3_, int p_189926_4_,
				int p_189926_5_, StructureBoundingBox p_189926_6_) {
			if (!this.isZombieInfested) {
				this.setBlockState(p_189926_1_,
						Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, p_189926_2_), p_189926_3_,
						p_189926_4_, p_189926_5_, p_189926_6_);
			}
		}

		/**
		 * Replaces air and liquid from given position downwards. Stops when
		 * hitting anything else than air or liquid
		 */
		protected void replaceAirAndLiquidDownwards(World worldIn, IBlockState blockstateIn, int x, int y, int z,
				StructureBoundingBox boundingboxIn) {
			IBlockState iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
			super.replaceAirAndLiquidDownwards(worldIn, iblockstate, x, y, z, boundingboxIn);
		}

		protected void setStructureType(int p_189924_1_) {
			this.structureType = p_189924_1_;
		}
	}

	public static class Well extends StructureCorbaVillagePieces.Village {
		public Well() {
		}

		public Well(StructureCorbaVillagePieces.Start start, int type, Random rand, int x, int z) {
			super(start, type);
			this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

			if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
				this.boundingBox = new StructureBoundingBox(x, 64, z, x + 8 - 1, 78, z + 8 - 1);
			} else {
				this.boundingBox = new StructureBoundingBox(x, 64, z, x + 8 - 1, 78, z + 8 - 1);
			}
		}

		/**
		 * Initiates construction of the Structure Component picked, at the
		 * current Location of StructGen
		 */
		public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
			StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn, listIn,
					rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1,
					EnumFacing.WEST, this.getComponentType());
			StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn, listIn,
					rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1,
					EnumFacing.EAST, this.getComponentType());
			StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn, listIn,
					rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1,
					EnumFacing.NORTH, this.getComponentType());
			StructureCorbaVillagePieces.generateAndAddRoadPiece((StructureCorbaVillagePieces.Start) componentIn, listIn,
					rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1,
					EnumFacing.SOUTH, this.getComponentType());
		}

		/**
		 * second Part of Structure generating, this for example places
		 * Spiderwebs, Mob Spawners, it closes Mineshafts at the end, it adds
		 * Fences...
		 */
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			int i = 0;
			int j = +8;
			int k = 0;
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4, 0);
			}
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 10, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 10, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 11, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 2, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 3, Blocks.WATER.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 10, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 11, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 1, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 4, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 0, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 10, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 9, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			return true;
		}
		
		private void setBlockState(World worldIn, StructureBoundingBox structureBoundingBoxIn, int x, int y, int z, IBlockState defaultState) {
			this.setBlockState(worldIn, defaultState, x, y, z, structureBoundingBoxIn);
		}
	}

	public static class WoodHut extends StructureCorbaVillagePieces.Village {
		private boolean isTallHouse;
		private int tablePosition;

		public WoodHut() {
		}

		public WoodHut(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox structurebb,
				EnumFacing facing) {
			super(start, type);
			this.setCoordBaseMode(facing);
			this.boundingBox = structurebb;
			this.isTallHouse = rand.nextBoolean();
			this.tablePosition = rand.nextInt(3);
		}

		@Override
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			super.writeStructureToNBT(tagCompound);
			tagCompound.setInteger("T", this.tablePosition);
			tagCompound.setBoolean("C", this.isTallHouse);
		}

		@Override
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			super.readStructureFromNBT(tagCompound, p_143011_2_);
			this.tablePosition = tagCompound.getInteger("T");
			this.isTallHouse = tagCompound.getBoolean("C");
		}

		public static StructureCorbaVillagePieces.WoodHut createPiece(StructureCorbaVillagePieces.Start start,
				List<StructureComponent> p_175853_1_, Random rand, int p_175853_3_, int p_175853_4_, int p_175853_5_,
				EnumFacing facing, int p_175853_7_) {
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175853_3_,
					p_175853_4_, p_175853_5_, 0, 0, 0, 8, 12, 8, facing);
			return canVillageGoDeeper(structureboundingbox)
					&& StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null
							? new StructureCorbaVillagePieces.WoodHut(start, p_175853_7_, rand, structureboundingbox,
									facing)
							: null;
		}
		
		public IBlockState chooseRandomBrick() {
			return RandHelper.chooseEqual(new Random(), JourneyBlocks.corbaCrackedBricks.getDefaultState(), JourneyBlocks.corbaBricks.getDefaultState(), JourneyBlocks.corbaDarkBricks.getDefaultState(), JourneyBlocks.corbaLightBricks.getDefaultState());
		}

		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			if (this.averageGroundLvl < 0) {
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

				if (this.averageGroundLvl < 0) {
					return true;
				}

				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
			}

			int k = 0;
			int i = +5;
			int j = 0;
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 0, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 3, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, EnumDoorHalf.LOWER));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 3, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, EnumDoorHalf.UPPER));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 1, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 2, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 3, JourneyBlocks.corbaWall.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 3, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 3, Blocks.CHEST.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 3, Blocks.TRAPDOOR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 1, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 5, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 8, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 3, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 5, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 4, j + 9, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 1, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 2, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 3, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 4, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 5, JourneyBlocks.corbaCobblestone.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 0, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 1, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 2, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 3, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 4, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 5, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 1, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 3, JourneyBlocks.corbaPost.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 5, JourneyBlocks.corbaLog.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 2, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 3, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 4, chooseRandomBrick());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 5, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 0, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 1, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 2, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 4, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 5, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 6, k + 6, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 1, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 2, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 3, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 4, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 5, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 7, k + 6, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 0, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 1, Blocks.AIR.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 2, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 3, JourneyBlocks.corbaPlank.getDefaultState());
			this.setBlockState(worldIn, structureBoundingBoxIn, i + 6, j + 8, k + 4, (IBlockState)Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));

			this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
			return true;
		}
		
		private void setBlockState(World worldIn, StructureBoundingBox structureBoundingBoxIn, int x, int y, int z, IBlockState defaultState) {
			this.setBlockState(worldIn, defaultState, x, y, z, structureBoundingBoxIn);
		}
	}
}