package net.journey.dimension.corba.village.pieces;

import java.util.List;
import java.util.Random;

import net.journey.dimension.corba.village.StructureCorbaVillagePieces;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class CorbaVillageChurch extends StructureCorbaVillagePieces.Village {
	
	public CorbaVillageChurch() {}

	public CorbaVillageChurch(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing) {
		super(start, type);
		this.setCoordBaseMode(facing);
		this.boundingBox = boundingBox;
	}

	public static CorbaVillageChurch createPiece(StructureCorbaVillagePieces.Start start, List<StructureComponent> component, Random rand, int x, int y, int z, EnumFacing facing, int type) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 12, 12, 12, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(component, structureboundingbox) == null ? new CorbaVillageChurch(start, type, rand, structureboundingbox, facing) : null;
	}

	public void setBlockState(World worldIn, StructureBoundingBox boundingboxIn, int x, int y, int z, IBlockState blockstateIn) {
		this.setBlockState(worldIn, blockstateIn, x, y, z, boundingboxIn);
	}

	@Override
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

	@Override
	protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
		return 2;
	}
}