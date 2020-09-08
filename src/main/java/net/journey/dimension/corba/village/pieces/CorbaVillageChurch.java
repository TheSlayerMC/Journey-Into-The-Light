package net.journey.dimension.corba.village.pieces;

import net.journey.dimension.corba.templates.CorbaVillageChurchTemplate;
import net.journey.dimension.corba.village.StructureCorbaVillagePieces;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.List;
import java.util.Random;

public class CorbaVillageChurch extends StructureCorbaVillagePieces.Village {

	public CorbaVillageChurch() {
	}

	public CorbaVillageChurch(StructureCorbaVillagePieces.Start start, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing) {
		super(start, type);
		this.setCoordBaseMode(facing);
		this.boundingBox = boundingBox;
	}

	public static CorbaVillageChurch createPiece(StructureCorbaVillagePieces.Start start, List<StructureComponent> components, Random rand, int x, int y, int z, EnumFacing facing, int type) {
		StructureBoundingBox bb = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 16, 20, 16, facing);
		//noinspection ConstantConditions
		return canVillageGoDeeper(bb) && StructureComponent.findIntersecting(components, bb) == null ? new CorbaVillageChurch(start, type, rand, bb, facing) : null;
	}

	public Rotation getRandomRotation() {
		return RandHelper.chooseEqual(RandHelper.RANDOM, Rotation.CLOCKWISE_180, Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90);
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox boundingBoxIn) {
		if (this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(worldIn, boundingBoxIn);

			if (this.averageGroundLvl < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
		}

		CorbaVillageChurchTemplate template = new CorbaVillageChurchTemplate(this, boundingBoxIn);
		template.generate(worldIn, randomIn);

		this.spawnVillagers(worldIn, boundingBoxIn, 2, 1, 2, 1);
		return true;
	}

	@Override
	protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
		return 2;
	}

	public IBlockState getRandomBrick() {
		return RandHelper.chooseEqual(RandHelper.RANDOM,
				JourneyBlocks.corbaCrackedBricks,
				JourneyBlocks.corbaBricks,
				JourneyBlocks.corbaDarkBricks,
				JourneyBlocks.corbaLightBricks)
				.getDefaultState();
	}
}