package net.journey.dimension.senterian;

import net.journey.JourneyBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterSenterian extends Teleporter {
	
	protected WorldServer myWorld;

	public TeleporterSenterian(WorldServer var1) {
		super(var1);
		this.myWorld = var1;
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, float f) {
		short searchRange = 200;
		double var10 = -1.0D;
		int var12 = 0;
		int var13 = 0;
		int var14 = 0;
		int entityPosX_floored = MathHelper.floor(entity.posX);
		int entityPosY = MathHelper.floor(entity.posZ);
		double var24;

		for(int searchX = entityPosX_floored - searchRange; searchX <= entityPosX_floored + searchRange; ++searchX) {
			double var18 = searchX + 0.5D - entity.posX;

			for(int searchZ = entityPosY - searchRange; searchZ <= entityPosY + searchRange; ++searchZ) {
				double var21 = searchZ + 0.5D - entity.posZ;

				for(int searchY = 128 - 1; searchY >= 0; --searchY) {
					if(this.isBlockPortal(this.myWorld, searchX, searchY, searchZ)) {
						while(this.isBlockPortal(this.myWorld, searchX, searchY - 1, searchZ)) {
							--searchY;
						}

						var24 = searchY + 0.5D - entity.posY;
						double var26 = var18 * var18 + var24 * var24 + var21 * var21;

						if(var10 < 0.0D || var26 < var10) {
							var10 = var26;
							var12 = searchX;
							var13 = searchY;
							var14 = searchZ;
						}
					}
				}
			}
		}

		if(var10 >= 0.0D) {
			double var28 = var12 + 0.5D;
			double var22 = var13 + 0.5D;
			var24 = var14 + 0.5D;
			if(this.isBlockPortal(this.myWorld, var12 - 1, var13, var14)) var28 -= 0.5D;
			if(this.isBlockPortal(this.myWorld, var12 + 1, var13, var14)) var28 += 0.5D;
			if(this.isBlockPortal(this.myWorld, var12, var13, var14 - 1)) var24 -= 0.5D;
			if(this.isBlockPortal(this.myWorld, var12, var13, var14 + 1)) var24 += 0.5D;
			entity.setLocationAndAngles(var28, var22 + 1.0D, var24 + 1.0D, 180F, 0.0F);
			entity.motionX = entity.motionY = entity.motionZ = 0.0D;
			return true;
		} else 
			return false;
	}


	public boolean isBlockPortal(World var1, int var2, int var3, int var4) {
		return var1.getBlockState(new BlockPos(var2, var3, var4)) == JourneyBlocks.senterianPortal.getDefaultState();
	}

	@Override
	public boolean makePortal(Entity entity) {
		byte var4 = 16;
		double var32, var33, var16, var19, var5 = -1.0D, var2 = this.myWorld.provider.getDimension() == 0 ? 2.0D : 0.5D;
		int var7 = MathHelper.floor(entity.posX);
		int var8 = MathHelper.floor(entity.posY * var2);
		int var9 = MathHelper.floor(entity.posZ);
		int var10 = var7, var11 = var8, var12 = var9, var13 = 0, var14 = this.myWorld.rand.nextInt(4), var15, var18, var21, var23, var22, var25, var24, var27, var26, var29, var28;

		
		for(var15 = var7 - var4; var15 <= var7 + var4; ++var15) {
			var16 = var15 + 0.5D - entity.posX;

			for(var18 = var9 - var4; var18 <= var9 + var4; ++var18) {
				var19 = var18 + 0.5D - entity.posZ;
				label178:

					for(var21 = 127; var21 >= 0; --var21) {
						if(this.myWorld.isAirBlock(new BlockPos(var15, var21, var18))) {
							while(var21 > 0 && this.myWorld.isAirBlock(new BlockPos(var15, var21 - 1, var18))) {
								--var21;
							}

							for(var22 = var14; var22 < var14 + 4; ++var22) {
								var23 = var22 % 2;
								var24 = 1 - var23;

								if(var22 % 4 >= 2) {
									var23 = -var23;
									var24 = -var24;
								}

								for(var25 = 0; var25 < 3; ++var25) {
									for(var26 = 0; var26 < 4; ++var26) {
										for(var27 = -1; var27 < 4; ++var27) {
											var28 = var15 + (var26 - 1) * var23 + var25 * var24;
											var29 = var21 + var27;
											int var30 = var18 + (var26 - 1) * var24 - var25 * var23;

											if(var27 < 0 && !this.myWorld.getBlockState(new BlockPos(var28, var29, var30)).getBlock().getMaterial(null).isSolid() || var27 >= 0 && !this.myWorld.isAirBlock(new BlockPos(var28, var29, var30))) {
												continue label178;
											}
										}
									}
								}

								var32 = var21 + 0.5D - entity.posY * var2;
								var33 = var16 * var16 + var32 * var32 + var19 * var19;

								if(var5 < 0.0D || var33 < var5) {
									var5 = var33;
									var10 = var15;
									var11 = var21;
									var12 = var18;
									var13 = var22 % 4;
								}
							}
						}
					}
			}
		}

		if(var5 < 0.0D) {
			for(var15 = var7 - var4; var15 <= var7 + var4; ++var15) {
				var16 = var15 + 0.5D - entity.posX;

				for(var18 = var9 - var4; var18 <= var9 + var4; ++var18) {
					var19 = var18 + 0.5D - entity.posZ;
					label126:

						for(var21 = 127; var21 >= 0; --var21) {
							if(this.myWorld.isAirBlock(new BlockPos(var15, var21, var18))) {
								while(var21 > 0 && this.myWorld.isAirBlock(new BlockPos(var15, var21 - 1, var18))) {
									--var21;
								}

								for(var22 = var14; var22 < var14 + 2; ++var22) {
									var23 = var22 % 2;
									var24 = 1 - var23;

									for(var25 = 0; var25 < 4; ++var25) {
										for(var26 = -1; var26 < 4; ++var26) {
											var27 = var15 + (var25 - 1) * var23;
											var28 = var21 + var26;
											var29 = var18 + (var25 - 1) * var24;

											if(var26 < 0 && !this.myWorld.getBlockState(new BlockPos(var27, var28, var29)).getBlock().getMaterial(null).isSolid() || var26 >= 0 && !this.myWorld.isAirBlock(new BlockPos(var27, var28, var29))) {
												continue label126;
											}
										}
									}

									var32 = var21 + 0.5D - entity.posY * var2;
									var33 = var16 * var16 + var32 * var32 + var19 * var19;

									if(var5 < 0.0D || var33 < var5) {
										var5 = var33;
										var10 = var15;
										var11 = var21;
										var12 = var18;
										var13 = var22 % 2;
									}
								}
							}
						}
				}
			}
		}

		int var31 = var13 % 2;
		int var20 = 1 - var31;

		if(var13 % 4 >= 2) {
			var31 = -var31;
			var20 = -var20;
		}

		this.makePortalAt(this.myWorld, var10, var11, var12);
		return true;
	}

	private void makePortalAt(World world, int i, int j, int k) {
		j = 90;
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 1), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 2), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 3), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 4), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 5), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 10), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 11), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 12), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 13), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 14), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 1, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 1), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 2), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 3), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 12), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 13), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 14), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 2, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 1), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 2), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 3), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 4), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 5), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 6), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 7), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 8), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 9), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 10), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 11), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 12), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 13), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 14), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 0, j + 3, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 1, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 2, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 1, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 1, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 2, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 2, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 3, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 5), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 10), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 7), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 8), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 6), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 7), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 8), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 9), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 7, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 4, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 6), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 7), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 8), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 9), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 2, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 2, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 3, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 3, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 4, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 4, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 5, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 5, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 6, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 6, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 7, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 7, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 5, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 5), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 10), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 2, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 2, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 2, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 2, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 3, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 3, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 4, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 4, k + 7), JourneyBlocks.senterianMellowLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 4, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 5, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 5, k + 7), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 5, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 6, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 6, k + 7), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 6, k + 9), JourneyBlocks.senterianLightLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 6, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 7, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 7, k + 7), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 7, k + 9), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 7, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 1, k + 5), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 7, j + 1, k + 10), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 7, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 5, k + 8), JourneyBlocks.senterianLightLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 6, k + 4), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 6, k + 8), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 6, k + 11), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 7, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 7, k + 8), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 7, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 1, k + 5), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 8, j + 1, k + 10), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 8, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 4, k + 9), JourneyBlocks.senterianMellowLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 5, k + 9), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 6, k + 4), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 6, k + 6), JourneyBlocks.senterianMellowLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 6, k + 9), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 6, k + 11), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 7, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 7, k + 6), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 7, k + 9), JourneyBlocks.senterianPost.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 7, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 5), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 10), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 2, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 2, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 2, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 2, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 3, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 3, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 4, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 4, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 5, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 5, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 6, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 6, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 7, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 7, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 6), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 7), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 8), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 9), JourneyBlocks.senterianPortalFrame.getStateFromMeta(5));
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 2, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 2, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 3, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 3, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 4, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 4, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 5, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 5, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 6, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 6, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 7, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 7, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 10, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 5), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 10), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 7), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 8), JourneyBlocks.senterianGuardianLamp.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 4), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 6), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 7), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 8), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 9), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 7, k + 11), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 11, j + 8, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 1, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 1, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 2, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 2, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 12, j + 6, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 13, j + 5, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 1, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 1, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 2, k + 0), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 2, k + 15), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 3, k + 0), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 3, k + 15), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 1), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 2), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 3), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 4), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 5), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 6), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 7), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 8), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 9), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 10), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 11), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 12), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 13), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 14, j + 4, k + 14), JourneyBlocks.senterianRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 0), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 1), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 2), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 3), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 4), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 5), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 6), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 7), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 8), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 9), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 10), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 11), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 12), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 13), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 14), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 0, k + 15), JourneyBlocks.senterianFloor.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 1), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 2), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 3), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 4), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 5), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 10), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 11), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 12), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 13), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 14), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 1, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 1), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 2), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 3), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 4), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 5), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 6), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 9), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 10), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 11), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 12), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 13), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 14), JourneyBlocks.senterianBars.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 2, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 0), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 1), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 2), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 3), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 4), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 5), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 6), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 7), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 8), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 9), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 10), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 11), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 12), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 13), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 14), JourneyBlocks.senterianBricks.getDefaultState());
		world.setBlockState( new BlockPos(i + 15, j + 3, k + 15), JourneyBlocks.senterianCarvedRock.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 6), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 7), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 8), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 6, j + 1, k + 9), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 1, k + 6), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 1, k + 7), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 1, k + 8), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 7, j + 1, k + 9), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 1, k + 6), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 1, k + 7), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 1, k + 8), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 8, j + 1, k + 9), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 6), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 7), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 8), JourneyBlocks.senterianPortal.getDefaultState());
		world.setBlockState( new BlockPos(i + 9, j + 1, k + 9), JourneyBlocks.senterianPortal.getDefaultState());

	}
}
