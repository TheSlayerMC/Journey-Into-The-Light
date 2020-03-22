package net.journey.blocks.portal;

import net.journey.JourneyBlocks;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

class PortalSize {
	private final Block portalFrame;
	private final Block portal;
	private final World world;
	private final BlockPos originPos;
	private final EnumFacing.Axis axis;
	private final EnumFacing rightDir;
	private final EnumFacing leftDir;
	int portalBlockCount;
	private BlockPos bottomLeft;
	private int height;
	private int width;

	public PortalSize(Block portalFrame, Block portal, World worldIn, BlockPos pos, EnumFacing.Axis axis) {
		this.portalFrame = portalFrame;
		this.portal = portal;
		this.world = worldIn;
		this.originPos = pos;
		this.axis = axis;

		if(axis == EnumFacing.Axis.X) {
			this.leftDir = EnumFacing.EAST;
			this.rightDir = EnumFacing.WEST;
		} else {
			this.leftDir = EnumFacing.NORTH;
			this.rightDir = EnumFacing.SOUTH;
		}

		for(BlockPos start = pos; pos.getY() > start.getY() - 21 && pos.getY() > 0 && this.isEmptyBlock(pos.down()); pos = pos.down()) {
			;
		}

		int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;

		if(i >= 0) {
			this.bottomLeft = pos.offset(this.leftDir, i);
			this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);

			if(this.width < 2 || this.width > 21) {
				this.bottomLeft = null;
				this.width = 0;
			}
		}

		if(this.bottomLeft != null) {
			this.height = this.calculatePortalHeight();
		}
	}

	public PortalSize spin(Axis axis) {
		return new PortalSize(portalFrame, portal, world, originPos, axis);
	}

	protected int getDistanceUntilEdge(BlockPos pos, EnumFacing facing) {
		int i;

		for(i = 0; i < 22; ++i) {
			BlockPos blockpos = pos.offset(facing, i);

			if(!this.isEmptyBlock(blockpos) || this.world.getBlockState(blockpos.down()).getBlock() != portalFrame) {
				break;
			}
		}

		Block block = this.world.getBlockState(pos.offset(facing, i)).getBlock();
		return block == portalFrame ? i : 0;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	protected int calculatePortalHeight() {
		label56:

			for(this.height = 0; this.height < 21; ++this.height) {
				for(int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);

					if(!this.isEmptyBlock(blockpos)) {
						break label56;
					}

					Block block = this.world.getBlockState(blockpos).getBlock();
					if(block == portal) {
						++this.portalBlockCount;
					}

					if(i == 0) {
						block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();

						if(block != portalFrame) {
							break label56;
						}
					}
					else if(i == this.width - 1) {
						block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();

						if(block != portalFrame) {
							break label56;
						}
					}
				}
			}

	for(int j = 0; j < this.width; ++j) {
		if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != portalFrame) {
			this.height = 0;
			break;
		}
	}

	if (this.height <= 21 && this.height >= 3) {
		return this.height;
	} else {
		this.bottomLeft = null;
		this.width = 0;
		this.height = 0;
		return 0;
	}
	}

	protected boolean isEmptyBlock(BlockPos pos) {
		Block blockIn;
		return world.isAirBlock(pos) || (blockIn = world.getBlockState(pos).getBlock()) == JourneyBlocks.fire || blockIn == portal;
	}

	public boolean isValid() {
		return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
	}

	public void placePortalBlocks() {
		for(int i = 0; i < this.width; ++i) {
			BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

			for(int j = 0; j < this.height; ++j) {
				this.world.setBlockState(blockpos.up(j), portal.getDefaultState().withProperty(BlockModPortal.AXIS, this.axis), 2);
			}
		}
	}
}