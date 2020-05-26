package net.journey.dimension.corba.village.pieces;

import java.util.List;
import java.util.Random;

import net.journey.dimension.corba.village.StructureCorbaVillagePieces;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class CorbaVillageLamp extends StructureCorbaVillagePieces.Village {
	
	public CorbaVillageLamp() {}

	public CorbaVillageLamp(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing) {
		super(start, type);
		this.setCoordBaseMode(facing);
		this.boundingBox = boundingBox;
	}

	public static StructureBoundingBox findPieceBox(StructureCorbaVillagePieces.Start start, List<StructureComponent> component, Random rand, int x, int y, int z, EnumFacing facing) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 3, 4, 2, facing);
		return StructureComponent.findIntersecting(component, structureboundingbox) != null ? null : structureboundingbox;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		if (this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
			if (this.averageGroundLvl < 0) {
				return true;
			}
			this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
		}
		IBlockState iblockstate = this.getBiomeSpecificBlockState(JourneyBlocks.corbaPost.getDefaultState());
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 3, 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
		this.setBlockState(worldIn, iblockstate, 1, 0, 0, structureBoundingBoxIn);
		this.setBlockState(worldIn, iblockstate, 1, 1, 0, structureBoundingBoxIn);
		this.setBlockState(worldIn, iblockstate, 1, 2, 0, structureBoundingBoxIn);
		this.setBlockState(worldIn, JourneyBlocks.corbaLamp.getDefaultState(), 1, 3, 0, structureBoundingBoxIn);
		return true;
	}
}