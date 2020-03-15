package net.journey.dimension.depths;

import net.journey.JourneyBlocks;
import net.journey.blocks.portal.BlockDepthsPortalFrame;
import net.journey.dimension.DimensionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterDepths extends Teleporter {

	protected WorldServer myWorld;

	public TeleporterDepths(WorldServer var1) {
		super(var1);
		this.myWorld = var1;
	}

	public boolean findPortalBlockNearEntity(Entity entity, int yLimit) {
		int chunkX = (MathHelper.floor(entity.posX) & ~0xf);
		int chunkZ = (MathHelper.floor(entity.posZ) & ~0xf);
		int y;
		
		for(y = 1; y < yLimit; y++) {
			for(int x2 = chunkX; x2 < chunkX + 16; x2++) {
				for(int z2 = chunkZ; z2 < chunkZ + 16; z2++) {
					if(this.myWorld.getBlockState(new BlockPos(x2, y, z2)) == JourneyBlocks.depthsPortal
							.getDefaultState()) {
						if(myWorld.provider.getDimension() == 0) {
							entity.setLocationAndAngles(x2, getTopBlock(myWorld, x2, z2) + 1, z2, entity.rotationYaw, 0.0F);	
						} else {
							entity.setLocationAndAngles(x2, getTopBlock(myWorld, x2, z2) + 1, z2, entity.rotationYaw, 0.0F);
						}
						entity.motionX = entity.motionY = entity.motionZ = 0.0D;
						return true;
					}
				}
			}

		}
		return false;
	}

	public static int getTopBlock(World world, int x, int z) {
		for(int i = 90; i > 0; i--) {
			if(world.getBlockState(new BlockPos(x, i, z)) != Blocks.AIR.getDefaultState()) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
		if(entity.dimension == DimensionHelper.depthsType.getId()) {
			int chunkX = (MathHelper.floor(entity.posX) & ~0xf);
			int chunkZ = (MathHelper.floor(entity.posZ) & ~0xf);
			int y;
			boolean foundPortal = findPortalBlockNearEntity(entity, 256);
			if(foundPortal) return true;
			for(y = 8; y < 256; y += 8) {
				if(this.myWorld.getBlockState(new BlockPos(chunkX + 7, y, chunkZ + 7)) == Blocks.AIR.getDefaultState() && this.myWorld.getBlockState(new BlockPos(chunkX + 7, y + 8, chunkZ + 7)) == Blocks.AIR .getDefaultState()) {
					makePortalAt(this.myWorld, new BlockPos(chunkX, getTopBlock(myWorld, chunkX, chunkZ) + 1, chunkZ));
					foundPortal = findPortalBlockNearEntity(entity, 256);
					if(foundPortal) {
						return true;
					}
				}
			}
		} else {
			findPortalBlockNearEntity(entity, 256);
			entity.motionX = entity.motionY = entity.motionZ = 0.0D;
			return true;
		}
		return false;
	}

	private void makePortalAt(World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.setBlockState(new BlockPos(x, y, z), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x, y, z + 1), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x, y, z + 2), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 1, y, z + 3), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 2, y, z + 3), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 3, y, z + 3), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 4, y, z), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 4, y, z + 1), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 4, y, z + 2), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 1, y, z - 1), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 2, y, z - 1), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));
		world.setBlockState(new BlockPos(x + 3, y, z - 1), JourneyBlocks.depthsPortalFrame.getDefaultState().withProperty(BlockDepthsPortalFrame.EYE, true));

		world.setBlockState(new BlockPos(x + 1, y, z), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 2, y, z), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 3, y, z), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 1, y, z + 1), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 2, y, z + 1), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 3, y, z + 1), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 1, y, z + 2), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 2, y, z + 2), JourneyBlocks.depthsPortal.getDefaultState());
		world.setBlockState(new BlockPos(x + 3, y, z + 2), JourneyBlocks.depthsPortal.getDefaultState());
	}

	@Override
	public boolean makePortal(Entity entityIn) {
		return false;
	}
}
